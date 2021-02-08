package com.example.shop

import java.util.*

class User {
    var id=0
    var email=""
    var password=""
    var birthdate= ""
    constructor(_id:Int,_email:String,_password:String,_birthdate:String){
        id=_id
        email=_email
        password=_password
        birthdate=_birthdate
    }
    constructor(_email:String,_password:String,_birthdate:String){
        id=0
        email=_email
        password=_password
        birthdate=_birthdate
    }

}