package com.example.runimprove

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.sql.Date

class DatabaseHelper(context: Context) : SQLiteOpenHelper
    (context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE ${Constants.ENTRENOS} " +
                "(${Constants.PROPERTY_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${Constants.PROPERTY_TIPO} VARCHAR(45)," +
                "${Constants.PROPERTY_PORCENTAJE} REAL," +
                "${Constants.PROPERTY_DATE} VARCHAR(20))"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun insertEntreno(tipo: String, porcentaje: Double, fecha: String) : Long{
        val database = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(Constants.PROPERTY_TIPO, tipo)
            put(Constants.PROPERTY_PORCENTAJE, porcentaje)
            put(Constants.PROPERTY_DATE, fecha)
        }

        val resultId = database.insert(Constants.ENTRENOS,null,contentValues)
        return resultId
    }
}