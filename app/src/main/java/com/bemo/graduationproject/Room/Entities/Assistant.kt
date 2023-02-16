package com.bemo.graduationproject.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Assistant (
    @PrimaryKey(autoGenerate = false)
    val assistantCode:String,
    val assistantName:String,
    val Specialization:String
)