package com.example.gianlucariverabiagioni.proyectoapps.classes

import android.media.Image

class Estudiante {
    var nombre: String
    var carne: String
    var correo: String
    var contrasena: String
    var horario: Horario
    var tutor: Boolean
    var image: Int

    constructor(nombre: String, carne: String, correo: String, contrasena: String, horario: Horario) {
        this.nombre = nombre
        this.carne = carne
        this.correo = correo
        this.contrasena = contrasena
        this.horario = horario
        this.tutor = false
        this.image = 0
    }

}