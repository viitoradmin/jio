package com.example.demoapp.ui.login.model

/**
 * Data validation state of the login form.
 */
data class LoginFormState(
    var emailIdError: Int? = null,
    var passwordError: Int? = null,
    )