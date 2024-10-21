package com.example.Account_microservice.config;



public final class ConstantResponseExceptionText {

    public static final String SING_OUT_USER_UNAUTHORIZED = "Пользователь не аутентифицирован";
    public static final String VALIDATE_TOKEN_BLACK_LIST = "Данный токен в находится в блэк листе";
    public static final String NOT_FOUND_USER_BY_ID = "Пользователь с  id %d не найден";
    public static final String NOT_FOUND_DOCTOR_BY_ID = "Доктор с  id %d не найден";
    public static final String NOT_FOUND_USER_BY_USERNAME = "Пользователь с ником %s не найден";
    public static final String INVALID_CREDENTIALS_MESSAGE = "Неверное имя пользователя или пароль. Аккаунт может иметь статус: удалён";
    public static final String NOT_FOUND_ROLE_EXCEPTION = "%s - данной роли нет в базе данных";
    public static final String BAD_TOKEN = "Токен не прошёл проверку";
    public static final String USERNAME_ALREADY_EXISTS = "данный username уже занят";
    public static final String SIGNATURE_TOKEN = "Неккореткны токен";
}
