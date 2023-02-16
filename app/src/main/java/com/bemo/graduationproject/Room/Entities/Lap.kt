package com.bemo.graduationproject.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Lap (
    @PrimaryKey(autoGenerate = false)
    val lapID:String,
    val size : Int,
    val Specialization:String


    )