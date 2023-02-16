package com.bemo.graduationproject.Classes



class User {
    var userId:String? = null
    var name :String? = null
    var code :Int? = null
    var nationalId :String? = null
    var grade :String? = null

    constructor()
    constructor(userId:String?,name :String?,code :Int?,nationalId :String?,grade:String?){
        this.userId=userId
        this.name=name
        this.code=code
        this.nationalId=nationalId
        this.grade=grade


    }
}