package com.example.gianlucariverabiagioni.proyectoapps.classes

class Club {
    lateinit var nombre: String
    lateinit var encargado: Estudiante
    lateinit var descripcion: String
    var horario: Horario = Horario()

    constructor(){}

    constructor(nombre: String, encargado: Estudiante, descripcion: String) {
        this.nombre = nombre
        this.encargado = encargado
        this.descripcion = descripcion
    }
}