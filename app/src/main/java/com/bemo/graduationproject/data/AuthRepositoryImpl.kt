package com.bemo.graduationproject.data

import com.bemo.graduationproject.Classes.User
import com.bemo.graduationproject.data.utils.await
import com.bemo.graduationproject.di.FireStoreTable
import com.example.uni.data.AuthRepository
import com.example.uni.data.Resource
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthRepositoryImpl@Inject constructor(
     val firebaseAuth: FirebaseAuth,
     val database:FirebaseFirestore
) : AuthRepository {
    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override suspend fun updateUserInfo(user: User, result: (Resource<String>) ->Unit ) {
val document=database.collection(FireStoreTable.users).document()
        user.userId=document.id
        document.set(user)
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
        user: User,
        result: (Resource<String>) -> Unit
    ) {
     }

    override suspend fun Register(
        email: String,
        password: String,
        user: User,
        result: (Resource<String>) -> Unit
    ) {
        firebaseAuth.createUserWithEmailAndPassword(email,password)
            .addOnCompleteListener{
                if (it.isSuccessful){

                    GlobalScope.launch {

                        updateUserInfo(user){state->

                            when(state){

                                is Resource.Success->{result.invoke(

                                    Resource.Success("user created successfully")

                                )}

                                is Resource.Failure->{result.invoke(Resource.Failure(state.exception))}

                            }

                        }

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
}