package com.example.Timetable_microservice;


import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.time.Duration;

public class TimetableValidator implements ConstraintValidator<ValidTimetable, TimetableValidate> {


    @Override
    public boolean isValid(TimetableValidate timetable, ConstraintValidatorContext context) {
        if (timetable == null) {
            return true; // Можно использовать @NotNull для проверки на null
        }


        if (timetable.getTo().isBefore(timetable.getFrom())) {
            context.disableDefaultConstraintViolation(); // отключаеам стандартное сообщение об ошибки, чтобы можно было вставть своё
            context.buildConstraintViolationWithTemplate("Поле 'to' должно быть позже 'from'").addPropertyNode("to").addConstraintViolation(); // кастомное сообщение
            return false;
        }

        // Проверка разницы во времени
        Duration duration = Duration.between(timetable.getFrom(), timetable.getTo());
        if (duration.toHours() > 12) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Разница между 'to' и 'from' не должна превышать 12 часов").addPropertyNode("to").addConstraintViolation();
            return false;
        }

        // Проверка кратности 30 минут
        if (timetable.getFrom().getMinute() % 30 != 0 || timetable.getTo().getMinute() % 30 != 0) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Время должно быть кратно 30 минутам").addPropertyNode("from").addConstraintViolation();
            return false;
        }
        return true;
        }
}

