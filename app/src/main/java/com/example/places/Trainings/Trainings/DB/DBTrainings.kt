package com.example.places.Trainings.Trainings.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.view.ContextMenu

class DBTrainings(context: Context): SQLiteOpenHelper(context, "UserDataBaseTrainings", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table UserDataBaseTrainings (name TEXT, weight TEXT, date TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists UserDataBaseTrainings")
    }

    fun savedatatrainings(name: String, weight: String, date: String): Boolean{
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("weight", weight)
        cv.put("date", date)
        val result = p0.insert("UserDataBaseTrainings", null, cv)
        return result != (-1).toLong()
    }

    fun updatauserdatatrainingsName(OldName: String, NewName: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", NewName)
        val cursor: Cursor = p0.rawQuery("select * from UserDataBaseTrainings where name=?", arrayOf(OldName))
        if (cursor.count>0) {
            val result = p0.update("UserDataBaseTrainings", cv, "name=?", arrayOf(OldName))
            return result != -1
        }
        return false
    }

    fun updatauserdatatrainingsWeight(name: String, weight: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("weight", weight)
        val cursor: Cursor = p0.rawQuery("select * from UserDataBaseTrainings where name=?", arrayOf(name))
        if (cursor.count>0) {
            val result = p0.update("UserDataBaseTrainings", cv, "name=?", arrayOf(name))
            return result != -1
        }
        return false
    }


    fun deleteuserdatatraining(name: String, weight: String, date: String): Boolean {
        val p0 = this.writableDatabase
        val cursor: Cursor = p0.rawQuery("select * from UserDataBaseTrainings where name=? and weight=? and date=?", arrayOf(name, weight, date))
            val result = p0.delete("UserDataBaseTrainings", "name=? and weight=? and date=?", arrayOf(name, weight, date))
            return result != -1
        return false
    }

    fun gettext(): Cursor? {
        val p0 = this.writableDatabase
        val cursor = p0.rawQuery("select * from UserDataBaseTrainings", null)
        return cursor
    }

}