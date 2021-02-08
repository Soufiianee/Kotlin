package com.example.shop

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class DAO {
    lateinit var helper:DBHelper
    lateinit var db: SQLiteDatabase
    constructor(c: Context){
        helper=DBHelper(c,DBHelper.bdName,null,DBHelper.version)
        db=helper.writableDatabase
    }

    fun ajouter(e:User):Long{
        var values= ContentValues()
        values.put(DBHelper.user_email,e.email)
        values.put(DBHelper.user_password,e.password)
        values.put(DBHelper.user_birthdate,e.birthdate)
        return db.insert(DBHelper.user_tableName,null,values)
    }
    fun chercher(email:String):User{
        var curseur=db.rawQuery("select * from ${DBHelper.user_tableName} where email=\'"+email+"\';",null)
        var res= User(-1,"","","")
        if(!curseur.moveToFirst()){
            return res
        }
        else{
            res.id=curseur.getInt(curseur.getColumnIndex(DBHelper.user_id))
            res.email=curseur.getString(curseur.getColumnIndex(DBHelper.user_email))
            res.password=curseur.getString(curseur.getColumnIndex(DBHelper.user_password))
            res.birthdate=curseur.getString(curseur.getColumnIndex(DBHelper.user_birthdate))
            return res
        }
    }
    fun getAll():ArrayList<User>{
        var mesUsers:ArrayList<User> = ArrayList<User>()
        var curseur=db.rawQuery("select * from ${DBHelper.user_tableName};",null)
        var email:String=""
        var password:String=""
        var birthdate:String=""
        var id=0
        if(curseur.moveToFirst()){
            curseur.moveToPrevious()
            while (curseur.moveToNext()) {
                id = curseur.getInt(curseur.getColumnIndex(DBHelper.user_id))
                email = curseur.getString(curseur.getColumnIndex(DBHelper.user_email))
                password = curseur.getString(curseur.getColumnIndex(DBHelper.user_password))
                birthdate=curseur.getString(curseur.getColumnIndex(DBHelper.user_birthdate))
                mesUsers.add(User(id,email,password,birthdate))
            }
        }
        return mesUsers

    }

    fun getproducts_category(category:Category):ArrayList<Product>{
        var mesProducts:ArrayList<Product> = ArrayList<Product>()
        var curseur=db.rawQuery("select * from ${DBHelper.product_tableName} WHERE ${DBHelper.product_category}=${category.id};",null)
        var id=0
        var name=""
        var price=0
        var image=""
        var description=""
        if(curseur.moveToFirst()){
            curseur.moveToPrevious()
            while (curseur.moveToNext()) {
                id = curseur.getInt(curseur.getColumnIndex(DBHelper.product_id))
                name = curseur.getString(curseur.getColumnIndex(DBHelper.product_name))
                price= curseur.getInt(curseur.getColumnIndex(DBHelper.product_price))
                image= curseur.getString(curseur.getColumnIndex(DBHelper.product_image))
                description= curseur.getString(curseur.getColumnIndex(DBHelper.product_description))

                mesProducts.add(Product(id,name,category,price,image,description))
            }
        }
        return mesProducts

    }
    fun getproducts():ArrayList<Product>{
        var mesProducts:ArrayList<Product> = ArrayList<Product>()
        var curseur=db.rawQuery("select * from ${DBHelper.product_tableName} INNER JOIN ${DBHelper.category_tableName}" +
                " ON ${DBHelper.product_tableName}.${DBHelper.product_category} = ${DBHelper.category_tableName}.${DBHelper.category_id};",null)
        var id=0
        var name=""
        var price=0
        var image=""
        var description=""
        var category:Category
        if(curseur.moveToFirst()){
            curseur.moveToPrevious()
            while (curseur.moveToNext()) {
                id = curseur.getInt(curseur.getColumnIndex(DBHelper.product_id))
                name = curseur.getString(curseur.getColumnIndex(DBHelper.product_name))
                price= curseur.getInt(curseur.getColumnIndex(DBHelper.product_price))
                category=Category(curseur.getInt(curseur.getColumnIndex(DBHelper.category_id)),curseur.getString(curseur.getColumnIndex(DBHelper.category_name)))
                price= curseur.getInt(curseur.getColumnIndex(DBHelper.product_price))
                image= curseur.getString(curseur.getColumnIndex(DBHelper.product_image))
                description= curseur.getString(curseur.getColumnIndex(DBHelper.product_description))

                mesProducts.add(Product(id,name,category,price,image,description))
            }
        }
        return mesProducts

    }



    fun getAllcategories():ArrayList<Category>{
        var mesCategories:ArrayList<Category> = ArrayList<Category>()
        var curseur=db.rawQuery("select * from ${DBHelper.category_tableName};",null)
        var name:String=""
        var id=0
        if(curseur.moveToFirst()){
            curseur.moveToPrevious()
            while (curseur.moveToNext()) {
                id = curseur.getInt(curseur.getColumnIndex(DBHelper.category_id))
                name = curseur.getString(curseur.getColumnIndex(DBHelper.category_name))
                mesCategories.add(Category(id,name))

            }
        }
        return mesCategories

    }

    fun supprimer(){
        db.delete(DBHelper.user_tableName,null,null)
    }
}