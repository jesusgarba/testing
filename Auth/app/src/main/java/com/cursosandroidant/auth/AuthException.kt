package com.cursosandroidant.auth

import java.lang.Exception

class AuthException(val authEvent: AuthEvent, msg: String? = null) : Exception(msg) {
}