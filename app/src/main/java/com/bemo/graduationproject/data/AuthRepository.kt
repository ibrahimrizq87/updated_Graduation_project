package com.example.uni.data

import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.User
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser:FirebaseUser?
    suspend fun updateUserInfo(user: User,result:(Resource<String>) ->Unit)
    suspend fun logIn(email:String ,password:String,user: User,result:(Resource<String>) -> Unit)
//suspend fun signUp(name:String,email:String ,password:String):Resource<FirebaseUser>
    suspend fun Register(email:String ,password:String,user: User,result:(Resource<String>) -> Unit)
    suspend fun logOut(result:()->Unit)
    fun storeSession(id :String,result :(User?)-> Unit)

    fun getSession(result :(User?)-> Unit)
    suspend fun addPermission(permission: Permission, result :(Resource<String>) ->Unit)


}