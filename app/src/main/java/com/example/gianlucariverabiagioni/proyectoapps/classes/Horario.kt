package com.example.gianlucariverabiagioni.proyectoapps.classes

class Horario{
    val horas : List<Periodo> = arrayListOf(
        Periodo(1 ,"7:00", "7:45"),
        Periodo(2, "7:50", "8:35"),
        Periodo(3, "8:40", "9:25"),
        Periodo(4, "9:30", "10:15"),
        Periodo(5, "10:40", "11:25"),
        Periodo(6, "11:30", "12:15"),
        Periodo(7, "12:20", "1:05")
    )

    lateinit var lunes: Map<Periodo, Curso?>
    lateinit var martes: Map<Periodo, Curso?>
    lateinit var miercoles: Map<Periodo, Curso?>
    lateinit var jueves: Map<Periodo, Curso?>
    lateinit var viernes: Map<Periodo, Curso?>

    constructor(){
        for (i in horas){
            this.lunes = hashMapOf( i to null)
            this.martes = hashMapOf( i to null)
            this.miercoles = hashMapOf( i to null)
            this.jueves = hashMapOf( i to null)
            this.viernes = hashMapOf( i to null)
        }
    }





}