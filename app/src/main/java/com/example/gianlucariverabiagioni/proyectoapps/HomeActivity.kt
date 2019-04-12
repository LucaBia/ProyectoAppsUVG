package com.example.gianlucariverabiagioni.proyectoapps

import android.app.Dialog
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.GridView
import android.widget.Toast
import com.example.gianlucariverabiagioni.proyectoapps.classes.Horario
import kotlinx.android.synthetic.main.activity_home.*
import kotlinx.android.synthetic.main.app_bar_home.*
import com.example.gianlucariverabiagioni.proyectoapps.adapters.CursoAdapter
import android.support.v4.os.HandlerCompat.postDelayed
import android.support.v7.app.AlertDialog
import android.widget.EditText
import android.widget.ImageButton


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    val miHorario : Horario = Horario()
    var telefonoEmergencia: Int =  59781736
    private var mDrawerLayout: DrawerLayout? = null

    private lateinit var gridView: GridView
    private lateinit var adapter: CursoAdapter

    companion object {
        @JvmStatic var myOnClickListener: View.OnClickListener? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        modificar.setOnClickListener { view ->
            //val fragment = MainFragment()
            //Toast.makeText(this, "Hola soy modificar" , Toast.LENGTH_SHORT).show()
            //TODO
            var alertDialog: AlertDialog
            alertDialog = AlertDialog.Builder(this,R.style.Base_Theme_MaterialComponents_Dialog_Alert).create()
            alertDialog.setTitle("Agregar Curso")
            alertDialog.setMessage("Dia")
            alertDialog.setMessage("Hora Inicio")
            alertDialog.setMessage("Curso")
            alertDialog.setMessage("Salon")

            var inputSalon = EditText(this)
            inputSalon.hint = "salon"
            alertDialog.setView(inputSalon)

            alertDialog.setButton(Dialog.BUTTON_POSITIVE,"Contactar") { dialog, which ->
                //TODO
                //miHorario.addCurso()
            }
            alertDialog.setButton(Dialog.BUTTON_NEGATIVE,"Cancelar") { dialog, which ->
                alertDialog.cancel()
            }
            alertDialog.show()
        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        mDrawerLayout = findViewById(R.id.drawer_layout)
        val drawerButton = findViewById<ImageButton>(R.id.drawerOpen)
        drawerButton.setOnClickListener {
            mDrawerLayout?.openDrawer(GravityCompat.START)
        }

        nav_view.setNavigationItemSelectedListener(this)

        gridView = findViewById<GridView>(R.id.grid) as GridView
        cargar()

        /*
        val EXECUTION_TIME: Long = 60000 // 1 minuto
        val handler = Handler()
        handler.postDelayed(object : Runnable {
            override fun run() {
                cargar()
                handler.postDelayed(this, EXECUTION_TIME)
            }
        }, EXECUTION_TIME)
        */
    }

    fun cargar() {
        val horario: Horario = miHorario
        adapter = CursoAdapter(this, horario)
        gridView.adapter = adapter
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {

        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.home, menu)
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
                mDrawerLayout?.closeDrawers()
            }
            R.id.nav_atencionProfesores -> {
                val intent2 = Intent(this, ProfesoresActivity::class.java)
                startActivity(intent2)
                finish()

            }
            R.id.nav_tutorias -> {
                val intent3 = Intent(this, TutoriasActivity::class.java)
                startActivity(intent3)
                finish()

            }
            R.id.nav_clubes -> {
                val intent4 = Intent(this, ClubesActivity::class.java)
                startActivity(intent4)
                finish()
            }
            R.id.nav_biblioteca -> {
                val intent5 = Intent(this, BibliotecaActivity::class.java)
                startActivity(intent5)
                finish()

            }
            R.id.nav_emergencia -> {
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:"+telefonoEmergencia)
                startActivity(intent)

            }
            R.id.nav_configuracion -> {
                val intent6 = Intent(this, ConfiguracionActivity::class.java)
                startActivity(intent6)
                finish()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    public class MyOnClickListener (private val context: Context) : View.OnClickListener {

        override fun onClick(v: View) {
            Toast.makeText(context, "Hola" , Toast.LENGTH_SHORT).show()
        }
    }

}
