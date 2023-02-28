package com.bemo.graduationproject.data

import android.util.Log
import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.Classes.user.Users
import com.bemo.graduationproject.Room.Entities.*
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


    override suspend fun updateCourse(courses: Courses,professor: Professor,assistant: Assistant, result: (Resource<String>) -> Unit) {
        var str=""
        val document=database.collection(FireStoreTable.courses).document(courses.courseCode)
        document.set(courses)
            .addOnSuccessListener {
                GlobalScope.launch {
                    str += "course data add, "
                    updateProfessor(professor,courses.courseCode){
                        str+=it
                    }
                    updateAssistant(assistant,courses.courseCode){
                        str+=it
                    }
                    }

                result.invoke(
                    Resource.Success(str)
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
    override suspend fun updateProfessor(professor: Professor,courseId:String, result: (Resource<String>) -> Unit) {
        val document=database.collection(FireStoreTable.courses).document(courseId)
            .collection(FireStoreTable.professor).document(professor.code)
        document.set(professor)
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("professor added, ")
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
    override suspend fun updateAssistant(assistant: Assistant,courseId:String, result: (Resource<String>) -> Unit) {
        val document=database.collection(FireStoreTable.courses).document(courseId)
            .collection(FireStoreTable.assistant).document(assistant.code)
        document.set(assistant)
            .addOnSuccessListener {
                result.invoke(
                    Resource.Success("assistant added, ")
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
    override suspend fun updateLecture(lecture: Lecture, result: (Resource<String>) -> Unit) {
        val document=database.collection(FireStoreTable.courses).document(lecture.courseCode)
            .collection(FireStoreTable.lectures).document()
        document.set(lecture)
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
    override suspend fun getCourse( grade:String,result: (Resource<List<Courses>>) -> Unit) {
        val docRef = database.collection(FireStoreTable.courses)
            .whereEqualTo("grade", grade)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                result.invoke(Resource.Failure(e.toString()))
                return@addSnapshotListener
            }

            val listOfPosts= arrayListOf<Courses>()
            for (rec in snapshot!!){
                val post = rec.toObject(Courses::class.java)
                listOfPosts.add(post)
            }
            result.invoke(Resource.Success(listOfPosts))
        }
    }
    override suspend fun getLectures(courses: List<Courses>, result: (Resource<List<Lecture>>) -> Unit) {
        val listOfPosts= arrayListOf<Lecture>()
        for(course in courses){
        val docRef = database.collection(FireStoreTable.courses).document(course.courseCode)
            .collection(FireStoreTable.lectures)
        docRef.addSnapshotListener { snapshot, e ->
            if (e != null) {
                result.invoke(Resource.Failure(e.toString()))
                return@addSnapshotListener
            }
            for (rec in snapshot!!){
                val post = rec.toObject(Lecture::class.java)
                listOfPosts.add(post)
            }

        }
    }
        result.invoke(Resource.Success(listOfPosts))
    }

    override suspend fun getSection(courses: List<Courses>, result: (Resource<List<Section>>) -> Unit) {
        val listOfPosts= arrayListOf<Section>()
        for(course in courses){
            val docRef = database.collection(FireStoreTable.courses).document(course.courseCode)
                .collection(FireStoreTable.sections)
            docRef.addSnapshotListener { snapshot, e ->
                if (e != null) {
                    result.invoke(Resource.Failure(e.toString()))
                    return@addSnapshotListener
                }
                for (rec in snapshot!!){
                    val post = rec.toObject(Section::class.java)
                    listOfPosts.add(post)
                }

            }
        }
        result.invoke(Resource.Success(listOfPosts))
    }
    override suspend fun getProfessor(courses: List<Courses>, result: (Resource<List<Professor>>) -> Unit) {
        val listOfPosts= arrayListOf<Professor>()
        for(course in courses){
            val docRef = database.collection(FireStoreTable.courses).document(course.courseCode)
                .collection(FireStoreTable.professor)
            docRef.addSnapshotListener { snapshot, e ->
                if (e != null) {
                    result.invoke(Resource.Failure(e.toString()))
                    return@addSnapshotListener
                }
                for (rec in snapshot!!){
                    val post = rec.toObject(Professor::class.java)
                    listOfPosts.add(post)
                }

            }
        }
        result.invoke(Resource.Success(listOfPosts))
    }
    override suspend fun getAssistant(courses: List<Courses>, result: (Resource<List<Assistant>>) -> Unit) {
        val listOfPosts= arrayListOf<Assistant>()
        for(course in courses){
            val docRef = database.collection(FireStoreTable.courses).document(course.courseCode)
                .collection(FireStoreTable.assistant)
            docRef.addSnapshotListener { snapshot, e ->
                if (e != null) {
                    result.invoke(Resource.Failure(e.toString()))
                    return@addSnapshotListener
                }
                for (rec in snapshot!!){
                    val post = rec.toObject(Assistant::class.java)
                    listOfPosts.add(post)
                }

            }
        }
        result.invoke(Resource.Success(listOfPosts))
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