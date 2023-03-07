package com.bemo.graduationproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bemo.graduationproject.Classes.AttendanceEmbedded
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

    private val _updateHallState= MutableStateFlow<Resource<String>?>(null)
    val updateHallState=_updateHallState.asStateFlow()

    private val _getAttendanceCode= MutableStateFlow<Resource<Boolean>?>(null)
    val getAttendanceCode=_getAttendanceCode.asStateFlow()
    fun getAttendanceCode (embeddedId:String,scannedCode:Int)= viewModelScope.launch {
        _getAttendanceCode.value= Resource.Loading
        repository.getAttendWithCode(embeddedId,scannedCode){
            _getAttendanceCode.value=it
        }
    }

    fun updateHallState (embedded: AttendanceEmbedded)= viewModelScope.launch {
        _updateHallState.value= Resource.Loading
        repository.updateHallState(embedded){
            _updateHallState.value=it
        }
    }

    private val _updateLabState= MutableStateFlow<Resource<String>?>(null)
    val updateLabState=_updateLabState.asStateFlow()


    fun updateLabState (embedded: AttendanceEmbedded)= viewModelScope.launch {
        _updateLabState.value= Resource.Loading
        repository.updateLabState(embedded){
            _updateLabState.value=it
        }
    }


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


