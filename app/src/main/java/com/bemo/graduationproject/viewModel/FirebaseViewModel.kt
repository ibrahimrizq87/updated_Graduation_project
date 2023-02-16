package com.bemo.graduationproject.viewModel

import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.Room.Repository
import com.bemo.graduationproject.data.FirebaseRepo
import com.example.uni.data.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FirebaseViewModel @Inject constructor(
    val repository: FirebaseRepo
): ViewModel() {

    private val _post=MutableLiveData<Resource<List<Posts>>>()
     val post: LiveData<Resource<List<Posts>>>
             get()=_post


    private val _addPost=MutableLiveData<Resource<String>>()
    val addPost: LiveData<Resource<String>>
        get()=_addPost

    private val _updatePost=MutableLiveData<Resource<String>>()
    val updatePost: LiveData<Resource<String>>
        get()=_updatePost

    private val _deletePost=MutableLiveData<Resource<String>>()
    val deletePost: LiveData<Resource<String>>
        get()=_deletePost

    fun getPosts(){
        _post.value=Resource.Loading
        repository.getPosts {
        _post.value=it
    }}
    fun addPostF (post:Posts){
        _addPost.value=Resource.Loading
        repository.addPosts(post){
            _addPost.value=it
        }
    }
    fun updatePostF (post:Posts){
        _updatePost.value=Resource.Loading
        repository.updatePosts(post){
            _updatePost.value=it
        }
    }
    fun deletePost(post: Posts) {
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
