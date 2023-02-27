package com.bemo.graduationproject.Classes.user

data class UserStudent (
    override var name: String="",
    override var userId: String="",
    override val code: String="",
    override val nationalId: String="",
    val grade :String = "",
    var hasPermission:Boolean=false,
    var section:String=""
    ): Users