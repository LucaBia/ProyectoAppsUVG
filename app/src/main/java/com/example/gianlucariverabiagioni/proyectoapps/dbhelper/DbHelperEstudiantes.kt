package com.example.gianlucariverabiagioni.proyectoapps.dbhelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.gianlucariverabiagioni.proyectoapps.classes.Estudiante
import com.example.gianlucariverabiagioni.proyectoapps.classes.Horario
import com.example.gianlucariverabiagioni.proyectoapps.dbhelper.BytesUtil.toObject
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream

class DbHelperEstudiantes(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "EstudianteDB.db"

        //Table
        private val TABLE_NAME = "Estudiantes"
        private val COL_NOMBRE = "Nombre"
        private val COL_CARNE = "Carne"
        private val COL_CORREO = "Correo"
        private val COL_CONTRASENA = "Contrasena"
        private val COL_HORARIO = "Horario"
        private val COL_TUTOR = "Tutor"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String = ("CREATE TABLE $TABLE_NAME ($COL_NOMBRE TEXT, $COL_CARNE STRING, $COL_CORREO TEXT, $COL_CONTRASENA TEXT, $COL_HORARIO TEXT, $COL_TUTOR TEXT )")
        db!!.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD

    val allEstudiantes: List<Estudiante>
        get() {
            val lstEstudiantes = ArrayList<Estudiante>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db:SQLiteDatabase = this.writableDatabase
            val cursor: Cursor = db.rawQuery(selectQuery, null)

            if (cursor.moveToFirst()) {
                do {
                    val nombre = cursor.getString(cursor.getColumnIndex(COL_NOMBRE))
                    val carne = cursor.getString(cursor.getColumnIndex(COL_CARNE))
                    val correo = cursor.getString(cursor.getColumnIndex(COL_CORREO))
                    val contrasena = cursor.getString(cursor.getColumnIndex(COL_CONTRASENA))
                    //val horarioByteArray = cursor.getBlob(cursor.getColumnIndex(COL_HORARIO))
                    //val horario = BytesUtil.toObject(horarioByteArray) as Horario
                    val horario = cursor.getString(cursor.getString(cursor.getColumnIndex(COL_HORARIO)).toInt())
                    val estudiante = Estudiante(nombre, carne, correo, contrasena)

                    lstEstudiantes.add(estudiante)
                } while (cursor.moveToNext())
            }
            db.close()
            return lstEstudiantes
        }

    fun addEstudiante(estudiante: Estudiante) {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NOMBRE, estudiante.nombre)
        values.put(COL_CARNE, estudiante.carne)
        values.put(COL_CORREO, estudiante.correo)
        values.put(COL_CONTRASENA, estudiante.contrasena)
        //val horarioByteArray = BytesUtil.toByteArray(estudiante.horario)
        //values.put(COL_HORARIO, horarioByteArray)
        val horarioString = estudiante.horario.toString()
        values.put(COL_HORARIO, horarioString)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateEstudiante(estudiante: Estudiante): Int {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COL_CONTRASENA, estudiante.contrasena)
        //val horarioByteArray = BytesUtil.toByteArray(estudiante.horario)
        //values.put(COL_HORARIO, horarioByteArray)
        val horarioString = estudiante.horario.toString()
        values.put(COL_HORARIO, horarioString)
        return db.update(TABLE_NAME,values, "$COL_CARNE=?", arrayOf(estudiante.carne.toString()))

    }

    fun deleteEstudiante(estudiante: Estudiante) {
        val db: SQLiteDatabase = this.writableDatabase
        db.delete(TABLE_NAME, "$COL_CARNE=?", arrayOf(estudiante.carne.toString()))
        db.close()
    }

    fun checkUser(email: String, password: String): Boolean {
        for (i in this.allEstudiantes) {
            if (i.correo == email) {
                if (i.contrasena == password) {
                    return true
                }
            }
        }
        return false
    }

}

