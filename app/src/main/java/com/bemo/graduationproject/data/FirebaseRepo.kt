package com.bemo.graduationproject.data

import com.bemo.graduationproject.Classes.Posts
import com.example.uni.data.Resource

interface FirebaseRepo {
    fun getPosts(result :(Resource<List<Posts>>) ->Unit)
    fun addPosts(posts: Posts,result :(Resource<String>) ->Unit)
    fun updatePosts(posts: Posts,result :(Resource<String>) ->Unit)
    fun deletePosts(posts: Posts,result :(Resource<String>) ->Unit)
}