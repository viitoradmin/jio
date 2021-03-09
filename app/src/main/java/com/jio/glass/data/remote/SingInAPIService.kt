package com.jio.glass.data.remote

import retrofit2.Retrofit
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Query

interface SingInAPIService {

    @GET("/signin")
    suspend fun signInUser(@Query("email") email: String, @Query("password") password: String): ApiUser

    companion object {
        operator fun invoke(retrofit: Retrofit) = retrofit.create<SingInAPIService>()
    }
}