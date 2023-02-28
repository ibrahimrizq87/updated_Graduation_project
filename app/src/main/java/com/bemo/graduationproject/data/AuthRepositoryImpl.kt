package com.bemo.graduationproject.data

import android.content.SharedPreferences
import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.user.*
import com.bemo.graduationproject.di.PermissionsRequired
import com.bemo.graduationproject.di.SharedPreferencesTable
import com.bemo.graduationproject.di.UserTypes
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

    override suspend fun updateUserInfo(userStudent: Users, result: (Resource<String>) ->Unit ) {
        val type =userStudent.userType
        val document=database.collection(UserTypes.user).document(type).collection(UserTypes.user).document(userStudent.userId)
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
        userStudent: Users,
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
                                    setUserType(userStudent.userType)
                                storeSession(it.result.user?.uid?:"",userStudent.userType){
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
                        //val permission=Permission(PermissionsRequired.sing_in_permission, userStudent.userId,"")


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
        appPreferences.edit().putString(SharedPreferencesTable.userType,null).apply()
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
    override fun setSession(user:Users) {
        appPreferences.edit().putString(SharedPreferencesTable.user_session,gson.toJson(user)).apply()
    }

    override fun getUserType(): String? {
        val user_str = appPreferences.getString(SharedPreferencesTable.userType,null)
        if (user_str==null){
            return null
        }
        return user_str

    }
    override fun setUserType(userType:String) {
        appPreferences.edit().putString(SharedPreferencesTable.userType,userType).apply()
    }
    override fun storeSession(id :String,userType:String,result :(Users?)-> Unit){
        database.collection(UserTypes.user).document(userType).collection(UserTypes.user).document(id)
            .get()
            .addOnCompleteListener {
                if (it.isSuccessful){
                 when(userType){
                        UserTypes.studentUser->{
                            val userStudent= it.result.toObject(UserStudent::class.java)
                            if (userStudent != null) {
                                setSession(userStudent)
                            }
                            result.invoke(userStudent)
                        }
                        UserTypes.adminUser->{
                            val userStudent= it.result.toObject(UserStudent::class.java)
                            if (userStudent != null) {
                                setSession(userStudent)
                            }
                            result.invoke(userStudent)
                        }
                        UserTypes.assistantUser->{
                            val userStudent= it.result.toObject(UserStudent::class.java)
                            if (userStudent != null) {
                                setSession(userStudent)
                            }
                            result.invoke(userStudent)
                        }
                        UserTypes.professorUser->{
                            val userStudent= it.result.toObject(UserStudent::class.java)
                            if (userStudent != null) {
                                setSession(userStudent)
                            }
                            result.invoke(userStudent)
                        }
                    }



                }else{
                    result.invoke(null)
                }
            }
            .addOnFailureListener {
                result.invoke(null)
            }
    }

    override fun getSessionAssistant(result: (UserAssistant?) -> Unit) {
        val user_str = appPreferences.getString(SharedPreferencesTable.user_session,null)
        if (user_str==null){
            result.invoke(null)
        }else{
            val userStudent = gson.fromJson(user_str, UserAssistant::class.java)
            result.invoke(userStudent)
        }
    }
    override fun getSessionAdmin(result: (UserAdmin?) -> Unit) {
        val user_str = appPreferences.getString(SharedPreferencesTable.user_session,null)
        if (user_str==null){
            result.invoke(null)
        }else{
            val userStudent = gson.fromJson(user_str, UserAdmin::class.java)
            result.invoke(userStudent)
        }
    }
    override fun getSessionProfessor(result: (UserProfessor?) -> Unit) {
        val user_str = appPreferences.getString(SharedPreferencesTable.user_session,null)
        if (user_str==null){
            result.invoke(null)
        }else{
            val userStudent = gson.fromJson(user_str, UserProfessor::class.java)
            result.invoke(userStudent)
        }
    }
    override fun getSessionStudent(result: (UserStudent?) -> Unit) {
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
    override  suspend fun getUserStudent(id :String,result:(Resource<UserStudent?>) -> Unit) {

        val docRef =  database.collection(UserTypes.user).document(UserTypes.studentUser).collection(UserTypes.user).document(id)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                result.invoke(Resource.Failure(e.toString()))
                return@addSnapshotListener
            }
            result.invoke(Resource.Success(snapshot?.toObject(UserStudent::class.java)))
        }
    }
    override  suspend fun getUserProfessor(id :String,result:(Resource<UserProfessor?>) -> Unit) {

        val docRef =  database.collection(UserTypes.user).document(UserTypes.professorUser).collection(UserTypes.user).document(id)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                result.invoke(Resource.Failure(e.toString()))
                return@addSnapshotListener
            }
            result.invoke(Resource.Success(snapshot?.toObject(UserProfessor::class.java)))
        }
    }
    override  suspend fun getUserAssistant(id :String,result:(Resource<UserAssistant?>) -> Unit) {

        val docRef =  database.collection(UserTypes.user).document(UserTypes.assistantUser).collection(UserTypes.user).document(id)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                result.invoke(Resource.Failure(e.toString()))
                return@addSnapshotListener
            }
            result.invoke(Resource.Success(snapshot?.toObject(UserAssistant::class.java)))
        }
    }
    override  suspend fun getUserAdmin(id :String,result:(Resource<UserAdmin?>) -> Unit) {

        val docRef =  database.collection(UserTypes.user).document(UserTypes.adminUser).collection(UserTypes.user).document(id)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                result.invoke(Resource.Failure(e.toString()))
                return@addSnapshotListener
            }
            result.invoke(Resource.Success(snapshot?.toObject(UserAdmin::class.java)))
        }
    }
}