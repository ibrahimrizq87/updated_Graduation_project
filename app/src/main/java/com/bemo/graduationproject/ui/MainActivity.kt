package com.bemo.graduationproject.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.bemo.graduationproject.databinding.ActivityMainBinding
import com.bemo.graduationproject.ui.userPakage.HomeScreen
import com.bemo.graduationproject.viewModel.AuthViewModel
import com.bemo.graduationproject.viewModel.FirebaseRealtimeModelView
import com.bemo.graduationproject.viewModel.FirebaseViewModel
import com.example.uni.data.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

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
            startActivity(Intent(this, SignUp::class.java))
        }
        binding.logIn.setOnClickListener {
            startActivity(Intent(this, HomeScreen::class.java))
        }
        binding.addTestData.setOnClickListener {
            authViewModel.logOut { }
        }
        binding.btAttend.setOnClickListener {


            var code=1
            if (binding.editText.text.toString().isEmpty()){

            }else{
                code=Integer. parseInt(binding.editText.text.toString())
            }
            realtimeViewModel.getAttendanceCode("AAA111", code)
            lifecycleScope.launchWhenCreated {
                realtimeViewModel.getAttendanceCode.collectLatest { stat ->
                    when (stat) {
                        is Resource.Loading -> {
                        }
                        is Resource.Success -> {
                            if (stat.result){
                                Toast.makeText(
                                    this@MainActivity,
                                    "attended successfully",
                                    Toast.LENGTH_LONG
                                ).show()
                            }else{
                                Toast.makeText(
                                    this@MainActivity,
                                    "wrong code",
                                    Toast.LENGTH_LONG
                                ).show()
                            }

                        }

                        is Resource.Failure -> {
                            Toast.makeText(
                                this@MainActivity,
                                stat.exception.toString(),
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }
            }
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