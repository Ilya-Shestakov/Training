package com.example.places.BTNSNEWMACHINE.HANDS.DB


import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHalperHands(context: Context): SQLiteOpenHelper(context, "UserdataHands", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table UserdataHands (name TEXT primary key)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists UserdataHands")
    }

    fun saveuserdata(name: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        val result = p0.insert("UserdataHands", null, cv)
        return result != (-1).toLong()
    }

    fun deleteuserdata(name: String): Boolean {
        val p0 = this.writableDatabase
        val cursor: Cursor = p0.rawQuery("select * from UserdataHands where name=?", arrayOf(name))
        if (cursor.count>0) {
            val result = p0.delete("UserdataHands", "name=?", arrayOf(name))
            return result != -1
        }
        return false
    }

    fun gettext(): Cursor? {
        val p0 = this.writableDatabase
        val cursor = p0.rawQuery("select * from UserdataHands", null)
        return cursor
    }


}