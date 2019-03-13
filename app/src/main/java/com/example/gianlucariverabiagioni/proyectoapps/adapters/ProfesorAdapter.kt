package com.example.gianlucariverabiagioni.proyectoapps.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.gianlucariverabiagioni.proyectoapps.R
import com.example.gianlucariverabiagioni.proyectoapps.TutoriasActivity
import com.example.gianlucariverabiagioni.proyectoapps.classes.Profesor


class ProfesorAdapter: RecyclerView.Adapter<ProfesorAdapter.ProfesorViewHolder> {
    private var dataSet: ArrayList<Profesor>

    class ProfesorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textViewName: TextView
        internal var textViewCorreo: TextView

        init {
            this.textViewName = itemView.findViewById(R.id.text_view_nombre) as TextView
            this.textViewCorreo = itemView.findViewById(R.id.text_view_correo) as TextView
        }
    }

    constructor(data: ArrayList<Profesor>) {
        this.dataSet = data
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfesorViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.profesor_item, parent, false)

        view.setOnClickListener(TutoriasActivity.myOnClickListener)

        return ProfesorViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfesorViewHolder, listPosition: Int) {

        val textViewName = holder.textViewName
        val textViewVersion = holder.textViewCorreo

        textViewName.setText(dataSet[listPosition].nombre)
        textViewVersion.setText(dataSet[listPosition].correo)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}