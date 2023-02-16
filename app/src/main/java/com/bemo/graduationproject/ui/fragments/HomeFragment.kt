package com.bemo.graduationproject.ui.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast

import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bemo.graduationproject.Classes.Posts
import com.bemo.graduationproject.R
import com.bemo.graduationproject.adapters.PostsAdapter
import com.bemo.graduationproject.viewModel.FirebaseViewModel
import com.example.uni.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

val TAG:String="PostsListFragment"
    val viewModel:FirebaseViewModel by viewModels()
lateinit var  adapter : PostsAdapter
lateinit var postsList:MutableList<Posts>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_home, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val add= view.findViewById<Button>(R.id.add)
        val text= view.findViewById<EditText>(R.id.txtxt)

        val progress= view.findViewById<ProgressBar>(R.id.progress_par)
        val recyclerView=view.findViewById<RecyclerView>(R.id.recy)
        postsList= arrayListOf()
        adapter= PostsAdapter(requireContext(),postsList,

    onItemClicked = {pos, item->
        Toast.makeText(requireContext(),item.authorName,Toast.LENGTH_SHORT).show()
                    }
,
    onUpdateClicked = { pos, item ->
        viewModel.updatePostF(
            Posts(description = text.text.toString(),
                authorName = text.text.toString(),
                postID =  item.postID,
                time = Date())
                    )
        viewModel.updatePost.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading -> {
                    progress.visibility=View.VISIBLE
                    Log.e(TAG, "Loading")

                }
                is Resource.Success -> {
                    progress.visibility=View.INVISIBLE
                    Toast.makeText(context,state.result,Toast.LENGTH_LONG).show()
                }
                is Resource.Failure -> {
                    Log.e(TAG, state.exception.toString())
                    progress.visibility=View.INVISIBLE
                    Toast.makeText(context,state.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }

        //Toast.makeText(requireContext(), item.authorName+" update", Toast.LENGTH_SHORT).show()
    },
    onDeleteClicked = {pos, item->
    viewModel.deletePost(item)
        viewModel.deletePost.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading -> {
                    progress.visibility=View.VISIBLE
                    Log.e(TAG, "Loading")

                }
                is Resource.Success -> {
                    progress.visibility=View.INVISIBLE
                    Toast.makeText(context,state.result,Toast.LENGTH_LONG).show()
                }
                is Resource.Failure -> {
                    Log.e(TAG, state.exception.toString())
                    progress.visibility=View.INVISIBLE
                    Toast.makeText(context,state.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }

    //Toast.makeText(requireContext(),item.authorName+" Delete",Toast.LENGTH_SHORT).show()
    }
)

        recyclerView.layoutManager=LinearLayoutManager(requireContext())
        recyclerView.adapter=adapter

        add.setOnClickListener{

        viewModel.addPostF(Posts(
        description = text.text.toString(),
        authorName = text.text.toString(),
        postID =  "",
        time = Date()
    ))


}
        viewModel.addPost.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading -> {
                    progress.visibility=View.VISIBLE
                    Log.e(TAG, "Loading")

                }
                is Resource.Success -> {
                    progress.visibility=View.INVISIBLE
                    Toast.makeText(context,state.result,Toast.LENGTH_LONG).show()
                }
                is Resource.Failure -> {
                    Log.e(TAG, state.exception.toString())
                    progress.visibility=View.INVISIBLE
                    Toast.makeText(context,state.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }
   viewModel.getPosts()
        viewModel.post.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Loading -> {
                    progress.visibility=View.VISIBLE
                    Log.e(TAG, "Loading")

                }
                is Resource.Success -> {
                    progress.visibility=View.INVISIBLE
adapter.update(state.result.toMutableList())
                    state.result.forEach {
                        Log.e(TAG, it.toString())
                    }
                }
                is Resource.Failure -> {
                    Log.e(TAG, state.exception.toString())
                progress.visibility=View.INVISIBLE
                    Toast.makeText(context,state.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }
        }

}
    }


