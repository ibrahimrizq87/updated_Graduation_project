package com.bemo.graduationproject.data

import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.Posts
import com.example.uni.data.Resource

interface FirebaseRepo {
    suspend fun getPosts(result :(Resource<List<Posts>>) ->Unit)
    suspend fun addPosts(posts: Posts,result :(Resource<String>) ->Unit)
    suspend fun updatePosts(posts: Posts,result :(Resource<String>) ->Unit)
    suspend fun deletePosts(posts: Posts,result :(Resource<String>) ->Unit)
    suspend fun deletePermission(permission: Permission, result :(Resource<String>) ->Unit)
    suspend fun addPermission(permission: Permission,result :(Resource<String>) ->Unit)

}