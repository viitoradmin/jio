package com.jio.glass.di

import com.jio.glass.data.SignInRepositoryImpl
import com.jio.glass.domain.repository.SignInRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class DomainModule {

    @Binds
    abstract fun bindSignInRepository(signInRepositoryImpl: SignInRepositoryImpl): SignInRepository

}