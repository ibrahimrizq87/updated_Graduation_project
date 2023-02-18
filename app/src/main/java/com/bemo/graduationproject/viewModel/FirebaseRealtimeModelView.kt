package com.bemo.graduationproject.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemo.graduationproject.Classes.DataClass
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.data.FirebaseRealtimeRepo
import com.bemo.graduationproject.data.FirebaseRealtimeRepoImp
import com.bemo.graduationproject.data.FirebaseRepo
import com.example.uni.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirebaseRealtimeModelView @Inject constructor(
    val repository: FirebaseRealtimeRepo
): ViewModel() {


    private val _getData= MutableLiveData<Resource<List<DataClass>>>()
    val getData: LiveData<Resource<List<DataClass>>>
        get()=_getData

    private val _addData= MutableLiveData<Resource<String>>()
    val addData: LiveData<Resource<String>>
        get()=_addData

    fun addData (data: DataClass){
        _addData.value= Resource.Loading
        repository.addData(data){
            _addData.value=it
        }
    }

    fun getData(){
        _getData.value=Resource.Loading
        repository.getData {
            _getData.value=it
        }}
}


