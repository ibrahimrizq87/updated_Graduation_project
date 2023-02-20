package com.bemo.graduationproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bemo.graduationproject.Classes.CommentClass
import com.bemo.graduationproject.Classes.DataClass
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.data.FirebaseRealtimeRepo
import com.bemo.graduationproject.data.FirebaseRealtimeRepoImp
import com.bemo.graduationproject.data.FirebaseRepo
import com.example.uni.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FirebaseRealtimeModelView @Inject constructor(
    val repository: FirebaseRealtimeRepo
): ViewModel() {


    private val _getData= MutableStateFlow<Resource<List<DataClass>>?>(null)
    val getData=_getData.asStateFlow()


    private val _addData= MutableStateFlow<Resource<String>?>(null)
    val addData=_addData.asStateFlow()

    fun addData (data: DataClass)= viewModelScope.launch {
        _addData.value= Resource.Loading
        repository.addData(data){
            _addData.value=it
        }
    }


    fun getData()= viewModelScope.launch {
        _getData.value=Resource.Loading
        repository.getData {
            _getData.value=it
        }}
}


