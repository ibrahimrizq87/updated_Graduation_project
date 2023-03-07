package com.bemo.graduationproject.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Lecture (
    @PrimaryKey(autoGenerate = false)
    val lectureId:String,
    val courseCode:String,
    val hallID:String,
    val professorID:String,
    val time:String,
    val endTime:String

)