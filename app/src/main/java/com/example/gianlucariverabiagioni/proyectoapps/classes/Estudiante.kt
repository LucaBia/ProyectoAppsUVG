package com.example.gianlucariverabiagioni.proyectoapps.classes

class Estudiante {
    var nombre: String
    var carne: Int
    var correo: String
    var horario: Horario
    var tutor: Boolean

    constructor(nombre: String, carne: Int, correo: String, horario: Horario) {
        this.nombre = nombre
        this.carne = carne
        this.correo = correo
        this.horario = horario
        this.tutor = false
    }

}