package com.example.gianlucariverabiagioni.proyectoapps.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.gianlucariverabiagioni.proyectoapps.R
import com.example.gianlucariverabiagioni.proyectoapps.classes.Horario
import android.support.v4.content.ContextCompat.getSystemService
import com.example.gianlucariverabiagioni.proyectoapps.classes.Curso


class CursoAdapter(context: Context, horario: Horario) : BaseAdapter() {

    private var context: Context
    private var dataHorario: ArrayList<Curso?>

    /*constructor(context: Context, dataHorario: Horario) {
        //this.context = context
        this.dataHorario = dataHorario.toArray()
    }*/

    init {
        this.context = context
        this.dataHorario = horario.toArray()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var convertView1: View

        if (convertView == null) {
            val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            convertView1 = layoutInflater.inflate(R.layout.curso_item, null, false)
            //val convertView1 = LayoutInflater.from(parent!!.context).inflate(R.layout.curso_item, parent, false)
            //return convertView1
        } else {
            convertView1 = convertView
        }

        var textViewName: TextView = convertView1.findViewById(R.id.text_view_nombre) as TextView
        var textViewSalon: TextView = convertView1.findViewById(R.id.text_view_salon) as TextView
        textViewName.text = dataHorario[position]?.nombre
        textViewSalon.text = dataHorario[position]?.salon
        return convertView1
    }

    override fun getItem(position: Int): Any? {
        return dataHorario[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataHorario.size
    }

}
