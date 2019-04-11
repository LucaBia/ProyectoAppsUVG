package com.example.gianlucariverabiagioni.proyectoapps.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.gianlucariverabiagioni.proyectoapps.R
import com.example.gianlucariverabiagioni.proyectoapps.TutoriasActivity
import com.example.gianlucariverabiagioni.proyectoapps.classes.Curso

class CursoAdapter : BaseAdapter {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        if (convertView == null) {
            val convertView1 = LayoutInflater.from(parent!!.context).inflate(R.layout.curso_item, parent, false)
            return convertView1
        }
        return convertView
    }

    private var context: Context
    private var dataHorario: ArrayList<String>

    constructor(context: Context, dataHorario: ArrayList<String>) {
        this.context = context
        this.dataHorario = dataHorario
    }

    override fun getItem(position: Int): Any {
        return dataHorario.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return dataHorario.size
    }

}



/*
class CursoAdapter: RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {
    private var dataSet: ArrayList<Curso>

    class CursoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textViewName: TextView
        internal var textViewSalon: TextView

        init {
            this.textViewName = itemView.findViewById(R.id.text_view_nombre) as TextView
            this.textViewSalon = itemView.findViewById(R.id.text_view_salon) as TextView
        }
    }

    constructor(data: ArrayList<Curso>) {
        this.dataSet = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CursoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.curso_item, parent, false)

        view.setOnClickListener(TutoriasActivity.myOnClickListener)

        return CursoViewHolder(view)
    }

    override fun onBindViewHolder(holder: CursoViewHolder, listPosition: Int) {

        val textViewName = holder.textViewName
        val textViewVersion = holder.textViewSalon

        textViewName.setText(dataSet[listPosition].nombre)
        textViewVersion.setText(dataSet[listPosition].salon)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}*/