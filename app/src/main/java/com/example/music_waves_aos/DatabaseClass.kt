package com.example.music_waves_aos

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseClass(context:Context?) : SQLiteOpenHelper(context,"login1.db",null, 1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE user(ID INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, password TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS user")
    }

    fun Insert(email: String?, password: String?): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put("email", email)
        contentValues.put("password", password)
        val result = sqLiteDatabase.insert("email", null, contentValues)
        return result != -1L
    }

    fun CheckEmail(email: String): Boolean {
        val sqLiteDatabase = this.writableDatabase
        val cursor = sqLiteDatabase.rawQuery("SELECT * FROM user WHERE email=?", arrayOf(email))
        return cursor.count <= 0
    }

    fun CheckLogin(email: String, password: String): Boolean {
        val sqLiteDatabase = this.readableDatabase
        val cursor = sqLiteDatabase.rawQuery(
            "SELECT * FROM user WHERE email=? AND password=?",
            arrayOf(email, password)
        )
        return (cursor.count > 0)
    }
}
