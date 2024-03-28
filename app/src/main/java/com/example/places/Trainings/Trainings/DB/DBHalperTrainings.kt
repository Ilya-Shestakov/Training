package com.example.places.Trainings.Trainings.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHalperTrainings(context: Context): SQLiteOpenHelper(context, "UserdataTrainings", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table UserdataTrainings (name TEXT, date TEXT, weight TEXT)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists UserdataTrainings")
    }

    fun saveuserdata(name: String, date: String, weight: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("date", date)
        cv.put("weight", weight)
        val result = p0.insert("UserdataTrainings", null, cv)
        return result != (-1).toLong()
    }

    fun deleteuserdata(name: String, date: String, weight: String): Boolean {
        val p0 = this.writableDatabase
        val cursor: Cursor = p0.rawQuery("select * from UserdataTrainings where name=? and weight=? and date=?", arrayOf(name, weight, date))
        if (cursor.count>0) {
            val result = p0.delete("UserdataTrainings", "name=? and weight=? and date=?", arrayOf(name, weight, date))
            return result != -1
        }
        return false
    }

    fun gettext(): Cursor? {
        val p0 = this.writableDatabase
        val cursor = p0.rawQuery("select * from UserdataTrainings", null)
        return cursor
    }
}