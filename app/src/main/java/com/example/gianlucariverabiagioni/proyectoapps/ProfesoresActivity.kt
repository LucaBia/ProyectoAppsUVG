package com.example.gianlucariverabiagioni.proyectoapps

import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.gianlucariverabiagioni.proyectoapps.adapters.EstudianteAdapter
import com.example.gianlucariverabiagioni.proyectoapps.adapters.ProfesorAdapter
import com.example.gianlucariverabiagioni.proyectoapps.adapters.ProfesoresData
import com.example.gianlucariverabiagioni.proyectoapps.adapters.TutoresData
import com.example.gianlucariverabiagioni.proyectoapps.classes.Estudiante
import com.example.gianlucariverabiagioni.proyectoapps.classes.Profesor
import kotlinx.android.synthetic.main.activity_profesores.*
import kotlinx.android.synthetic.main.app_bar_profesores.*

class ProfesoresActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private var data: ArrayList<Profesor>? = null

    companion object {
        @JvmStatic var myOnClickListener: View.OnClickListener? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profesores)
        /*setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        ProfesoresActivity.myOnClickListener = ProfesoresActivity.MyOnClickListener(this)

        recyclerView = (findViewById(R.id.recyclerView) as RecyclerView)
        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView!!.setLayoutManager(layoutManager)
        recyclerView!!.setItemAnimator(DefaultItemAnimator())

        data = ArrayList<Profesor>()
        for (i in 0 until ProfesoresData.nameArray.size) {
            data!!.add(
                Profesor(
                    ProfesoresData.nameArray[i],
                    ProfesoresData.correoArray[i]
                )
            )
        }

        adapter = ProfesorAdapter(data!!)
        recyclerView?.setAdapter(adapter)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.profesores, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.
        when (item.itemId) {
            R.id.nav_horario -> {
                // Handle the camera action
            }
            R.id.nav_atencionProfesores -> {

            }
            R.id.nav_tutorias -> {

            }
            R.id.nav_clubes -> {

            }
            R.id.nav_biblioteca -> {

            }
            R.id.nav_emergencia -> {

            }
            R.id.nav_configuracion -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    private class MyOnClickListener (private val context: Context) : View.OnClickListener {

        override fun onClick(v: View) {
            Toast.makeText(context, "Hola" , Toast.LENGTH_SHORT).show()
        }
    }
}
