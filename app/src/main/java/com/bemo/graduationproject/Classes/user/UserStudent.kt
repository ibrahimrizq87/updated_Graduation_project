package com.bemo.graduationproject.Classes.user

import com.bemo.graduationproject.di.UserTypes

data class UserStudent (
     override var name: String="",
     override var userId: String="",
     override  val code: String="",
     override  val nationalId: String="",
        val grade :String = "",
        var section:String="",
     override  val userType: String= UserTypes.studentUser,
     override  var hasPermission:Boolean=false



):Users