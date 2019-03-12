package com.example.gianlucariverabiagioni.proyectoapps.classes

class Horario{
    val horas : List<Periodo> = arrayListOf(
        Periodo("7:00", "7:45"),
        Periodo("7:50", "8:35"),
        Periodo("8:40", "9:25"),
        Periodo("9:30", "10:15"),
        Periodo("10:40", "11:25"),
        Periodo("11:30", "12:15"),
        Periodo("12:20", "1:05")
    )

    constructor(){
        for (i in horas){
            val lunes: Map<Periodo, Curso?> = hashMapOf( i to null)
            val martes: Map<Periodo, Curso?> = hashMapOf( i to null)
            val miercoles: Map<Periodo, Curso?> = hashMapOf( i to null)
            val jueves: Map<Periodo, Curso?> = hashMapOf( i to null)
            val viernes: Map<Periodo, Curso?> = hashMapOf( i to null)
        }
    }





}