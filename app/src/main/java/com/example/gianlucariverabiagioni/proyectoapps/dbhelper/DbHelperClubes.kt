package com.example.gianlucariverabiagioni.proyectoapps.dbhelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.gianlucariverabiagioni.proyectoapps.classes.Club
import com.example.gianlucariverabiagioni.proyectoapps.classes.Estudiante
import com.example.gianlucariverabiagioni.proyectoapps.classes.Horario
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream

class DbHelperClubes (context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "ClubDB.db"

        //Table
        private val TABLE_NAME = "Clubs"
        private val COL_NOMBRE = "Nombre"
        private val COL_HORARIO = "Horario"
        private val COL_ENCARGADO = "Encargado"
        private val COL_DESCRIPCION = "Descripcion"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String = ("CREATE TABLE $TABLE_NAME ($COL_NOMBRE TEXT, $COL_HORARIO STRING, $COL_ENCARGADO TEXT, $COL_DESCRIPCION TEXT, $COL_HORARIO BLOB)")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD

    val allClubs: List<Club>
        get() {
            val lstClubs = ArrayList<Club>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db: SQLiteDatabase = this.writableDatabase
            val cursor: Cursor = db.rawQuery(selectQuery, null)

            if (cursor.moveToFirst()) {
                do {
                    val nombre = cursor.getString(cursor.getColumnIndex(COL_NOMBRE))
                    val encargadoByteArray = cursor.getBlob(cursor.getColumnIndex(COL_ENCARGADO))
                    val descripcion = cursor.getString(cursor.getColumnIndex(COL_DESCRIPCION))
                    val horarioByteArray = cursor.getBlob(cursor.getColumnIndex(COL_HORARIO))
                    val encargado = BytesUtil.toObject(encargadoByteArray) as Estudiante
                    val horario = BytesUtil.toObject(horarioByteArray) as Horario
                    val club = Club(nombre, horario, encargado, descripcion)

                    lstClubs.add(club)
                } while (cursor.moveToNext())
            }
            db.close()
            return lstClubs
        }

    fun addClub(club: Club) {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NOMBRE, club.nombre)
        values.put(COL_DESCRIPCION, club.descripcion)
        val horarioByteArray = BytesUtil.toByteArray(club.horario)
        values.put(COL_HORARIO, horarioByteArray)
        val encargadoByteArray = BytesUtil.toByteArray(club.encargado)
        values.put(COL_ENCARGADO, encargadoByteArray)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateClub(club: Club): Int {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        val encargadoByteArray = BytesUtil.toByteArray(club.horario)
        values.put(COL_ENCARGADO, encargadoByteArray)
        val ba = ByteArrayOutputStream()
        val oos = ObjectOutputStream(ba)
        oos.writeObject(club.horario)
        val arrayBytes = ba.toByteArray()
        values.put(COL_HORARIO, arrayBytes)
        return db.update(TABLE_NAME,values, "$COL_NOMBRE=?", arrayOf(club.nombre.toString()))

    }

    fun deleteClub(club: Club) {
        val db: SQLiteDatabase = this.writableDatabase
        db.delete(TABLE_NAME, "$COL_NOMBRE=?", arrayOf(club.nombre.toString()))
        db.close()
    }
}