package com.bemo.graduationproject.viewModel

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bemo.graduationproject.Classes.Permission
import com.bemo.graduationproject.Classes.Posts
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
    /*android.os.Handler(Looper.getMainLooper()).postDelayed({
        _post.value=repository.getPosts()
    },2000)
*/

     }
