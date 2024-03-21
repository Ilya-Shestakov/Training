package com.example.places.Trainings.Trainings.DB

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHalperTrainings(context: Context): SQLiteOpenHelper(context, "UserdataTrName", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table UserdataTrName (name TEXT primary key)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists UserdataTrName")
    }

    fun saveuserdata(name: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        val result = p0.insert("UserdataTrName", null, cv)
        return result != (-1).toLong()
    }

    fun deleteuserdata(name: String): Boolean {
        val p0 = this.writableDatabase
        val cursor: Cursor = p0.rawQuery("select * from UserdataTrName where name=?", arrayOf(name))
        if (cursor.count>0) {
            val result = p0.delete("UserdataTrName", "name=?", arrayOf(name))
            return result != -1
        }
        return false
    }

    fun gettext(): Cursor? {
        val p0 = this.writableDatabase
        val cursor = p0.rawQuery("select * from UserdataTrName", null)
        return cursor
    }
}