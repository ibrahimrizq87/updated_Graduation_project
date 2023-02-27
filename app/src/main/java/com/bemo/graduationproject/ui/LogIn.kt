package com.bemo.graduationproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.bemo.graduationproject.R
import com.bemo.graduationproject.databinding.ActivityLogInBinding

class LogIn : AppCompatActivity() {
    private lateinit var binding:ActivityLogInBinding
    /*private lateinit var email:EditText
     private lateinit var password:EditText
     private lateinit var logInBt:Button
     private lateinit var signUpText:TextView
    */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLogInBinding.inflate(layoutInflater)
        setContentView(binding.root)
    /*    email=findViewById(R.id.logEmailAddress)
        password=findViewById(R.id.logPassword)
        logInBt=findViewById(R.id.logBt)
        signUpText=findViewById(R.id.signInText)
    */

    }
}