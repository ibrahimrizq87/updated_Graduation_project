package com.bemo.graduationproject.Classes.user

import com.bemo.graduationproject.di.UserTypes


data class UserAdmin (
    override var name: String="",
    override   var userId: String="",
    override val code: String="",
    override val nationalId: String="",
     val jobTitle:String="",
    override val userType: String= UserTypes.adminUser,
    override val hasPermission: Boolean=false
):Users