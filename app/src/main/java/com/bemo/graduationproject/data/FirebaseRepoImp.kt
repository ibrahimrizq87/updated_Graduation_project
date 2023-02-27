package com.bemo.graduationproject.data

import android.util.Log
import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.Room.Entities.Courses
import com.bemo.graduationproject.Room.Entities.Professor
import com.bemo.graduationproject.Room.Entities.Section
import com.bemo.graduationproject.Room.Repository
import com.bemo.graduationproject.di.FireStoreTable
import com.bemo.graduationproject.di.PermissionsRequired
import com.example.uni.data.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class FirebaseRepoImp@Inject constructor(
    val database:FirebaseFirestore
    //val roomRepository: Repository
):FirebaseRepo{
    override  suspend fun getPosts(result:(Resource<List<Posts>>) -> Unit) {
  database.collection(FireStoreTable.post)
        val docRef = database.collection(FireStoreTable.post)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
result.invoke(Resource.Failure(e.toString()))
                return@addSnapshotListener
            }

            val listOfPosts= arrayListOf<Posts>()
            for (rec in snapshot!!){
                val post = rec.toObject(Posts::class.java)
                listOfPosts.add(post)
}
            result.invoke(Resource.Success(listOfPosts))
        }

    /*  .get()
      .addOnSuccessListener {
val listOfPosts= arrayListOf<Posts>()
          for (document in it){
val post = document.toObject(Posts::class.java)
              listOfPosts.add(post)
          }
          result.invoke(
              Resource.Success(listOfPosts)
          )
      }
      .addOnFailureListener {
          result.invoke(
              Resource.Failure(
                  it.localizedMessage
              )
          )

      }

*/}

    override suspend fun addPosts(posts: Posts, result: (Resource<String>) -> Unit) {
val document=database.collection(FireStoreTable.post).document()
posts.postID=document.id
        document.set(posts)
    .addOnSuccessListener {
result.invoke(
    Resource.Success("post added successfully")
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

    override suspend fun updatePosts(posts: Posts, result: (Resource<String>) -> Unit) {
        val document=database.collection(FireStoreTable.post).document(posts.postID)
      document.set(posts)
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("post updated successfully")
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



    override suspend fun deletePosts(posts: Posts, result: (Resource<String>) -> Unit) {
        val document=database.collection(FireStoreTable.post).document(posts.postID)
        document.delete()
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("post deleted successfully")
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

    override suspend fun deletePermission(permission: Permission, result: (Resource<String>) -> Unit) {
        val document=database.collection(PermissionsRequired.sing_in_permission).document(permission.permissionId)
        document.delete()
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("done")
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

    override fun updateCoursesDoc(grade:String) {
        GlobalScope.launch {
            getData(grade){

                // get courses data and then get the professor data by doctor id
                // when saving the doctor data save it by doctor id

            }

        }
        }
    override suspend fun updateCourse(courses: Courses, result: (Resource<String>) -> Unit) {
        val document=database.collection(FireStoreTable.courses).document(courses.courseCode)
        document.set(courses)
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("courses added successfully")
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

    override suspend fun updateSection(section: Section, result: (Resource<String>) -> Unit) {
        val document=database.collection(FireStoreTable.courses).document(section.courseCode)
            .collection(FireStoreTable.sections).document()
        document.set(section)
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("sections added successfully")
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

    override suspend fun updateLecture(section: Section, result: (Resource<String>) -> Unit) {
        val document=database.collection(FireStoreTable.courses).document(section.courseCode)
            .collection(FireStoreTable.lectures).document()
        document.set(section)
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("lectures added successfully")
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
    override suspend fun getCourse(courses: Courses, result: (Resource<List<Courses>>) -> Unit) {

    }
    override suspend fun getData(grade:String , result: (Resource<List<Courses>>) -> Unit) {
        database.collection(FireStoreTable.courses)
            .get()
            .addOnSuccessListener {
                val listOfPosts= arrayListOf<Courses>()
                for (document in it){
                    val rec = document.toObject(Courses::class.java)
        //GlobalScope.launch { roomRepository.addProfessor(rec) }
if (rec.grade==grade){
    listOfPosts.add(rec)
}


                }
                result.invoke(
                    Resource.Success(listOfPosts)
                )
            }
            .addOnFailureListener {
                result.invoke(
                    Resource.Failure(
                        it.localizedMessage
                    )
                )

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
// save course by course code
// loop for courses