package com.bemo.graduationproject.data.di

import android.content.SharedPreferences
import com.bemo.graduationproject.Room.Repository
import com.bemo.graduationproject.data.*
import com.example.uni.data.AuthRepository
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
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
         database:FirebaseFirestore,
         //roomRepository:Repository
    ):FirebaseRepo {
        return FirebaseRepoImp(database)//,roomRepository)
    }



    @Provides
    @Singleton
    fun provideAuthRepo(
        database:FirebaseFirestore,
        auth:FirebaseAuth,
        appPreferences: SharedPreferences,
        gson: Gson
    ):AuthRepository {
        return AuthRepositoryImpl(auth,database,appPreferences,gson)
    }


    @Provides
    @Singleton
    fun provideRealtimeRepo(
        database: DatabaseReference,
    ):FirebaseRealtimeRepo {
        return FirebaseRealtimeRepoImp(database)
    }
}