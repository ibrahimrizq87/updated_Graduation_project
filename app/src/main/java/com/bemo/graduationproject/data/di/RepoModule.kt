package com.bemo.graduationproject.data.di

import com.bemo.graduationproject.data.AuthRepositoryImpl
import com.bemo.graduationproject.data.FirebaseRepo
import com.bemo.graduationproject.data.FirebaseRepoImp
import com.example.uni.data.AuthRepository
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object RepoModule {

    @Provides
    @Singleton
    fun provideFirebaseRepo(
         database:FirebaseFirestore
    ):FirebaseRepo {
        return FirebaseRepoImp(database)
    }



    @Provides
    @Singleton
    fun provideAuthRepo(
        database:FirebaseFirestore,
        auth:FirebaseAuth
    ):AuthRepository {
        return AuthRepositoryImpl(auth,database)
    }
}