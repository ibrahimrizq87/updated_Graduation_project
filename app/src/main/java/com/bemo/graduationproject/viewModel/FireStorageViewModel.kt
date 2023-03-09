package com.bemo.graduationproject.viewModel

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.data.FireStorageRepo
import com.example.uni.data.Resource
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class FireStorageViewModel @Inject constructor(
    val repository: FireStorageRepo
): ViewModel() {
    private val _getUri= MutableStateFlow<Resource<Uri>?>(null)
    val getUri=_getUri.asStateFlow()

    private val _addUri= MutableStateFlow<Resource<String>?>(null)
    val addUri=_addUri.asStateFlow()

    fun getUri(userId:String)= viewModelScope.launch{
        _getUri.value=Resource.Loading
        repository.downloadUri(userId) {
            _getUri.value=it
        }
    }
    fun addUri(userId:String,uri:Uri)= viewModelScope.launch{
        _addUri.value=Resource.Loading
        repository.uploadImage(uri,userId) {
            _addUri.value=it
        }
    }

}