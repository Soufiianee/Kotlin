package com.example.shop

class Product {
    var id:Int=0
    var name:String=""
    var category:Category= Category()
    var price:Int=0
    var image:String=""
    var description:String=""
    constructor(_id:Int,_name:String,_category:Category,_price:Int, _image:String, _description:String){
        id=_id
        name=_name
        price=_price
        category=_category
        image=_image
        description=_description


    }
    constructor(){
        id=0
        name=""
        price=0
        category=Category()
        image=""
        description=""
    }

}