package com.bemo.graduationproject.data

import com.bemo.graduationproject.Classes.CommentClass
import com.bemo.graduationproject.Classes.DataClass
import com.bemo.graduationproject.Classes.Posts
import com.example.uni.data.Resource

interface FirebaseRealtimeRepo {
    suspend fun getData(result :(Resource<List<DataClass>>) ->Unit)
    suspend fun addData(data: DataClass, result :(Resource<String>) ->Unit)


}