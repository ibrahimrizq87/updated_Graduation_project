package com.bemo.graduationproject.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Student (
    @PrimaryKey(autoGenerate = false)
    val studentCode:String,
    val name:String,
    val grade : String,
    val section:String,
    val nationalID:String
)