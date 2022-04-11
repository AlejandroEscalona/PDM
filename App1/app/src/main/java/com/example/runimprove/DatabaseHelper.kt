package com.example.runimprove

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

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

    @SuppressLint("Range")
    fun getAllEntrenos(): MutableList<Entreno>{
        val entrenos : MutableList<Entreno> = mutableListOf()

        val database = this.readableDatabase
        val query = "SELECT * FROM ${Constants.ENTRENOS}"

        val result = database.rawQuery(query,null)

        if(result.moveToFirst()){
            do {
                val entreno = Entreno()
                entreno.id = result.getLong(result.getColumnIndex(Constants.PROPERTY_ID))
                entreno.tipo = result.getString(result.getColumnIndex(Constants.PROPERTY_TIPO))
                entreno.porcentaje = result.getDouble(result.getColumnIndex(Constants.PROPERTY_PORCENTAJE))
                entreno.fecha = result.getString(result.getColumnIndex(Constants.PROPERTY_DATE))

                entrenos.add(entreno)
            }while (result.moveToNext())
        }

        return entrenos
    }

    fun insertEntreno( tipo: String, porcentaje: Double, fecha: String) : Long{
        val database = this.writableDatabase
        val contentValues = ContentValues().apply {
            put(Constants.PROPERTY_TIPO, tipo)
            put(Constants.PROPERTY_PORCENTAJE, porcentaje)
            put(Constants.PROPERTY_DATE, fecha)
        }

        val resultId = database.insert(Constants.ENTRENOS,null,contentValues)
        return resultId
    }

    fun deleteEntreno(entreno : Entreno): Boolean{
        val database = this.writableDatabase
        val result = database.delete(Constants.ENTRENOS,
            "${Constants.PROPERTY_ID} = ${entreno.id}",null)

        return result == 1
    }

    fun deleteAllEntreno(): Boolean{
        val database = this.writableDatabase
        val result = database.delete(Constants.ENTRENOS,
            null,null)

        return result == 1
    }


}