package com.bemo.graduationproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bemo.graduationproject.Classes.user.*
import com.example.uni.data.AuthRepository
import com.example.uni.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository):ViewModel() {

    private val _register = MutableStateFlow<Resource<String>?>(null)
    //val register:LiveData<Resource<String>>
    val register=_register.asStateFlow()
    //get() = _register


    private val _userStudent = MutableStateFlow<Resource<UserStudent?>?>(null)
    val userStudent=_userStudent.asStateFlow()



    private val _userAssistant = MutableStateFlow<Resource<UserAssistant?>?>(null)
    val userAssistant=_userAssistant.asStateFlow()



    private val _userProfessor = MutableStateFlow<Resource<UserProfessor?>?>(null)
    val userProfessor=_userProfessor.asStateFlow()


    private val _userAdmin = MutableStateFlow<Resource<UserAdmin?>?>(null)
    val userAdmin=_userAdmin.asStateFlow()



    fun Register(email:String, password:String, user: Users) = viewModelScope.launch {
      _register.value=Resource.Loading
      repository.register(email,password,user){
              _register.value=it
          }

    }
    fun getUserAdmin(id :String)= viewModelScope.launch{
        _userAdmin.value=Resource.Loading
        repository.getUserAdmin(id){
            _userAdmin.value=it
        }
    }



    fun getUserProfessor(id :String)= viewModelScope.launch{
        _userProfessor.value=Resource.Loading
        repository.getUserProfessor(id){
            _userProfessor.value=it
        }
    }


    fun getUserStudent(id :String)= viewModelScope.launch{
        _userStudent.value=Resource.Loading
        repository.getUserStudent(id){
            _userStudent.value=it
        }
    }


    fun getUserAssistant(id :String)= viewModelScope.launch{
        _userAssistant.value=Resource.Loading
        repository.getUserAssistant(id){
            _userAssistant.value=it
        }
    }
    fun setSession(user:Users){
        repository.setSession(user)
    }
    fun logOut(result:()->Unit)= viewModelScope.launch {
        repository.logOut (result)
    }
fun getSessionStudent(result: (UserStudent?) -> Unit){
    repository.getSessionStudent(result)
}
    fun getSessionAdmin(result: (UserAdmin?) -> Unit){
        repository.getSessionAdmin(result)
    }
    fun getSessionAssistant(result: (UserAssistant?) -> Unit){
        repository.getSessionAssistant(result)
    }
    fun getSessionProfessor(result: (Users?) -> Unit){
        repository.getSessionProfessor(result)
    }
    fun getUserType():String?{
        return repository.getUserType()
    }
    fun setUserType(userType:String){
        repository.setUserType(userType)
    }
}