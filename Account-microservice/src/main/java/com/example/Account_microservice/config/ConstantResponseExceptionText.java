package com.example.Account_microservice.config;



public final class ConstantResponseExceptionText {

    public static final String SING_OUT_USER_OK = "Вы успешно вышли из системы";
    public static final String SING_OUT_USER_UNAUTHORIZED = "Пользователь не аутентифицирован";
    public static final String VALIDATE_TOKEN_DEAD = "У данного токена истёк срок жизни";
    public static final String NOT_SUCH_USER = "Данный пользователь не найден";
    public static final String NOT_FOUND_USER_BY_ID = "Пользователь с  id %d не найден";
    public static final String INVALID_CREDENTIALS_MESSAGE = "Неверное имя пользователя или пароль";
    public static final String SUCCESSFUL_DELETE_USER = "Пользователь с %d id удалён";
    public static final String NOT_FOUND_ROLE_EXCEPTION = "%s данной роли нет в базе данных";
}
