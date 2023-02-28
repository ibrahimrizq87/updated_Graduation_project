package com.bemo.graduationproject.Classes.user

import com.bemo.graduationproject.di.UserTypes

data class UserAssistant (
    override  var name: String="",
    override var userId: String="",
    override val code: String="",
    override val nationalId: String="",
    val Specialization:String="",
    override val userType: String= UserTypes.assistantUser,
    override val hasPermission: Boolean=false
    ):Users