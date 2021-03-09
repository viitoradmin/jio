package com.jio.glass.domain.repository

import com.jio.glass.data.remote.ApiUser
import kotlinx.coroutines.flow.Flow

interface SignInRepository {
    fun signIn(email: String, password: String): Flow<ApiUser>
}