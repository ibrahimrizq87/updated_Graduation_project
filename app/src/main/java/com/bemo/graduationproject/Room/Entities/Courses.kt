package com.bemo.graduationproject.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Courses (
    @PrimaryKey(autoGenerate = false)
    val courseCode:String,
    val grade : String,
    val professor:String,
    val learningAssistant:String

    )