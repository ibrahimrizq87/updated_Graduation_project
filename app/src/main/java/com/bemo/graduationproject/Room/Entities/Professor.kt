package com.bemo.graduationproject.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bemo.graduationproject.Classes.user.Users
import com.bemo.graduationproject.di.UserTypes

@Entity
data class Professor (
    @PrimaryKey(autoGenerate = false)
     var name: String="",
     val code: String="",
     val Specialization:String,

)