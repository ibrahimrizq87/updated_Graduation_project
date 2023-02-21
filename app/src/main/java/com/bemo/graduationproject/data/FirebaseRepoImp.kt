package com.bemo.graduationproject.data

import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.di.FireStoreTable
import com.bemo.graduationproject.di.PermissionsRequired
import com.example.uni.data.Resource
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.toObject
import java.util.*

class FirebaseRepoImp(
    val database:FirebaseFirestore
):FirebaseRepo{
    override  suspend fun getPosts(result:(Resource<List<Posts>>) -> Unit) {
  database.collection(FireStoreTable.post)
      .get()
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
    /*val data = arrayListOf(
       Posts(
           description = "test1"
       , authorName = "name1"
       , time = Date()
       ),
       Posts(
           description = "test2"
           , authorName = "name2"
           , time = Date()
       ),
       Posts(
           description = "test3"
           , authorName = "name3"
           , time = Date()
       )
   )
if (data.isNullOrEmpty()){
    return Resource.Failure("there is no post data")
}else{
    return Resource.Success(data)
}
*/
}

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