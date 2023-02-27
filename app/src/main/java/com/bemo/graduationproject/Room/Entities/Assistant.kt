package com.bemo.graduationproject.Room.Entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.bemo.graduationproject.Classes.user.Users

@Entity
data class Assistant (
    @PrimaryKey(autoGenerate = false)
    override var name: String,
    override var userId: String,
    override val code: String,
    override val nationalId: String,
    val Specialization:String
):Users