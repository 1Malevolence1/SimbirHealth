package com.example.Timetable_microservice.timetable.config;



public final class ConstantResponseExceptionText {

    public static final String NOT_FOUND_ROOM_BY_ID_HOSPITAL = "Команта {%s} не найден в бальнце с id {%d}";
    public static final String NOT_FOUND_TIMETABLE_BY_ID = "Запиьс с id {%d} не найдена";
    public static final String USER_NOT_REGISTERED_BY_ID = "Пользователь с ID {%d} не записан на приём";
    public static final String USER_ANOTHER_REGISTERED_BY_APPOINTMENT = "Данная запись занята";
    public static final String BADE_DELETE_REGISTERED_BY_ID = "Не удлась удлать отменить запись. Дрйгой пользователь записан на данную запись";
    public static final String BAD_UPDATE_TIMETABLE_BY_ID = "Ошибка обновлении записи. Кто-то записан на приём";

}
