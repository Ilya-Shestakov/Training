package com.example.places.NewTraining.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.text.BoringLayout

class DBHalperNewTraining(context: Context): SQLiteOpenHelper(context, "UserdataAttitude", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table UserdataAttitude (name TEXT, weight TEXT, count TEXT, type TEXT)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists UserdataAttitude")
    }

    fun saveuserdata(name: String, weight: String, count: String, type: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        cv.put("weight", weight)
        cv.put("count", count)
        cv.put("type", type)
        val result = p0.insert("UserdataAttitude", null, cv)
        return result != (-1).toLong()
    }

    fun deletecount(name: String, weight: String, count: String, type: String) {
        if (count != "1"){
            updatauserdatanewtrainingsCount(name, weight, type,count, (count.toInt()-1).toString())
        } else {
            deleteuserdata(name, weight, count, type)
        }
    }

    fun deleteuserdata(name: String, weight: String, count: String, type: String): Boolean {
        val p0 = this.writableDatabase
        val cursor: Cursor = p0.rawQuery("select * from UserdataAttitude where name=? and weight=? and count=? and type=?", arrayOf(name, weight, count, type))
            val result = p0.delete("UserdataAttitude", "name=? and weight=? and count=? and type=?", arrayOf(name, weight, count, type))
            return result != -1
        return false
    }

    fun updatauserdatanewtrainingsCount(name: String, weight: String, type: String, oldCount: String, newCount: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("count", newCount)
        val cursor: Cursor = p0.rawQuery("select * from UserdataAttitude where name=? and weight=? and count=? and type=?", arrayOf(name, weight, oldCount, type))
        if (cursor.count>0) {
            val result = p0.update("UserdataAttitude", cv, "name=? and weight=? and count=? and type=?", arrayOf(name, weight, oldCount, type))
            return result != -1
        }
        return false
    }

    fun deleteAllData(type: String): Boolean {
        val p0 = this.writableDatabase
        val cursor: Cursor = p0.rawQuery("select * from UserdataAttitude where type=?", arrayOf(type))
            val result = p0.delete("UserdataAttitude", "type=?", arrayOf(type))
            return result != -1
        return false
    }

    fun gettext(): Cursor? {
        val p0 = this.writableDatabase
        val cursor = p0.rawQuery("select * from UserdataAttitude", null)
        return cursor
    }
}