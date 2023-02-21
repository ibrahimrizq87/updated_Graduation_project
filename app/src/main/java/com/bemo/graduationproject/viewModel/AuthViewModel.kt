package com.bemo.graduationproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bemo.graduationproject.Classes.User
import com.example.uni.data.AuthRepository
import com.example.uni.data.Resource
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(private val repository: AuthRepository):ViewModel() {

    private val _register = MutableStateFlow<Resource<String>?>(null)
    //val register:LiveData<Resource<String>>
    val register=_register.asStateFlow()
    //get() = _register


    fun Register(email:String,password:String,user: User) = viewModelScope.launch {
      _register.value=Resource.Loading
      repository.Register(email,password,user){
              _register.value=it
          }

    }
    fun logOut(result:()->Unit)= viewModelScope.launch {
        repository.logOut (result)
    }
fun getSession(result: (User?) -> Unit){
    repository.getSession(result)
}
}