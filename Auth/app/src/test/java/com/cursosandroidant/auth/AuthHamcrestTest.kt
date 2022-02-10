package com.cursosandroidant.auth


import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.hasItemInArray
import org.junit.Assert
import org.junit.Ignore
import org.junit.Test

class AuthHamcrestTest {

    //given-when-then

    @Test
    fun loginUser_correctData_returnsSuccessEvent(){
        val result = userAuthenticationTDD("schugarba@test.com", "1234")
        assertThat(AuthEvent.USER_EXIST, `is`(result))
    }

    @Test
    fun loginUser_wrongData_returnsFailEvent(){
        val result = userAuthenticationTDD("jesus@gmail.com","1234")
        assertThat(AuthEvent.NOT_USER_EXIST, `is`(result))
    }

    @Test
    fun loginUser_emptyEmail_returnsFailEvent(){
        val result = userAuthenticationTDD("","1234")
        assertThat(AuthEvent.EMPTY_EMAIL, `is`(result))
    }

    @Test
    fun loginUser_emptyPassword_returnsFailEvent(){
        val result = userAuthenticationTDD("schugarba@test.com","")
        assertThat(AuthEvent.EMPTY_PASSWORD, `is`(result))
    }

    @Test
    fun loginUser_emptyForm_returnsFailEvent(){
        val result = userAuthenticationTDD("", "")
        assertThat(AuthEvent.EMPTY_FORM, `is`(result))
    }

    @Test
    fun loginUser_invalidEmail_returnsFailEvent(){
        val result = userAuthenticationTDD("jesus@testcom","1234")
        assertThat(AuthEvent.INVALID_EMAIL, `is`(result))
    }

    @Test
    fun loginUser_invalidPassword_returnsFailEvent(){
        val result = userAuthenticationTDD("jesus@test.com","123u")
        assertThat(AuthEvent.INVALID_PASSWORD, `is`(result))
    }

    @Test
    fun loginUser_invalidUser_returnsFailEvent(){
        val result = userAuthenticationTDD("jesus@testcom","123A")
        assertThat(AuthEvent.INVALID_USER, `is`(result))
    }

    @Test(expected = AuthException::class)
    fun loginUser_nullEmail_returnsException(){
        val result = userAuthenticationTDD(null,"1234")
        assertThat(AuthEvent.NULL_EMAIL, `is`(result))
    }

    @Test
    fun loginUser_nullPassword_returnsException(){
        Assert.assertThrows(AuthException::class.java) {
            print(userAuthenticationTDD("schugarba@gmail.com", null))
        }
    }

    @Test
    fun loginUser_nullForm_returnsException(){
        try {
            val result = userAuthenticationTDD(null,null)
            assertThat(AuthEvent.NULL_FORM, `is`(result))
        } catch (e: Exception) {
            (e as? AuthException)?.let {
                assertThat(AuthEvent.NULL_FORM, `is`(it.authEvent))
            }
        }
    }

    //@Ignore("Falta definir requisito cliente...")
    @Test
    fun loginUser_errorLengthPassword_returnsFailEvent(){
        val result  = userAuthenticationTDD("schugarba@test.com","12345")
        assertThat(AuthEvent.ERROR_LENGTH_PASSWORD, `is`(result))
    }

    @Test
    fun checkNames_differentUsers_match(){
        assertThat("Maria", both(containsString("a")).and(containsString("i")))
    }

    @Test
    fun checkdata_emailPassword_noMatch(){
        val email = "schugarba@test.com"
        val password = "1234"
        assertThat(email, not(`is`(password)))
    }

    @Test
    fun checkExist_newEmail_returnString(){
        val oldEmail = "schugarba@test.com"
        val newEmail = "SchuNewEmail@test.com"
        val emails = arrayOf(oldEmail, newEmail)
        assertThat(emails, hasItemInArray(newEmail))
    }

    @Test
    fun checkDomain_arrayEmails_returnString(){
        val nextEmail = "peter@test.com"
        val oldEmail = "schugarba@test.com"
        val newEmail = "SchuNewEmail@test.com"
        val emails = arrayListOf(oldEmail, newEmail, nextEmail)
        assertThat(emails, everyItem(endsWith("test.com")))
    }
}