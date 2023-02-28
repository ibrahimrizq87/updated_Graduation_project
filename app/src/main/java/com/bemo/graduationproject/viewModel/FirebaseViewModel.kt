package com.bemo.graduationproject.viewModel

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.Room.Entities.*
import com.bemo.graduationproject.Room.Repository
import com.bemo.graduationproject.data.FirebaseRepo
import com.example.uni.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    val repository: FirebaseRepo
): ViewModel() {

    private val _post= MutableStateFlow<Resource<List<Posts>>?>(null)
     val post=_post.asStateFlow()



    private val _addPermission= MutableStateFlow<Resource<String>?>(null)
    val addPermission=_addPermission.asStateFlow()

    private val _addPost=MutableStateFlow<Resource<String>?>(null)
    val addPost=_addPost.asStateFlow()


    private val _updatePost=MutableStateFlow<Resource<String>?>(null)
    val updatePost=_updatePost.asStateFlow()

    private val _deletePost=MutableStateFlow<Resource<String>?>(null)
    val deletePost=_deletePost.asStateFlow()


    private val _addCourse= MutableStateFlow<Resource<String>?>(null)
    val addCourse=_addCourse.asStateFlow()

    private val _addSection= MutableStateFlow<Resource<String>?>(null)
    val addSection=_addSection.asStateFlow()


    private val _addLecture= MutableStateFlow<Resource<String>?>(null)
    val addLecture=_addLecture.asStateFlow()

    private val _addProfessor= MutableStateFlow<Resource<String>?>(null)
    val addProfessor=_addProfessor.asStateFlow()
    private val _addAssistant= MutableStateFlow<Resource<String>?>(null)
    val addAssistant=_addAssistant.asStateFlow()

    private val _getCourses= MutableStateFlow<Resource<List<Courses>>?>(null)
    val getCourses=_getCourses.asStateFlow()

    private val _getProfessor= MutableStateFlow<Resource<List<Professor>>?>(null)
    val getProfessor=_getProfessor.asStateFlow()
    private val _getAssistant= MutableStateFlow<Resource<List<Assistant>>?>(null)
    val getAssistant=_getAssistant.asStateFlow()
    private val _getSection= MutableStateFlow<Resource<List<Section>>?>(null)
    val getSection=_getSection.asStateFlow()
    private val _getLecture= MutableStateFlow<Resource<List<Lecture>>?>(null)
    val getLecture=_getLecture.asStateFlow()


    fun getPosts()= viewModelScope.launch{
        _post.value=Resource.Loading
        repository.getPosts {
        _post.value=it
    }}
    fun addPostF (post:Posts)= viewModelScope.launch{
        _addPost.value=Resource.Loading
        repository.addPosts(post){
            _addPost.value=it
        }
    }
    fun addCourse (course: Courses,professor: Professor,assistant: Assistant)= viewModelScope.launch{
        _addCourse.value=Resource.Loading
        repository.updateCourse(course,professor,assistant){
            _addCourse.value=it
        }
    }

    fun addSection (section: Section)= viewModelScope.launch{
        _addSection.value=Resource.Loading
        repository.updateSection(section){
            _addSection.value=it
        }
    }

    fun addLecture (lecture: Lecture)= viewModelScope.launch{
        _addLecture.value=Resource.Loading
        repository.updateLecture(lecture){
            _addLecture.value=it
        }
    }
    fun getCourses(grade:String)= viewModelScope.launch{
        _getCourses.value=Resource.Loading
        repository.getCourse (grade){
            _getCourses.value=it
        }}
    fun getLecture(courses:List<Courses>)= viewModelScope.launch{
        _getLecture.value=Resource.Loading
        repository.getLectures(courses) {
            _getLecture.value=it
        }}
    fun getSection(courses:List<Courses>)= viewModelScope.launch{
        _getSection.value=Resource.Loading
        repository.getSection(courses) {
            _getSection.value=it
        }}
    fun getProfessor(courses:List<Courses>)= viewModelScope.launch{
        _getProfessor.value=Resource.Loading
        repository.getProfessor(courses) {
            _getProfessor.value=it
        }}
    fun getAssistant(courses:List<Courses>)= viewModelScope.launch{
        _getAssistant.value=Resource.Loading
        repository.getAssistant(courses) {
            _getAssistant.value=it
        }}
    fun addPermission (permission: Permission)= viewModelScope.launch{
        _addPermission.value=Resource.Loading
        repository.addPermission(permission){
            _addPermission.value=it
        }
    }
    fun updatePostF (post:Posts)= viewModelScope.launch{
        _updatePost.value=Resource.Loading
        repository.updatePosts(post){
            _updatePost.value=it
        }
    }
    fun deletePost(post: Posts) = viewModelScope.launch{
        _deletePost.value=Resource.Loading
        repository.deletePosts(post){
            _deletePost.value=it
        }

    }


     }
