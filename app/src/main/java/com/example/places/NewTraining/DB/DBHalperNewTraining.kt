package com.example.places.NewTraining.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.BoringLayout

class DBHalperNewTraining(context: Context): SQLiteOpenHelper(context, "UserdataAttitude", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table UserdataAttitude (name TEXT, weight TEXT, type TEXT)")
        p0?.execSQL("create table UserdataWeight (weight TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists UserdataAttitude")
        db?.execSQL("drop table if exists UserdataWeight")
    }

    fun saveuserdata(name: String, weight: String, type: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("weight", weight)
        cv.put("type", type)
        val result = p0.insert("UserdataAttitude", null, cv)
        return result != (-1).toLong()
    }

    fun deleteuserdata(name: String, weight: String, type: String): Boolean {
        val p0 = this.writableDatabase
        val cursor: Cursor = p0.rawQuery("select * from UserdataAttitude where name=? and weight=? and type=?", arrayOf(name, weight, type))
            val result = p0.delete("UserdataAttitude", "name=? and weight=? and type=?", arrayOf(name, weight, type))
            return result != -1
        return false
    }

//    fun deleteuserdataName(name: String): Boolean {
//        val p0 = this.writableDatabase
//        val cursor: Cursor = p0.rawQuery("select * from UserdataAttitude where name=?", arrayOf(name))
//        val result = p0.delete("UserdataAttitude", "name=?", arrayOf(name))
//        return result != -1
//        return false
//    }
//
//    fun deleteuserdataDate(date: String): Boolean {
//        val p0 = this.writableDatabase
//        val cursor: Cursor = p0.rawQuery("select * from UserdataAttitude where date=?", arrayOf(date))
//        val result = p0.delete("UserdataAttitude", "date=?", arrayOf(date))
//        return result != -1
//        return false
//    }
//
//    fun deleteuserdataWeight(weight: String): Boolean {
//        val p0 = this.writableDatabase
//        val cursor: Cursor = p0.rawQuery("select * from UserdataAttitude where weight=?", arrayOf(weight))
//        val result = p0.delete("UserdataAttitude", "weight=?", arrayOf(weight))
//        return result != -1
//        return false
//    }

    fun gettext(): Cursor? {
        val p0 = this.writableDatabase
        val cursor = p0.rawQuery("select * from UserdataAttitude", null)
        return cursor
    }
}