package com.example.gianlucariverabiagioni.proyectoapps.classes

class Profesor {
    var nombre: String
    var correo: String
    var horario: Horario

    constructor(nombre: String, correo: String, horario: Horario) {
        this.nombre = nombre
        this.correo = correo
        this.horario = horario
    }
}