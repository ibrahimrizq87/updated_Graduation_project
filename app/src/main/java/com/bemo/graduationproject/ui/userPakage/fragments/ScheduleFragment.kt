package com.bemo.graduationproject.ui.userPakage.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bemo.graduationproject.R
import com.bemo.graduationproject.viewModel.FirebaseViewModel

class ScheduleFragment : Fragment() {
    val viewModel: FirebaseViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_lectures, container, false)
    }


    }
