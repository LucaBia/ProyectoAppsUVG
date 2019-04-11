package com.example.gianlucariverabiagioni.proyectoapps.classes

import android.content.ContentValues.TAG
import android.util.Log

class Horario{

    val horas : List<Periodo> = arrayListOf(
        Periodo(0 ,"", ""),
        Periodo(1 ,"7:00", "7:45"),
        Periodo(2, "7:50", "8:35"),
        Periodo(3, "8:40", "9:25"),
        Periodo(4, "9:30", "10:15"),
        Periodo(5, "10:40", "11:25"),
        Periodo(6, "11:30", "12:15"),
        Periodo(7, "12:20", "1:05")
    )

    lateinit var horasP: MutableMap<Int, Curso?>
    lateinit var lunes: MutableMap<Int, Curso?>
    lateinit var martes: MutableMap<Int, Curso?>
    lateinit var miercoles: MutableMap<Int, Curso?>
    lateinit var jueves: MutableMap<Int, Curso?>
    lateinit var viernes: MutableMap<Int, Curso?>

    init {
        for (i in 0..(horas.size-1)){
            this.horasP = hashMapOf( i to Curso(horas[i].inicioHora, horas[i].finalHora))
            //this.horasP = hashMapOf( i to null)
            this.lunes = hashMapOf( i to null)
            this.martes = hashMapOf( i to null)
            this.miercoles = hashMapOf( i to null)
            this.jueves = hashMapOf( i to null)
            this.viernes = hashMapOf( i to null)
        }

        this.horasP[0] = Curso("Horas", "")
        this.lunes[0] = Curso("Lunes", "")
        this.martes[0] = Curso("Martes", "")
        this.miercoles[0] = Curso("Miercoles", "")
        this.jueves[0] = Curso("Jueves", "")
        this.viernes[0] = Curso("Viernes", "")
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

    fun toArray(): ArrayList<String?> {
        val horarioArray = ArrayList<String?>()

        for (i in 0..(horas.size-1)){
            horarioArray.add(horasP[i]?.nombre)
            horarioArray.add(lunes[i]?.nombre)
            horarioArray.add(martes[i]?.nombre)
            horarioArray.add(miercoles[i]?.nombre)
            horarioArray.add(jueves[i]?.nombre)
            horarioArray.add(viernes[i]?.nombre)
        }

        print(horarioArray)
        Log.i(TAG, "HOLAA" + horarioArray)

        return horarioArray
    }

}