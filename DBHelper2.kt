package com.example.shop

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper2(context:Context?, name:String?, factory: SQLiteDatabase.CursorFactory?, version:Int) : SQLiteOpenHelper(context,name,factory,version) {
    companion object {
        val bdName = "Shop.db"
        val tableName = "u"
        val id = "ID"
        val email = "Email"
        val password = "Password"
        val birthdate = "Birthdate"
        val version = 1
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db!!.execSQL("CREATE TABLE $tableName ($id INTEGER PRIMARY KEY AUTOINCREMENT, $email TEXT NOT NULL, $password TEXT NOT NULL, $birthdate DATE NOT NULL);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $tableName ;")
    }

}