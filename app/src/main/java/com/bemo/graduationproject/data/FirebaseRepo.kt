package com.bemo.graduationproject.data

import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.Room.Entities.Courses
import com.bemo.graduationproject.Room.Entities.Professor
import com.bemo.graduationproject.Room.Entities.Section
import com.example.uni.data.Resource

interface FirebaseRepo {
    suspend fun getPosts(result :(Resource<List<Posts>>) ->Unit)
    suspend fun addPosts(posts: Posts,result :(Resource<String>) ->Unit)
    suspend fun updatePosts(posts: Posts,result :(Resource<String>) ->Unit)
    suspend fun updateCourse(courses: Courses,result :(Resource<String>) ->Unit)
    suspend fun updateSection(section: Section,result :(Resource<String>) ->Unit)
    suspend fun updateLecture(section: Section,result :(Resource<String>) ->Unit)

    suspend fun getCourse(courses: Courses,result :(Resource<List<Courses>>) ->Unit)
    suspend fun deletePosts(posts: Posts,result :(Resource<String>) ->Unit)
    suspend fun deletePermission(permission: Permission, result :(Resource<String>) ->Unit)
    fun updateCoursesDoc(grade:String)
    suspend fun getData(grade:String,result :(Resource<List<Courses>>) ->Unit)

    suspend fun addPermission(permission: Permission,result :(Resource<String>) ->Unit)

}