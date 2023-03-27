package com.priyanka.studentbeans_tecttest.authorisation.di

import com.google.firebase.auth.FirebaseAuth
import com.priyanka.studentbeans_tecttest.authorisation.data.repository.AuthRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesFirebaseAuth() = FirebaseAuth.getInstance()

    @Provides
    @Singleton
    fun providesRepoImpl(): AuthRepoImpl {
        return AuthRepoImpl()
    }

}
