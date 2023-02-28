package com.bemo.graduationproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.bemo.graduationproject.databinding.ActivityMainBinding
import com.bemo.graduationproject.ui.userPakage.HomeScreen
import com.bemo.graduationproject.viewModel.AuthViewModel
import com.bemo.graduationproject.viewModel.FirebaseRealtimeModelView
import com.bemo.graduationproject.viewModel.FirebaseViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
val realtimeViewModel:FirebaseRealtimeModelView by viewModels()
    val vm :FirebaseViewModel by viewModels()
    val authViewModel:AuthViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    binding.signUp.setOnClickListener {
        startActivity(Intent(this,SignUp::class.java))
    }
        binding.logIn.setOnClickListener{
            startActivity(Intent(this, HomeScreen::class.java))
        }
        binding.addTestData.setOnClickListener {
            authViewModel.logOut {  }
           }

    }
}
/*
*   vm.addCourse(Courses("ABC",grades.firstGrade,"pf123","la123"))
            vm.addSection(Section("ABC","h123","la123","S1","s123","am","pm"))
            vm.addSection(Section("ABC","h1231","la1231","S11","s1231","am1","pm1"))
            vm.addSection(Section("ABC","h1232","la1232","S12","s1232","am2","pm2"))
            vm.addSection(Section("ABC","h1233","la1233","S13","s1233","am3","pm3"))
       */