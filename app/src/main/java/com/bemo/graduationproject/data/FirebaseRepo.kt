package com.bemo.graduationproject.data

import com.bemo.graduationproject.Classes.Attendance
import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.Room.Entities.*
import com.bemo.graduationproject.di.FireStoreTable
import com.example.uni.data.Resource

interface FirebaseRepo {
    suspend fun getPosts(result :(Resource<List<Posts>>) ->Unit)
    suspend fun addPosts(posts: Posts,result :(Resource<String>) ->Unit)
    suspend fun updatePosts(posts: Posts,result :(Resource<String>) ->Unit)
    suspend fun updateSection(section: Section,result :(Resource<String>) ->Unit)
    suspend fun updateLecture(lecture: Lecture,result :(Resource<String>) ->Unit)
    suspend fun updateAssistant(assistant: Assistant, courseId:String, result: (Resource<String>) -> Unit)
    suspend fun updateCourse(courses: Courses,professor: Professor,assistant: Assistant, result: (Resource<String>) -> Unit)
    suspend fun getAssistant(courses: List<Courses>, result: (Resource<List<Assistant>>) -> Unit)
    suspend fun getProfessor(courses: List<Courses>, result: (Resource<List<Professor>>) -> Unit)
    suspend fun getSection(courses: List<Courses>, result: (Resource<List<Section>>) -> Unit)
    suspend fun getLectures(courses: List<Courses>, result: (Resource<List<Lecture>>) -> Unit)
    suspend fun getCourseByAssistantCode( assistantCode:String,result: (Resource<List<Courses>>) -> Unit)
    suspend fun  getCourseByProfessorCode( professorCode:String,result: (Resource<List<Courses>>) -> Unit)
    suspend fun updateProfessor(professor: Professor,courseId:String, result: (Resource<String>) -> Unit)
    suspend fun getCourse( grade: String,result: (Resource<List<Courses>>) -> Unit)
    suspend fun deletePosts(posts: Posts,result :(Resource<String>) ->Unit)
    suspend fun deletePermission(permission: Permission, result :(Resource<String>) ->Unit)
    suspend fun getData(grade:String,result :(Resource<List<Courses>>) ->Unit)
    suspend fun addPermission(permission: Permission,result :(Resource<String>) ->Unit)



     suspend fun updateSectionAttendance(attendance: Attendance, sectionId: String, result: (Resource<String>) -> Unit)
     suspend fun updateLectureAttendance(attendance: Attendance, lectureId: String, result: (Resource<String>) -> Unit)
     suspend fun getLectureAttendance( lectureId: String,result: (Resource<List<Attendance>>) -> Unit)
     suspend fun getSectionAttendance( sectionId: String,result: (Resource<List<Attendance>>) -> Unit)


}