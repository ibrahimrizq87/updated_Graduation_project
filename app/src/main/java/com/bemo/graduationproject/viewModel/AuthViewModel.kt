package com.bemo.graduationproject.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bemo.graduationproject.Classes.user.UserStudent
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


    fun Register(email:String, password:String, userStudent: UserStudent) = viewModelScope.launch {
      _register.value=Resource.Loading
      repository.register(email,password,userStudent){
              _register.value=it
          }

    }
    fun logOut(result:()->Unit)= viewModelScope.launch {
        repository.logOut (result)
    }
fun getSession(result: (UserStudent?) -> Unit){
    repository.getSession(result)
}
}