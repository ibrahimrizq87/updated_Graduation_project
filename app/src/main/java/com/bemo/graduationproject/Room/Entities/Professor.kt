package com.bemo.graduationproject.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Professor (
    @PrimaryKey(autoGenerate = false)
    val professorCode:String,
    val professorName:String,
    val Specialization:String
)