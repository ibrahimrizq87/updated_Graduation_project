package com.bemo.graduationproject.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class Hall (
    @PrimaryKey(autoGenerate = false)
    val hallID:String,
    val size : Int,


)