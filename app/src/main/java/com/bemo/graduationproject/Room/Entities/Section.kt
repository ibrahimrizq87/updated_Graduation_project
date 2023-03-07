package com.bemo.graduationproject.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Section (
    @PrimaryKey(autoGenerate = false)
    val sectionId : String,
    val courseCode:String,
    val hallID:String,
    val assistantID:String,
    val section : String,

    val time:String,
    val endTime:String

)