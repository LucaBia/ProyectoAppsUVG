package com.example.gianlucariverabiagioni.proyectoapps.classes

class Club {
    lateinit var nombre: String
    lateinit var horario: Horario
    lateinit var encargado: Estudiante
    lateinit var descripcion: String

    constructor(){}

    constructor(nombre: String, horario: Horario, encargado: Estudiante, descripcion: String) {
        this.nombre = nombre
        this.horario = horario
        this.encargado = encargado
        this.descripcion = descripcion
    }
}