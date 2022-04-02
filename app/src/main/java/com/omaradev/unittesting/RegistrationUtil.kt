package com.omaradev.unittesting

object RegistrationUtil {
    private val existingUsers = listOf("ahmDev", "Dev")

    fun validateRegistrationInput(
        name: String,
        password: String,
        confirmPassword: String
    ): Boolean {
        if (name.isEmpty() || password.isEmpty())
            return false
        if (name in existingUsers)
            return false
        if (password !=confirmPassword)
            return false
        if (password.count { it.isDigit() }<2)
            return false

        return true
    }

}