package com.example.gianlucariverabiagioni.proyectoapps.classes

class Horario{

    val horas : List<Periodo> = arrayListOf(
        Periodo(0 ,"", ""),
        Periodo(1 ,"7:00", "7:45"),
        Periodo(2, "7:50", "8:35"),
        Periodo(3, "8:40", "9:25"),
        Periodo(4, "9:30", "10:15"),
        Periodo(5, "10:40", "11:25"),
        Periodo(6, "11:30", "12:15"),
        Periodo(7, "12:20", "1:05")
    )

    lateinit var horasP: MutableMap<Periodo, Curso?>
    lateinit var lunes: MutableMap<Periodo, Curso?>
    lateinit var martes: MutableMap<Periodo, Curso?>
    lateinit var miercoles: MutableMap<Periodo, Curso?>
    lateinit var jueves: MutableMap<Periodo, Curso?>
    lateinit var viernes: MutableMap<Periodo, Curso?>

    init {
        for (i in horas){
            this.horasP = hashMapOf( i to null)
            this.lunes = hashMapOf( i to null)
            this.martes = hashMapOf( i to null)
            this.miercoles = hashMapOf( i to null)
            this.jueves = hashMapOf( i to null)
            this.viernes = hashMapOf( i to null)
        }

        this.horasP.put(Periodo(0 ,"", ""), Curso("Horas", ""))
        this.lunes.put(Periodo(0 ,"", ""), Curso("Lunes", ""))
        this.martes.put(Periodo(0 ,"", ""), Curso("Martes", ""))
        this.miercoles.put(Periodo(0 ,"", ""), Curso("Miercoles", ""))
        this.jueves.put(Periodo(0 ,"", ""), Curso("Jueves", ""))
        this.viernes.put(Periodo(0 ,"", ""), Curso("Viernes", ""))
    }

    fun toMap(): Map<String, Any>{
        val horarioHashMap = HashMap<String, Any>()
        horarioHashMap.put("Horas", horasP.toString())
        horarioHashMap.put("Lunes", lunes.toString())
        horarioHashMap.put("Martes", martes.toString())
        horarioHashMap.put("Miercoles", miercoles.toString())
        horarioHashMap.put("Jueves", jueves.toString())
        horarioHashMap.put("Viernes", viernes.toString())
        return horarioHashMap
    }

    fun toArray(): ArrayList<String?> {
        var horarioArray = ArrayList<String?>()

        for (i in horas){
            horarioArray.add(horasP.get(i)?.nombre)
            horarioArray.add(lunes.get(i)?.nombre)
            horarioArray.add(martes.get(i)?.nombre)
            horarioArray.add(miercoles.get(i)?.nombre)
            horarioArray.add(jueves.get(i)?.nombre)
            horarioArray.add(viernes.get(i)?.nombre)
        }

        return horarioArray
    }

}