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

    val lunes : List<Curso?> = ArrayList()
    val martes : List<Curso?> = ArrayList()
    val miercoles : List<Curso?> = ArrayList()
    val jueves : List<Curso?> = ArrayList()
    val viernes : List<Curso?> = ArrayList()


}