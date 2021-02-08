package com.example.shop

class Category {
    var id:Int=0
    var name:String=""
    constructor(_id:Int, _name:String){
        id=_id
        name=_name
    }
    constructor(){
        name=""
        id=0
    }

}