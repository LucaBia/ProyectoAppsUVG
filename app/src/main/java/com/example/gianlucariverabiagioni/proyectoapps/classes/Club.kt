package com.example.gianlucariverabiagioni.proyectoapps.classes

class Club {
    var nombre: String
    var horario: Horario
    var encargado: Estudiante
    var descripcion: String

    constructor(nombre: String, horario: Horario, encargado: Estudiante, descripcion: String) {
        this.nombre = nombre
        this.horario = horario
        this.encargado = encargado
        this.descripcion = descripcion
    }
}