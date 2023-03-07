package com.bemo.graduationproject.data

import com.bemo.graduationproject.Classes.*
import com.example.uni.data.Resource

interface FirebaseRealtimeRepo {
    suspend fun getData(result :(Resource<List<DataClass>>) ->Unit)
    suspend fun getAttendWithCode(embeddedId:String,scannedCode:Int,result :(Resource<Boolean>) ->Unit)
    suspend fun addData(data: DataClass, result :(Resource<String>) ->Unit)
    suspend fun updateHallState(embedded: AttendanceEmbedded, result :(Resource<String>) ->Unit)
    suspend fun updateLabState(embedded: AttendanceEmbedded, result :(Resource<String>) ->Unit)


}