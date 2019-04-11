package com.example.gianlucariverabiagioni.proyectoapps.classes

import android.media.Image

class Estudiante {
    lateinit var nombre: String
    lateinit var carne: String
    lateinit var correo: String
    lateinit var contrasena: String
    lateinit var horario: Horario
    var tutor: Boolean = false
    var image: Int = 0

    constructor(name: String, carne: String, email: String, password: String){
        this.nombre = name
        this.carne = carne
        this.correo = email
        this.contrasena = password
        this.horario = Horario()
    }

    constructor(name: String, carne: String, email: String, password: String, horario: Horario){
        this.nombre = name
        this.carne = carne
        this.correo = email
        this.contrasena = password
        this.horario = horario
    }

    constructor(){

    }

    fun toMap(): Map<String, Any>{
        val estudianteHashMap = HashMap<String, Any>()
        estudianteHashMap.put("nombre", nombre)
        estudianteHashMap.put("carne", carne)
        estudianteHashMap.put("correo", correo)
        estudianteHashMap.put("horario", horario.toMap())
        estudianteHashMap.put("tutor", tutor)
        return estudianteHashMap
    }
}