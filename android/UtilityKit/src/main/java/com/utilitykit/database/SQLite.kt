package com.utilitykit.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

class SQLite {
    init {
        instance = this
    }

    companion object{
        private var instance: SQLite? = null
        fun shared() : SQLite {
            if(instance != null){
                return instance as SQLite
            }else{
                return SQLite()
            }
        }
    }

    lateinit var helper : SQLiteHelper
    lateinit var db : SQLiteDatabase
    lateinit var readableDb : SQLiteDatabase

    fun getReadableDB():SQLiteDatabase{
        readableDb = helper.readableDatabase
        db = helper.writableDatabase
        return readableDb
    }

    fun setUp(context: Context){
        helper = SQLiteHelper(context)
        db = helper.writableDatabase
        readableDb = helper.readableDatabase
    }

    fun insert(table:String,values: ContentValues){
        try {
            Log.d("Inserting In Table $table",values.toString())
            db.replaceOrThrow(table,null, values)
        }catch (e:Exception){
            Log.d("Replace Exception",e.localizedMessage)
        }
    }

    fun execute(query:String){
        if(db.isOpen){
            db.execSQL(query)
        }else{
            Log.d("Oops","Database Not Open")
        }
    }

    fun getRows(table: String):ArrayList<ContentValues>{
        val selectQuery = "SELECT  * FROM ${table}"
        val db = readableDb
        val cursor = db.rawQuery(selectQuery,null)
        var rows : ArrayList<ContentValues> = ArrayList()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            var retVal = ContentValues()
            var isValuexist = false
            for (i in 0 until cursor.columnCount) {
                val cName = cursor.getColumnName(i)
                try {
                    when (cursor.getType(i)) {
                        Cursor.FIELD_TYPE_INTEGER -> retVal.put(cName, cursor.getInt(i))
                        Cursor.FIELD_TYPE_FLOAT -> retVal.put(cName, cursor.getFloat(i))
                        Cursor.FIELD_TYPE_STRING -> retVal.put(cName, cursor.getString(i))
                        Cursor.FIELD_TYPE_BLOB -> retVal.put(
                            cName,
                            (cursor.getBlob(i))
                        )
                    }
                    isValuexist = true
                } catch (ex: Exception) {
                    isValuexist = false
                    retVal.put(cName,"")
                    Log.e("DbErrpr", "Exception converting cursor column field: $cName")
                    Log.e("Cursor", "cursor is as : $cursor")
                }
            }
            if(isValuexist){
                rows.add(retVal)
                isValuexist = false
            }
            cursor.moveToNext()
        }
        return rows
    }

    fun getRowsByQuery(query: String):ArrayList<ContentValues>{
        val db = getReadableDB()
        val cursor = db.rawQuery(query,null)
        var rows : ArrayList<ContentValues> = ArrayList()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            var retVal = ContentValues()
            var isValuexist = false
            for (i in 0 until cursor.columnCount) {
                val cName = cursor.getColumnName(i)
                try {
                    when (cursor.getType(i)) {
                        Cursor.FIELD_TYPE_INTEGER -> retVal.put(cName, cursor.getInt(i))
                        Cursor.FIELD_TYPE_FLOAT -> retVal.put(cName, cursor.getFloat(i))
                        Cursor.FIELD_TYPE_STRING -> retVal.put(cName, cursor.getString(i))
                        Cursor.FIELD_TYPE_BLOB -> retVal.put(
                            cName,
                            (cursor.getBlob(i))
                        )
                    }
                    isValuexist = true
                } catch (ex: Exception) {
                    isValuexist = false
                    retVal.put(cName,"")
                    Log.e("DbErrpr", "Exception converting cursor column field: $cName")
                    Log.e("Cursor", "cursor is as : $cursor")
                }
            }
            if(isValuexist){
                rows.add(retVal)
                isValuexist = false
            }
            cursor.moveToNext()
        }
        return rows
    }

    fun fetchRows(query: String): ArrayList<ContentValues> {
        val db = shared().readableDb
        val cursor = db.rawQuery(query, null)
        var rows: ArrayList<ContentValues> = ArrayList()
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            var retVal = ContentValues()
            var isValuexist = false
            for (i in 0 until cursor.columnCount) {
                val cName = cursor.getColumnName(i)
                try {
                    when (cursor.getType(i)) {
                        Cursor.FIELD_TYPE_INTEGER -> retVal.put(cName, cursor.getInt(i))
                        Cursor.FIELD_TYPE_FLOAT -> retVal.put(cName, cursor.getFloat(i))
                        Cursor.FIELD_TYPE_STRING -> retVal.put(cName, cursor.getString(i))
                        Cursor.FIELD_TYPE_BLOB -> retVal.put(
                            cName,
                            (cursor.getBlob(i))
                        )
                    }
                    isValuexist = true
                } catch (ex: Exception) {
                    isValuexist = false
                    retVal.put(cName, "")
                    Log.e("DbErrpr", "Exception converting cursor column field: $cName")
                    Log.e("Cursor", "cursor is as : $cursor")
                }
            }
            if (isValuexist) {
                rows.add(retVal)
                isValuexist = false
            }
            cursor.moveToNext()
        }
        return rows
    }

    fun getRow(query:String):ContentValues{
        val db = shared().readableDb
        val cursor = db.rawQuery(query,null)
        cursor.moveToFirst()
        var retVal = ContentValues()
        var isValuexist = false
        for (i in 0 until cursor.columnCount) {
            val cName = cursor.getColumnName(i)
            try {
                when (cursor.getType(i)) {
                    Cursor.FIELD_TYPE_INTEGER -> retVal.put(cName, cursor.getInt(i))
                    Cursor.FIELD_TYPE_FLOAT -> retVal.put(cName, cursor.getFloat(i))
                    Cursor.FIELD_TYPE_STRING -> retVal.put(cName, cursor.getString(i))
                    Cursor.FIELD_TYPE_BLOB -> retVal.put(
                        cName,
                        (cursor.getBlob(i))
                    )
                }
                isValuexist = true
            } catch (ex: Exception) {
                isValuexist = false
                retVal.put(cName,"")
                Log.e("DbErrpr", "Exception converting cursor column field: $cName")
                Log.e("Cursor", "cursor is as : $cursor")
            }
        }
        return retVal
    }

    fun getVal(query: String):Any?{
        val cursor = db.rawQuery(query,null)
        cursor.moveToFirst()
        while (!cursor.isAfterLast) {
            for (i in 0 until cursor.columnCount) {
                val cName = cursor.getColumnName(i)
                try {
                    when (cursor.getType(i)) {
                        Cursor.FIELD_TYPE_INTEGER -> return cursor.getInt(i)
                        Cursor.FIELD_TYPE_FLOAT -> return cursor.getFloat(i)
                        Cursor.FIELD_TYPE_STRING -> return cursor.getString(i)
                        Cursor.FIELD_TYPE_BLOB -> return cursor.getBlob(i)
                    }
                } catch (ex: Exception) {
                    Log.e("DbErrpr", "Exception converting cursor column field: $cName")
                    Log.e("Cursor", "cursor is as : $cursor")
                }
            }
        }
        return null
    }
}

class SQLiteHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){
    override fun onCreate(db: SQLiteDatabase) {

    }
    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        if(oldVersion != newVersion){
            onCreate(db)
        }
    }
    override fun onDowngrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        onUpgrade(db, oldVersion, newVersion)
    }
    companion object {
        // If you change the database schema, you must increment the database version.
        const val DATABASE_VERSION = 2
        const val DATABASE_NAME = "Solute_version${DATABASE_VERSION}.db"
    }
}