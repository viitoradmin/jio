package com.jio.glass.domain.usecase

import com.jio.glass.domain.repository.SignInRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val signInRepository: SignInRepository) {
    fun signIn(email: String, password: String) = signInRepository.signIn(email, password)
}