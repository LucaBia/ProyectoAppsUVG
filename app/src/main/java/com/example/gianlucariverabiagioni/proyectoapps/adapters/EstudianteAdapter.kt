package com.example.gianlucariverabiagioni.proyectoapps.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.gianlucariverabiagioni.proyectoapps.R
import com.example.gianlucariverabiagioni.proyectoapps.TutoriasActivity
import com.example.gianlucariverabiagioni.proyectoapps.classes.Estudiante


class EstudianteAdapter : RecyclerView.Adapter<EstudianteAdapter.EstudianteViewHolder> {
    private var dataSet: ArrayList<Estudiante>

    class EstudianteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        internal var textViewName: TextView
        internal var textViewVersion: TextView
        internal var imageViewIcon: ImageView

        init {
            this.textViewName = itemView.findViewById(R.id.text_view_nombre) as TextView
            this.textViewVersion = itemView.findViewById(R.id.text_view_correo) as TextView
            this.imageViewIcon = itemView.findViewById<View>(R.id.image_view) as ImageView
        }
    }

    constructor(data: ArrayList<Estudiante>) {
        this.dataSet = data
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): EstudianteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.estudiante_item, parent, false)

        view.setOnClickListener(TutoriasActivity.myOnClickListener)

        return EstudianteViewHolder(view)
    }

    override fun onBindViewHolder(holder: EstudianteViewHolder, listPosition: Int) {

        val textViewName = holder.textViewName
        val textViewVersion = holder.textViewVersion
        val imageView = holder.imageViewIcon

        textViewName.setText(dataSet[listPosition].nombre)
        textViewVersion.setText(dataSet[listPosition].correo)
        imageView.setImageResource(dataSet[listPosition].image)
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }
}