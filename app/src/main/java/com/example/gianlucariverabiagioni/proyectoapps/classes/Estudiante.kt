package com.example.gianlucariverabiagioni.proyectoapps.classes

class Estudiante {
    var nombre: String
    var carne: String
    var correo: String
    var contrasena: String
    var horario: Horario
    var tutor: Boolean

    constructor(nombre: String, carne: String, correo: String, contrasena: String, horario: Horario) {
        this.nombre = nombre
        this.carne = carne
        this.correo = correo
        this.contrasena = contrasena
        this.horario = horario
        this.tutor = false
    }

}