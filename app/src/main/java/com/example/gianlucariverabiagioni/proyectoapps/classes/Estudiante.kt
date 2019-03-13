package com.example.gianlucariverabiagioni.proyectoapps.classes

import android.media.Image

class Estudiante {
    var nombre: String
    var carne: String
    var correo: String
    var contrasena: String
    var horario: HashMap<Int, Horario> = hashMapOf(1 to Horario())
    var tutor: Boolean = false
    var image: Int = 0

    constructor(name: String, carne: String, email: String, password: String){
        this.nombre = name
        this.carne = carne
        this.correo = email
        this.contrasena = password
    }

    constructor(name: String, carne: String, email: String, password: String, horario: HashMap<Int, Horario>){
        this.nombre = name
        this.carne = carne
        this.correo = email
        this.contrasena = password
        this.horario = horario
    }
}