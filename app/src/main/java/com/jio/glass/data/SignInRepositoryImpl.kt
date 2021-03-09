package com.jio.glass.data

import com.jio.glass.data.remote.SingInAPIService
import com.jio.glass.domain.repository.SignInRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SignInRepositoryImpl @Inject constructor(
    private val signInAPIService: SingInAPIService
): SignInRepository {

    override fun signIn(email: String, password: String) = flow { emit(signInAPIService.signInUser(email, password)) }
}