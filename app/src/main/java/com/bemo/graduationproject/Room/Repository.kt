package com.bemo.graduationproject.Room

import androidx.lifecycle.LiveData
import com.bemo.graduationproject.Room.Entities.*


class Repository(private val databaseDao: DatabaseDao) {


    val readAllLectures : LiveData<List<Lecture>> = databaseDao.getAllLectures()
    val readAllCourses : LiveData<List<Courses>> = databaseDao.getAllCourses()
    val readAllSections : LiveData<List<Section>> = databaseDao.getAllSections()
    val readAllProfessors : LiveData<List<Professor>> = databaseDao.getAllProfessors()
    val readAllAssistants : LiveData<List<Assistant>> = databaseDao.getAllAssistants()



       suspend fun addLecture(lecture: Lecture){
           databaseDao.insertLecture(lecture)
       }
       suspend fun addSection(section: Section){
           databaseDao.insertSection(section)
       }
       suspend fun addProfessor(professor: Professor){
           databaseDao.insertProfessor(professor)
       }

    suspend fun addAssistant(assistant: Assistant){
        databaseDao.insertAssistant(assistant)
    }

    suspend fun addCourse(courses: Courses){
        databaseDao.insertCourse(courses)
    }


    suspend fun addHall(hall: Hall){
        databaseDao.insertHall(hall)
    }

    suspend fun addLap(lap: Lap){
        databaseDao.insertLap(lap)
    }

/*
   fun findStationsByName (stationName:String): LiveData<List<Station>> {
   return databaseDao.findStationByName(stationName)
   }
   fun findTrainByNumber (number:String): LiveData<List<Train>> {
       return databaseDao.findByNumber(number)
   }
   fun findStopsByTrainNumber (number:String): LiveData<List<Stops>> {
       return databaseDao.findStopsByTrainNumber(number)
   }
   fun findStopsByStationName (station:String): LiveData<List<Stops>> {
       return databaseDao.findStopsByStationName(station)
   }
*/
   }