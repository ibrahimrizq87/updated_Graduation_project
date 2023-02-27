package com.bemo.graduationproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import com.bemo.graduationproject.R
import com.bemo.graduationproject.databinding.ActivityHomeScreenBinding
import com.bemo.graduationproject.di.FireStoreTable
import com.bemo.graduationproject.ui.fragments.*
import com.bemo.graduationproject.viewModel.AuthViewModel
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.AndroidEntryPoint

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
        R.id.lectures -> replaceFragment(SchaduleFragment())
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

    override fun onStart() {
        super.onStart()
        viewModel.getSession { user->
            if (user!=null){
            if (user.hasPermission == true){
                replaceFragment(HomeFragment())
            }else{
               // replaceFragment(PermissionsFragment())
                replaceFragment(HomeFragment())
            }
            }else{
                replaceFragment(HomeFragment())
                Toast.makeText(this,"no user found in home fragment",Toast.LENGTH_SHORT).show()
            }
        }
    }
}
