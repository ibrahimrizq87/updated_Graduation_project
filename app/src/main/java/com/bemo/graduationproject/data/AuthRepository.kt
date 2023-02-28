package com.example.uni.data

import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.user.*
import com.bemo.graduationproject.Room.Entities.Assistant
import com.bemo.graduationproject.Room.Entities.Professor
import com.google.firebase.auth.FirebaseUser

interface AuthRepository {
    val currentUser:FirebaseUser?
    suspend fun updateUserInfo(userStudent: Users, result:(Resource<String>) ->Unit)
    suspend fun logIn(email:String, password:String, userStudent: UserStudent, result:(Resource<String>) -> Unit)
//suspend fun signUp(name:String,email:String ,password:String):Resource<FirebaseUser>
    suspend fun register(email:String, password:String, userStudent: Users, result:(Resource<String>) -> Unit)
    suspend fun logOut(result:()->Unit)
    fun storeSession(id :String,userType:String,result :(Users?)-> Unit)
    suspend fun getUserStudent(id :String,result:(Resource<UserStudent?>) -> Unit)

    suspend fun getUserProfessor(id :String,result:(Resource<UserProfessor?>) -> Unit)
    suspend fun getUserAssistant(id :String,result:(Resource<UserAssistant?>) -> Unit)
    suspend fun getUserAdmin(id :String,result:(Resource<UserAdmin?>) -> Unit)


    fun getSessionAssistant(result :(UserAssistant?)-> Unit)
    fun getSessionStudent(result :(UserStudent?)-> Unit)
    fun getSessionAdmin(result :(UserAdmin?)-> Unit)
    fun getSessionProfessor(result :(UserProfessor?)-> Unit)
    fun setSession(user:Users)
    fun setUserType(userType:String)
    fun getUserType():String?

    suspend fun addPermission(permission: Permission, result :(Resource<String>) ->Unit)


}