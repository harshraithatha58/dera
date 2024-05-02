package com.example.dera

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class MyDBHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "userData.db"
        private const val DATABASE_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE IF NOT EXISTS users (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "identification TEXT, " +
                "name TEXT, " +
                "fathers_name TEXT, " +
                "address TEXT, " +
                "religion TEXT, " +
                "marital_status TEXT, " +
                "mobile_number TEXT, " +
                "designation TEXT, " +
                "duration TEXT, " +
                "route_used TEXT, " +
                "place_visited_last_year TEXT, " +
                "f_name TEXT, " +
                "f_age TEXT, " +
                "f_gender TEXT, " +
                "f_relation TEXT, " +
                "f_addhar_number TEXT, " +
                "f_mobile_number TEXT, " +
                "image_address TEXT)")
    }


    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Handle database upgrades here
        db.execSQL("DROP TABLE IF EXISTS users")
        onCreate(db)
    }
}
