package com.bemo.graduationproject.Room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.bemo.graduationproject.Room.Entities.*

@Dao
interface DatabaseDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAssistant(assistant: Assistant)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCourse(courses: Courses)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertHall(hall: Hall)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLap(lap: Lap)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertLecture(lecture: Lecture)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertProfessor(professor: Professor)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSection(section: Section)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertStudent(student: Student)




    @Query("SELECT * FROM Professor")
    fun getAllProfessors(): LiveData<List<Professor>>


    @Query("SELECT * FROM Assistant")
    fun getAllAssistants():LiveData<List<Assistant>>

    @Query("SELECT * FROM Courses")
    fun getAllCourses():LiveData<List<Courses>>


    @Query("SELECT * FROM Lecture")
    fun getAllLectures():LiveData<List<Lecture>>


    @Query("SELECT * FROM Section")
    fun getAllSections():LiveData<List<Section>>


    @Query("SELECT * FROM Lap")
    fun getAllLaps():LiveData<List<Lap>>


    @Query("SELECT * FROM Hall")
    fun getAllHalls():LiveData<List<Hall>>







}