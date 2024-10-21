package com.example.Document_microservice.document.config;



public final class ConstantResponseExceptionText {


    public static final String NOT_FOUND_USER_BY_ID = "Не удалось найти аккаунт с id {%d}";
    public static final String NOT_FOUND_HISTORY_BY_ID = "Не удалось найти историю посещения с id {%d}";
    public static final String USER_DOES_NOT_HAVE_THIS_ROLE = "Пользоваетль с id {%d} не обладает ролью {%s}";
    public static final String ROLES_MORE_ONE= "Пациент с id {%d} имеет несколько ролей";
    public static final String NOT_FOUND_ROOM_IN_HOSPITAL_BY_ID= "Комнаты с названием {%s} не найдена в больнице с id {%d}";
    public static final String ID_NOT_BELONG_USER= "Данная история не принадлежит пользователю с id {%d}";

}
