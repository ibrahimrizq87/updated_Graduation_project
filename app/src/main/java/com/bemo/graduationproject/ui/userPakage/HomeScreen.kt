package com.bemo.graduationproject.ui.userPakage

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bemo.graduationproject.Classes.user.UserStudent
import com.bemo.graduationproject.R
import com.bemo.graduationproject.databinding.ActivityHomeScreenBinding
import com.bemo.graduationproject.ui.SignUp
import com.bemo.graduationproject.ui.userPakage.fragments.*

import com.bemo.graduationproject.viewModel.AuthViewModel
import com.example.uni.data.Resource
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class HomeScreen : AppCompatActivity() {
lateinit var database:FirebaseFirestore
    private lateinit var binding: ActivityHomeScreenBinding
    private val viewModel : AuthViewModel by viewModels()
// firebase -- > room  list courses
    /*
    // courses
    doctor
    assistant
    lecture
    section
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener {
            database=FirebaseFirestore.getInstance()

    when(it.itemId){
        R.id.home -> replaceFragment(HomeFragment())
        R.id.notifications -> replaceFragment(NotificationsFragment())
        R.id.profile -> replaceFragment(ProfileFragment())
        R.id.lectures -> replaceFragment(ScheduleFragment())
        else -> {

        }

    }
    true
}
    }
private fun replaceFragment(fragment: Fragment){
    val fragmentManager=supportFragmentManager
    val fragmentTransaction =fragmentManager.beginTransaction()
    fragmentTransaction.replace(R.id.fragment_container,fragment)
    fragmentTransaction.commit()

}
fun updateUser(user: UserStudent){
    viewModel.getUserStudent(user.userId)
    lifecycleScope.launchWhenCreated {
        viewModel.userStudent.collectLatest {state ->
            when (state) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {
                    val user =state.result
                    if (user!=null){viewModel.setSession(state.result)
                        Toast.makeText(this@HomeScreen,"user data = "+user.grade,Toast.LENGTH_LONG).show()

                    }
                                    }
                is Resource.Failure -> {
                    Toast.makeText(this@HomeScreen,state.exception.toString(),Toast.LENGTH_LONG).show()
                }
            }

        }
    }

}
    override fun onStart() {
        super.onStart()
        val userType =viewModel.getUserType()
        viewModel.getSessionStudent {user->
            if (user !=null){
                updateUser(user)
                if (user.hasPermission == true){
                    replaceFragment(HomeFragment())
                }else{
                    replaceFragment(PermissionsFragment())

                }
                if (userType==null){
                    viewModel.setUserType(user.userType)
                }
            }else{
                Toast.makeText(this,"no user found. have to register",Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,SignUp::class.java))
            }
        }
        }
    fun click(view: View) {
        replaceFragment(ScheduleFragment())
    }
}

