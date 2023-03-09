package com.bemo.graduationproject.data

import android.net.Uri
import com.example.uni.data.Resource

interface FireStorageRepo {
    suspend  fun downloadUri(userId:String ,result: (Resource<Uri>) -> Unit)
    suspend fun uploadImage( imageUri: Uri, userId:String, result: (Resource<String>) -> Unit)
}