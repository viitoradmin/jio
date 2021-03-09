package com.jio.glass.data.remote

import com.squareup.moshi.Json

data class ApiUser(
    @Json(name="id")
    val id: Int = 0,
    @Json(name="name")
    val name: String = "JioGlass",
    @Json(name="email")
    val email: String = "JioGlass@tesseract.com"
)
