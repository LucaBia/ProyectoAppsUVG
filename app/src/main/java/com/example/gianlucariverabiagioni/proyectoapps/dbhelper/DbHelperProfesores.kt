package com.example.gianlucariverabiagioni.proyectoapps.dbhelper

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.gianlucariverabiagioni.proyectoapps.classes.Profesor
import com.example.gianlucariverabiagioni.proyectoapps.classes.Horario
import java.io.ByteArrayOutputStream
import java.io.ObjectOutputStream

class DbHelperProfesores(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VER) {
    companion object {
        private val DATABASE_VER = 1
        private val DATABASE_NAME = "ProfesorDB.db"

        //Table
        private val TABLE_NAME = "Profesores"
        private val COL_NOMBRE = "Nombre"
        private val COL_CORREO = "Correo"
        private val COL_HORARIO = "Horario"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        val CREATE_TABLE_QUERY: String = ("CREATE TABLE $TABLE_NAME ($COL_NOMBRE TEXT, $COL_CORREO TEXT $COL_HORARIO BLOB)")
        db!!.execSQL(CREATE_TABLE_QUERY)
        addProfesor(Profesor("Pedro Luis Alfonso Lopez", "palf@uvg.edu.gt", Horario()))
        addProfesor(Profesor("Diego Alejandro Enriquez Rodriguez", "dalef@uvg.edu.gt", Horario()))
        addProfesor(Profesor("Magda Fabiola Moscoso Arriola", "mmos@uvg.edu.gt", Horario()))
        addProfesor(Profesor("Zayda Rita Pérez Zubillaga", "zper@uvg.edu.gt", Horario()))
        addProfesor(Profesor("Martha Ligia Naranjo Franky", "mnar@uvg.edu.gt", Horario()))
        addProfesor(Profesor("Oscar Estuardo Gil Sanchez", "ogil@uvg.edu.gt", Horario()))
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db!!.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db!!)
    }

    //CRUD

    val allProfesores: List<Profesor>
        get() {
            val lstProfesores = ArrayList<Profesor>()
            val selectQuery = "SELECT * FROM $TABLE_NAME"
            val db: SQLiteDatabase = this.writableDatabase
            val cursor: Cursor = db.rawQuery(selectQuery, null)

            if (cursor.moveToFirst()) {
                do {
                    val nombre = cursor.getString(cursor.getColumnIndex(COL_NOMBRE))
                    val correo = cursor.getString(cursor.getColumnIndex(COL_CORREO))
                    val horarioByteArray = cursor.getBlob(cursor.getColumnIndex(COL_HORARIO))
                    val horario = BytesUtil.toObject(horarioByteArray) as Horario
                    val profesor = Profesor(nombre, correo, horario)

                    lstProfesores.add(profesor)
                } while (cursor.moveToNext())
            }
            db.close()
            return lstProfesores
        }

    fun addProfesor(profesor: Profesor) {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        values.put(COL_NOMBRE, profesor.nombre)
        values.put(COL_CORREO, profesor.correo)
        val horarioByteArray = BytesUtil.toByteArray(profesor.horario)
        values.put(COL_HORARIO, horarioByteArray)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateProfesor(profesor: Profesor): Int {
        val db: SQLiteDatabase = this.writableDatabase
        val values = ContentValues()
        val horarioByteArray = BytesUtil.toByteArray(profesor.horario)
        values.put(COL_HORARIO, horarioByteArray)
        return db.update(TABLE_NAME,values, "$COL_NOMBRE=?", arrayOf(profesor.nombre.toString()))

    }

    fun deleteProfesor(profesor: Profesor) {
        val db: SQLiteDatabase = this.writableDatabase
        db.delete(TABLE_NAME, "$COL_NOMBRE=?", arrayOf(profesor.nombre.toString()))
        db.close()
    }

}