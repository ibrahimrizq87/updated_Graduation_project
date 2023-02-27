package com.bemo.graduationproject.Classes.user


data class UserAdmin (
    override var name: String="",
    override var userId: String="",
    override val code: String="",
    override val nationalId: String="",
    val jobTitle:String=""
):Users