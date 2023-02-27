package com.bemo.graduationproject.data
import com.bemo.graduationproject.Classes.CommentClass
import com.bemo.graduationproject.Classes.DataClass
import com.bemo.graduationproject.di.FireStoreTable
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




}