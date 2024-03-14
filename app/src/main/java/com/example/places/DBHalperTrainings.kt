package com.example.places

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast
import kotlin.coroutines.coroutineContext

class DBHalperTrainings(context: Context): SQLiteOpenHelper(context, "UserdataTrainings", null, 1) {
    override fun onCreate(p0: SQLiteDatabase?) {
        p0?.execSQL("create table UserdataTrainings (name TEXT primary key)")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        p0?.execSQL("drop table if exists UserdataTrainings")
    }

    fun saveuserdatatrainings(name: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("name", name)
        val result = p0.insert("UserdataTrainings", null, cv)
        return result != (-1).toLong()
    }

    fun showToast(context: Context) {
        val show = Toast.makeText(context, "12345", Toast.LENGTH_SHORT).show()
        return show
    }

    fun deleteuserdata(name: String): Boolean {
        val p0 = this.writableDatabase
        val cursor: Cursor = p0.rawQuery("select * from UserdataTrainings where name=?", arrayOf(name))
        if (cursor.count>0) {
            val result = p0.delete("UserdataTrainings", "name=?", arrayOf(name))
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