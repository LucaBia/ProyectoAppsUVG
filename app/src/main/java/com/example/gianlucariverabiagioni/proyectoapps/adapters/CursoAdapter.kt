package com.example.gianlucariverabiagioni.proyectoapps.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.gianlucariverabiagioni.proyectoapps.R
import com.example.gianlucariverabiagioni.proyectoapps.classes.Horario

class CursoAdapter : BaseAdapter {

    private var context: Context
    private var dataHorario: ArrayList<String?>

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null) {
            val convertView1 = LayoutInflater.from(parent!!.context).inflate(R.layout.curso_item, parent, false)
            return convertView1
        }

        var textViewName: TextView = convertView.findViewById(R.id.text_view_nombre) as TextView
        var textViewSalon: TextView = convertView.findViewById(R.id.text_view_salon) as TextView
        textViewName.text = dataHorario[position]

        return convertView
    }

    constructor(context: Context, dataHorario: Horario) {
        this.context = context
        this.dataHorario = dataHorario.toArray()
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