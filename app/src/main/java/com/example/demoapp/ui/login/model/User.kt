package com.example.demoapp.ui.login.model

import java.io.Serializable

data class User(
    var emailId: String,
    var password: String
) : Serializable

