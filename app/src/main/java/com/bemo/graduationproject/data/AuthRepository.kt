package com.example.uni.data

import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.user.UserStudent
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser:FirebaseUser?
    suspend fun updateUserInfo(userStudent: UserStudent, result:(Resource<String>) ->Unit)
    suspend fun logIn(email:String, password:String, userStudent: UserStudent, result:(Resource<String>) -> Unit)
//suspend fun signUp(name:String,email:String ,password:String):Resource<FirebaseUser>
    suspend fun register(email:String, password:String, userStudent: UserStudent, result:(Resource<String>) -> Unit)
    suspend fun logOut(result:()->Unit)
    fun storeSession(id :String,result :(UserStudent?)-> Unit)

    fun getSession(result :(UserStudent?)-> Unit)
    suspend fun addPermission(permission: Permission, result :(Resource<String>) ->Unit)


}