package com.example.gianlucariverabiagioni.proyectoapps

import android.content.Intent
import android.net.Uri
import android.content.Context
import android.os.Bundle
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
import com.example.gianlucariverabiagioni.proyectoapps.adapters.ProfesorAdapter
import com.example.gianlucariverabiagioni.proyectoapps.adapters.ProfesoresData
import com.example.gianlucariverabiagioni.proyectoapps.classes.Profesor
import kotlinx.android.synthetic.main.activity_profesores.*
import android.support.v4.widget.DrawerLayout
import android.view.Gravity


class ProfesoresActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var telefonoEmergencia: Int =  59781736

    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private var data: ArrayList<Profesor>? = null
    private var mDrawerLayout: DrawerLayout? = null

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
            val intentAtras = Intent(this, HomeActivity::class.java)
            startActivity(intentAtras)
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
                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_atencionProfesores -> {
                mDrawerLayout?.closeDrawers()

            }
            R.id.nav_tutorias -> {
                val intent3 = Intent(this, TutoriasActivity::class.java)
                startActivity(intent3)

            }
            R.id.nav_clubes -> {
                val intent4 = Intent(this, ClubesActivity::class.java)
                startActivity(intent4)

            }
            R.id.nav_biblioteca -> {
                val intent5 = Intent(this, BibliotecaActivity::class.java)
                startActivity(intent5)

            }
            R.id.nav_emergencia -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:"+telefonoEmergencia)
                startActivity(intent)
            }
            R.id.nav_configuracion -> {
                val intent6 = Intent(this, ConfiguracionActivity::class.java)
                startActivity(intent6)
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
