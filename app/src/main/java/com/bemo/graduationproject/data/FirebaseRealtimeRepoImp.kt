package com.bemo.graduationproject.data
import com.bemo.graduationproject.Classes.DataClass
import com.bemo.graduationproject.di.FireStoreTable
import com.example.uni.data.Resource
import com.google.firebase.database.DatabaseReference

class FirebaseRealtimeRepoImp (
    val database: DatabaseReference
        ):FirebaseRealtimeRepo{
    override fun getData(result: (Resource<List<DataClass>>) -> Unit) {
        database.child(FireStoreTable.dataCons)
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

    override fun addData(data: DataClass, result: (Resource<String>) -> Unit) {
   database.child(FireStoreTable.dataCons).setValue(data)
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