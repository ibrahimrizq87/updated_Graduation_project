package com.bemo.graduationproject.Room

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.bemo.graduationproject.Room.Entities.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RoomViewModel(application: Application):AndroidViewModel(application) {
    val readAllLecture:LiveData<List<Lecture>>
    val readAllSection:LiveData<List<Section>>

    val readAllProfessor:LiveData<List<Professor>>
    val readAllAssistant:LiveData<List<Assistant>>

    val readAllCourse:LiveData<List<Courses>>

    private  val repository: Repository
    init {
        val databaseDao= AppDatabase.getInstance(application).databaseDao()
        repository = Repository(databaseDao)

        readAllLecture = repository.readAllLectures
        readAllSection = repository.readAllSections

        readAllProfessor = repository.readAllProfessors
        readAllAssistant = repository.readAllAssistants

        readAllCourse = repository.readAllCourses
    }

    fun addLecture(lecture: Lecture){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLecture(lecture)
        }
    }

    fun addSection(section: Section){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addSection(section)
        }
    }
    fun addProfessor(professor: Professor){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addProfessor(professor)
        }
    }
    fun addAssistant(assistant: Assistant){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addAssistant(assistant)
        }
    }

    fun addCourse(courses: Courses){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addCourse(courses)
        }
    }
    fun addHall(hall: Hall){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addHall(hall)
        }
    }

    fun addLap(lap: Lap){
        viewModelScope.launch(Dispatchers.IO) {
            repository.addLap(lap)
        }
    }
    /*
    fun getStationsByName(name:String):LiveData<List<Station>>{
    return repository.findStationsByName(name)
    }

    fun getTrainByNumber(number:String):LiveData<List<Train>>{
        return repository.findTrainByNumber(number)
    }

    fun getStopsByTrainNumber(number:String):LiveData<List<Stops>>{
        return repository.findStopsByTrainNumber(number)
    }

    fun getStopsByStationName(station:String):LiveData<List<Stops>>{
        return repository.findStopsByStationName(station)
    }
*/

}