package com.bemo.graduationproject.data
import com.bemo.graduationproject.Classes.AttendanceEmbedded
import com.bemo.graduationproject.Classes.CommentClass
import com.bemo.graduationproject.Classes.DataClass
import com.bemo.graduationproject.Classes.EmbeddedModel
import com.bemo.graduationproject.di.FireStoreTable
import com.bemo.graduationproject.di.RealTime
import com.example.uni.data.Resource
import com.google.firebase.database.DatabaseReference
import javax.inject.Inject

class FirebaseRealtimeRepoImp @Inject constructor(
    val database: DatabaseReference
        ):FirebaseRealtimeRepo{

    override suspend fun getData(result: (Resource<List<DataClass>>) -> Unit) {
        database.child("data")
            .get()
            .addOnSuccessListener {
                val listOfData= arrayListOf<DataClass>()
                for (postSnapshot in it.children){
                    val data = postSnapshot.getValue(DataClass::class.java)
                    listOfData.add(data!!)
                }
                result.invoke(Resource.Success(listOfData))
            }

            .addOnFailureListener {
                result.invoke(Resource.Failure(it.localizedMessage))
            }




    }

    override suspend fun addData(data: DataClass, result: (Resource<String>) -> Unit) {
   database.child("data").setValue(data)
       .addOnSuccessListener {
   result.invoke(
       Resource.Success("data sent successfully")
   )
   }
       .addOnFailureListener {
           result.invoke(
               Resource.Failure(it.localizedMessage)
           )
       }
    }

    override suspend fun updateHallState(
        embedded: AttendanceEmbedded,
        result: (Resource<String>) -> Unit
    ) {
        database.child(RealTime.hall).child(embedded.id).setValue(embedded)
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("data sent successfully")
                )
            }
            .addOnFailureListener {
                result.invoke(
                    Resource.Failure(it.localizedMessage)
                )
            }

    }

    override suspend fun updateLabState(
        embedded: AttendanceEmbedded,
        result: (Resource<String>) -> Unit
    ) {

        database.child(RealTime.lab).child(embedded.id).setValue(embedded)
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("data sent successfully")
                )
            }
            .addOnFailureListener {
                result.invoke(
                    Resource.Failure(it.localizedMessage)
                )
            }

    }
    override  suspend fun getAttendWithCode(embeddedId:String,scannedCode:Int,result :(Resource<Boolean>) ->Unit){
        database.child("embedded").child(embeddedId)
            .get()
            .addOnSuccessListener {
                val data = it.getValue(EmbeddedModel::class.java)
                if (data!!.code==scannedCode){
                    result.invoke(Resource.Success(true))
                }else{
                    result.invoke(Resource.Success(false))
                }

            }

            .addOnFailureListener {
                result.invoke(Resource.Failure(it.localizedMessage))
            }
    }


}