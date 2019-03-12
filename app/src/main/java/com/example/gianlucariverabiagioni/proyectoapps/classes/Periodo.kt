package com.example.gianlucariverabiagioni.proyectoapps.classes

class Periodo {
    val numero: Int
    val inicioHora : String
    val finalHora : String

    constructor(numero: Int, inicioH: String, finalH: String) {
        this.inicioHora = inicioH
        this.finalHora = finalH
        this.numero = numero
    }
}