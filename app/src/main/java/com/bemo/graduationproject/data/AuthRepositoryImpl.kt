package com.bemo.graduationproject.data

import android.content.SharedPreferences
import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.user.UserStudent
import com.bemo.graduationproject.di.FireStoreTable
import com.bemo.graduationproject.di.PermissionsRequired
import com.bemo.graduationproject.di.SharedPreferencesTable
import com.example.uni.data.AuthRepository
import com.example.uni.data.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthRepositoryImpl@Inject constructor(
     val firebaseAuth: FirebaseAuth,
     val database:FirebaseFirestore,
     val appPreferences: SharedPreferences,
     val gson:Gson
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun updateUserInfo(userStudent: UserStudent, result: (Resource<String>) ->Unit ) {
val document=database.collection(FireStoreTable.users).document(userStudent.userId)

        document.set(userStudent)
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("user date updated successfully")
                )
            }
            .addOnFailureListener{
                result.invoke(
                    Resource.Failure(
                        it.localizedMessage
                    )
                )
            }
    }

    override suspend fun logIn(
        email: String,
        password: String,
        userStudent: UserStudent,
        result: (Resource<String>) -> Unit
    ) {
     }
    override suspend fun register(
        email: String,
        password: String,
        userStudent: UserStudent,
        result: (Resource<String>) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if (it.isSuccessful){

                    GlobalScope.launch {
                        userStudent.userId=it.result.user?.uid?:""
                        updateUserInfo(userStudent){ state->
                            when(state){
                                is Resource.Success->{
                                storeSession(it.result.user?.uid?:""){
                                    if (userStudent==null){
                                        result.invoke(Resource.Failure("user created successfully but session did not stored"))
                                    }else{
                                        result.invoke(Resource.Success("user created successfully"))
                                    }
                                }
                                }
                                is Resource.Failure->{result.invoke(Resource.Failure(state.exception))}
                            }
                        }
                        val permission=Permission(PermissionsRequired.sing_in_permission, userStudent.userId,"")


                    }
                }else{
                    result.invoke(
                        Resource.Failure(
                            it.exception.toString()
                        )
                    )
                }
            }
            .addOnFailureListener {
                result.invoke(
                    Resource.Failure(
                        it.localizedMessage
                    )
                )
            }

    }

    override suspend fun logOut(result: () -> Unit) {
        firebaseAuth.signOut()
        result.invoke()
        appPreferences.edit().putString(SharedPreferencesTable.user_session,null).apply()
    }


    /* override suspend fun logIn(email: String, password: String): Resource<FirebaseUser> {
         return try {
             val  result =firebaseAuth.signInWithEmailAndPassword(email, password).await()
         Resource.Success(result.user!!)
         }
         catch (e:Exception){
             e.printStackTrace()
             Resource.Failure(e.toString())
         }

     }

     override suspend fun signUp(
         name: String,
         email: String,
         password: String
     ): Resource<FirebaseUser> {
         return try {
             val  result =firebaseAuth.createUserWithEmailAndPassword(email, password).await()
             result?.user?.updateProfile(UserProfileChangeRequest.Builder().setDisplayName(name).build())?.await()
             Resource.Success(result.user!!)
         }
         catch (e:Exception){
             e.printStackTrace()
             Resource.Failure(e.toString())
         }
     }

     override fun logOut() {
         firebaseAuth.signOut()
     }*/
    override fun storeSession(id :String,result :(UserStudent?)-> Unit){
        database.collection(FireStoreTable.users).document(id)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                    val userStudent= it.result.toObject(UserStudent::class.java)
                    appPreferences.edit().putString(SharedPreferencesTable.user_session,gson.toJson(userStudent)).apply()
                    result.invoke(userStudent)
                }else{
                    result.invoke(null)
                }
            }
            .addOnFailureListener {
                result.invoke(null)
            }
    }

    override fun getSession(result: (UserStudent?) -> Unit) {
        val user_str = appPreferences.getString(SharedPreferencesTable.user_session,null)
        if (user_str==null){
            result.invoke(null)
        }else{
            val userStudent = gson.fromJson(user_str, UserStudent::class.java)
            result.invoke(userStudent)
        }
    }
    override suspend fun addPermission(permission: Permission, result: (Resource<String>) -> Unit) {
        val document=database.collection(PermissionsRequired.sing_in_permission).document()
        permission.permissionId=document.id
        document.set(permission)
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("asking for permission")
                )
            }
            .addOnFailureListener{
                result.invoke(
                    Resource.Failure(
                        it.localizedMessage
                    )
                )
            }

    }
}