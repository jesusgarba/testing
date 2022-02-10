package com.cursosandroidant.auth

import org.junit.Assert.assertEquals
import org.junit.Assert.assertThrows
import org.junit.Ignore
import org.junit.Test
import java.lang.NullPointerException

class AuthTDDTest {


     @Test
     fun login_completeFrom_existUser_returnsSuccessEvent(){
         val result = userAuthenticationTDD("schugarba@test.com", "1234")
         assertEquals(AuthEvent.USER_EXIST,result)
     }

     @Test
     fun login_completeForm_notExistUser_returnsFailEvent(){
         val result = userAuthenticationTDD("jesus@gmail.com","1234")
         assertEquals(AuthEvent.NOT_USER_EXIST, result)
     }

     @Test
     fun login_emptyEmail_returnsFailEvent(){
         val result = userAuthenticationTDD("","1234")
         assertEquals(AuthEvent.EMPTY_EMAIL, result)
     }

    @Test
    fun login_emptyPassword_returnsFailEvent(){
        val result = userAuthenticationTDD("schugarba@test.com","")
        assertEquals(AuthEvent.EMPTY_PASSWORD, result)
    }

    @Test
    fun login_emptyForm_returnsFailEvent(){
        val result = userAuthenticationTDD("", "")
        assertEquals(AuthEvent.EMPTY_FORM, result)
    }

    @Test
    fun login_completeForm_invalidEmail_returnsFailEvent(){
        val result = userAuthenticationTDD("jesus@testcom","1234")
        assertEquals(AuthEvent.INVALID_EMAIL, result)
    }

    @Test
    fun login_completeForm_invalidPassword_returnsFailEvent(){
        val result = userAuthenticationTDD("jesus@test.com","123u")
        assertEquals(AuthEvent.INVALID_PASSWORD, result)
    }

    @Test
    fun login_completeForm_invalidUser_returnsFailEvent(){
        val result = userAuthenticationTDD("jesus@testcom","123A")
        assertEquals(AuthEvent.INVALID_USER, result)
    }

    @Test(expected = AuthException::class)
    fun login_nullEmail_returnsException(){
        val result = userAuthenticationTDD(null,"1234")
        assertEquals(AuthEvent.NULL_EMAIL, result)
    }

    @Test
    fun login_nullPassword_returnsException(){
        assertThrows(AuthException::class.java){
            print(userAuthenticationTDD("schugarba@gmail.com", null))
        }
    }

    @Test
    fun login_nullForm_returnsException(){
        try {
            val result = userAuthenticationTDD(null,null)
            assertEquals(AuthEvent.NULL_FORM, result)
        } catch (e: Exception) {
            (e as? AuthException)?.let {
                assertEquals(AuthEvent.NULL_FORM, it.authEvent)
            }
        }
    }

    @Ignore("Falta definir requisito cliente...")
    @Test
    fun login_completeForm_errorLengthPassword_returnsFailEvent(){
        val result  = userAuthenticationTDD("schugarba@test.com","12345")
        assertEquals(AuthEvent.ERROR_LENGTH_PASSWORD, result)
    }
}