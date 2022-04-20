package com.example.runimprove

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper
    (context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION) {

    /**
     * @author Alejandro Escalona García
     * @constructor Crea una base de datos.
    */
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

    /**
     * @author Alejandro Escalona García
     * Devuelve todos los entrenos
     */
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

    /**
     * @author Alejandro Escalona García
     * @param Tipo : String = Tipo del entreno
     * @param porcentaje : Double = Porcentaje del entreno completado sobre 100
     * @param fecha : String = Fecha del entreno
     * Inserta un entreno a la base de datos
     */
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

    /**
     * @author Alejandro Escalona García
     * @param entreno: Entreno que se va a borrar
     * Borra un entreno de la base de datos
     */
    fun deleteEntreno(entreno : Entreno): Boolean{
        val database = this.writableDatabase
        val result = database.delete(Constants.ENTRENOS,
            "${Constants.PROPERTY_ID} = ${entreno.id}",null)

        return result == 1
    }

    /**
     * @author Alejandro Escalona García
     * Borra Todos los entrenos de la base de datos
     */
    fun deleteAllEntreno(): Boolean{
        val database = this.writableDatabase
        val result = database.delete(Constants.ENTRENOS,
            null,null)
        return result == 1
    }

    /**
     * @author Alejandro Escalona García
     * Devuelve una lista con los porcentajes por tipo de entreno
     */
    @SuppressLint("Recycle", "Range")
    fun stadistic():MutableList<Int>{
        var cantidad =mutableListOf<Int>()
        cantidad.addAll(listOf(0,0,0,0,0,0))

        val database = this.readableDatabase
        val query = "SELECT * FROM ${Constants.ENTRENOS}"
        val result = database.rawQuery(query,null)

        if(result.moveToFirst()){
            do {
                when {
                    result.getString(result.getColumnIndex(Constants.PROPERTY_TIPO)) == "Sprints" -> {
                        cantidad[0] = cantidad[0] + 1
                    }
                    result.getString(result.getColumnIndex(Constants.PROPERTY_TIPO)) == "Hit" -> {
                        cantidad[1] = cantidad[1] + 1
                    }
                    result.getString(result.getColumnIndex(Constants.PROPERTY_TIPO)) == "Descents" -> {
                        cantidad[2] = cantidad[2] + 1
                    }
                    result.getString(result.getColumnIndex(Constants.PROPERTY_TIPO)) == "Bajadas" -> {
                        cantidad[2] = cantidad[2] + 1
                    }
                    result.getString(result.getColumnIndex(Constants.PROPERTY_TIPO)) == "Endurance" -> {
                        cantidad[3] = cantidad[3] + 1
                    }
                    result.getString(result.getColumnIndex(Constants.PROPERTY_TIPO)) == "Resistencia" -> {
                        cantidad[3] = cantidad[3] + 1
                    }
                    result.getString(result.getColumnIndex(Constants.PROPERTY_TIPO)) == "Técnica" -> {
                        cantidad[4] = cantidad[4] + 1
                    }
                    result.getString(result.getColumnIndex(Constants.PROPERTY_TIPO)) == "Technique" -> {
                        cantidad[4] = cantidad[4] + 1
                    }
                    result.getString(result.getColumnIndex(Constants.PROPERTY_TIPO)) == "Stretching" -> {
                        cantidad[5] = cantidad[5] + 1
                    }
                    result.getString(result.getColumnIndex(Constants.PROPERTY_TIPO)) == "Estiramientos" -> {
                        cantidad[5] = cantidad[5] + 1
                    }
                }
            }while (result.moveToNext())
        }
        return cantidad
    }
}