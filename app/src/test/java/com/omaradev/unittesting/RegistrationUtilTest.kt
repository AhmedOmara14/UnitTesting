package com.omaradev.unittesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class RegistrationUtilTest{
    /**
     *Empty UserName/password return false
     *valid UserName and Password Correctly Repeated return true
     *UserName already exist return false
     *inCorrect confirmed Password return false
     *Password Not Valid return false
     */

    @Test
    fun `empty userName return false`(){
        val result =RegistrationUtil
            .validateRegistrationInput("","123","123")
        assertThat(result).isFalse()
    }

    @Test
    fun `empty password return false`(){
        val result =RegistrationUtil
            .validateRegistrationInput("ahmed","","")
        assertThat(result).isFalse()
    }

    @Test
    fun `valid userName and password return true`(){
        val result =RegistrationUtil
            .validateRegistrationInput("ahmed","tester123","tester123")
        assertThat(result).isTrue()
    }

    @Test
    fun `userName is exist return false`(){
        val result =RegistrationUtil
            .validateRegistrationInput("ahmDev","123","123")
        assertThat(result).isFalse()
    }
    @Test
    fun `inCorrect confirmed Password return false`(){
        val result =RegistrationUtil
            .validateRegistrationInput("ahmed","123456","874569")
        assertThat(result).isFalse()
    }

    @Test
    fun `password not valid return false`(){
        val result =RegistrationUtil
            .validateRegistrationInput("ahm","test1","test1")
        assertThat(result).isFalse()
    }

}