package com.example.gianlucariverabiagioni.proyectoapps.classes

import android.content.ContentValues.TAG
import android.util.Log

class Horario{

    val horas : List<Periodo> = arrayListOf(
        Periodo(0 ,"Horas", ""),
        Periodo(1 ,"7:00", "7:45"),
        Periodo(2, "7:50", "8:35"),
        Periodo(3, "8:40", "9:25"),
        Periodo(4, "9:30", "10:15"),
        Periodo(5, "10:40", "11:25"),
        Periodo(6, "11:30", "12:15"),
        Periodo(7, "12:20", "1:05")
    )

    var horasP: MutableMap<Int, Curso?> = hashMapOf()
    var lunes: MutableMap<Int, Curso?> = hashMapOf()
    var martes: MutableMap<Int, Curso?> = hashMapOf()
    var miercoles: MutableMap<Int, Curso?> = hashMapOf()
    var jueves: MutableMap<Int, Curso?> = hashMapOf()
    var viernes: MutableMap<Int, Curso?> = hashMapOf()

    init {
        for (i in horas.indices){
            this.horasP = hashMapOf( i to null)
            this.lunes = hashMapOf( i to null)
            this.martes = hashMapOf( i to null)
            this.miercoles = hashMapOf( i to null)
            this.jueves = hashMapOf( i to null)
            this.viernes = hashMapOf( i to null)
        }

        for (i in horas.indices) {
            this.horasP[i] = Curso(horas[i].inicioHora, horas[i].finalHora)
        }

        this.lunes[0] = Curso("Lunes", "")
        this.martes[0] = Curso("Martes", "")
        this.miercoles[0] = Curso("Miercoles", "")
        this.jueves[0] = Curso("Jueves", "")
        this.viernes[0] = Curso("Viernes", "")
        //Log.i(TAG, "HOLAA" + horasP)
    }

    fun addCurso(dia: String, num: Int, nombre: String, salon: String) {
        when (dia) {
            "Lunes" -> this.lunes[num] = Curso(nombre, salon)
            "Martes" -> this.martes[num] = Curso(nombre, salon)
            "Miercoles" -> this.miercoles[num] = Curso(nombre, salon)
            "Jueves" -> this.jueves[num] = Curso(nombre, salon)
            else -> this.viernes[num] = Curso(nombre, salon)
        }
    }

    fun deleteCurso(dia: String, num: Int) {
        when (dia) {
            "Lunes" -> this.lunes[num] = null
            "Martes" -> this.martes[num] = null
            "Miercoles" -> this.miercoles[num] = null
            "Jueves" -> this.jueves[num] = null
            else -> this.viernes[num] = null
        }
    }

    fun toMap(): Map<String, Any>{
        val horarioHashMap = HashMap<String, Any>()
        horarioHashMap.put("Horas", horasP.toString())
        horarioHashMap.put("Lunes", lunes.toString())
        horarioHashMap.put("Martes", martes.toString())
        horarioHashMap.put("Miercoles", miercoles.toString())
        horarioHashMap.put("Jueves", jueves.toString())
        horarioHashMap.put("Viernes", viernes.toString())
        return horarioHashMap
    }

    fun toArray(): ArrayList<Curso?> {
        val horarioArray = ArrayList<Curso?>()

        for (i in horas.indices){
            horarioArray.add(horasP[i])
            horarioArray.add(lunes[i])
            horarioArray.add(martes[i])
            horarioArray.add(miercoles[i])
            horarioArray.add(jueves[i])
            horarioArray.add(viernes[i])
        }
        Log.i(TAG, "HOLAA" + horarioArray)
        return horarioArray
    }

}