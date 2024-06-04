package com.example.places.NewTraining.SummerWeight

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class dbSummerWeight(context: Context): SQLiteOpenHelper(context, "ScoreSummerWeight", null, 1) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table ScoreSummerWeight (score INTEGER)")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("drop table if exists ScoreSummerWeight")
    }

    fun SaveScoreWeight(score: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("score", score)
        val result = p0.insert("ScoreSummerWeight", null, cv)
        return result != (-1).toLong()
    }

    fun DeleteScoreWeight(score: String): Boolean {
        val p0 = this.writableDatabase
        val cursor: Cursor = p0.rawQuery("select * from ScoreSummerWeight where score=?", arrayOf(score))
        val result = p0.delete("ScoreSummerWeight", "score=?", arrayOf(score))
        return result != -1
        return false
    }

    fun UpDataScoreWeight(oldCount: String, newCount: String): Boolean {
        val p0 = this.writableDatabase
        val cv = ContentValues()
        cv.put("count", newCount)
        val cursor: Cursor = p0.rawQuery("select * from UserdataAttitude where count=?", arrayOf(oldCount))
        if (cursor.count>0) {
            val result = p0.update("UserdataAttitude", cv, "count=?", arrayOf(oldCount))
            return result != -1
        }
        return false
    }

    fun gettext(): Cursor? {
        val p0 = this.writableDatabase
        val cursor = p0.rawQuery("select * from ScoreSummerWeight", null)
        return cursor
    }

}