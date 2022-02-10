package com.cursosandroidant.auth

enum class AuthEvent {
    //sucess
    USER_EXIST,
    //fail
    NOT_USER_EXIST,
    EMPTY_EMAIL,
    EMPTY_PASSWORD,
    EMPTY_FORM,
    INVALID_EMAIL,
    INVALID_PASSWORD,
    INVALID_USER,
    NULL_EMAIL,
    NULL_PASSWORD,
    NULL_FORM,
    ERROR_LENGTH_PASSWORD, ;

}