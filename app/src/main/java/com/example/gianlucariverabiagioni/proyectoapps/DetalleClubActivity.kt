package com.example.gianlucariverabiagioni.proyectoapps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import android.widget.Toast
import com.example.gianlucariverabiagioni.proyectoapps.classes.Club
import com.example.gianlucariverabiagioni.proyectoapps.classes.Estudiante
import kotlinx.android.synthetic.main.activity_detalle_club.*
import kotlinx.android.synthetic.main.app_bar_detalle_club.*
import kotlinx.android.synthetic.main.content_detalle_club.*

class DetalleClubActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var club: Club = Club()
    //var myAwesomeTextView = findViewById<TextView>(R.id.tvNombre)


    var clubBaloncesto: Club = Club("Baloncesto", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de baloncesto ...")
    var clubFutbol: Club = Club("Fútbol", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de futbol ...")
    var clubVoleibol: Club = Club("Voleibol", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de voleibol ...")
    var clubAjedrez: Club = Club("Ajedrez", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de ajedrez ...")
    var clubTenisMesa: Club = Club("Tenis de Mesa", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de tenis de mesa ...")
    var clubAcondicionamientoFisi: Club = Club("Acondicionamiento Físico", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de acondicionamiento fisico ...")
    var clubYoga: Club = Club("Yoga", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de yoga ...")
    var clubCoro: Club = Club("Coro", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de coro ...")
    var clubMarimba: Club = Club("Marimba", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de marimba ...")
    var clubTeatro: Club = Club("Teatro", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de teatro ...")
    var clubGuitarra: Club = Club("Guitarra", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de guitarra ...")
    var clubDanza: Club = Club("Danza", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de danza ...")
    var clubDebates: Club = Club("Debates", Estudiante("Julio", "18040", "jul18040@uvg.edu.gt", "123"), "El club de debates ...")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_club)
        //setSupportActionBar(toolbar)
        val textMail = findViewById<TextView>(R.id.mailText)

        fab.setOnClickListener { view ->
            var correo =textMail.text.toString()
            var asunto:String = "Inscripcion del club"
            var mensaje:String = "Buen día, me gustaría inscribirme al club"

            sendEmail(correo,asunto,mensaje)

        }

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
        var tvNombre = findViewById<TextView>(R.id.tvNombre)
        var tvDescripcion = findViewById<TextView>(R.id.tvDescripcion)
        var clubText = intent.extras.getString("club")
        if (clubText == "baloncesto"){
            tvNombre.text = clubBaloncesto.nombre
            tvDescripcion.text = clubBaloncesto.descripcion
        }
        if (clubText == "futbol"){
            tvNombre.text = clubFutbol.nombre
            tvDescripcion.text = clubFutbol.descripcion
        }
        if (clubText == "acondicionamiento"){
            tvNombre.text = clubAcondicionamientoFisi.nombre
            tvDescripcion.text = clubAcondicionamientoFisi.descripcion
        }
        if (clubText == "ajedrez"){
            tvNombre.text = clubAjedrez.nombre
            tvDescripcion.text = clubAjedrez.descripcion
        }
        if (clubText == "coro"){
            tvNombre.text = clubCoro.nombre
            tvDescripcion.text = clubCoro.descripcion
        }
        if (clubText == "danza"){
            tvNombre.text = clubDanza.nombre
            tvDescripcion.text = clubDanza.descripcion
        }
        if (clubText == "debates"){
            tvNombre.text = clubDebates.nombre
            tvDescripcion.text = clubDebates.descripcion
        }
        if (clubText == "guitarra"){
            tvNombre.text = clubGuitarra.nombre
            tvDescripcion.text = clubGuitarra.descripcion
        }
        if (clubText == "marimba"){
            tvNombre.text = clubMarimba.nombre
            tvDescripcion.text = clubMarimba.descripcion
        }
        if (clubText == "teatro"){
            tvNombre.text = clubTeatro.nombre
            tvDescripcion.text = clubTeatro.descripcion
        }
        if (clubText == "tenis"){
            tvNombre.text = clubTenisMesa.nombre
            tvDescripcion.text = clubTenisMesa.descripcion
        }
        if (clubText == "voleibol"){
            tvNombre.text = clubVoleibol.nombre
            tvDescripcion.text = clubVoleibol.descripcion
        }
        if (clubText == "yoga"){
            tvNombre.text = clubYoga.nombre
            tvDescripcion.text = clubYoga.descripcion
        }

        
    }

    private fun sendEmail(correo: String, asunto: String, message: String){
        val mIntent = Intent(Intent.ACTION_SEND)
        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "text/plain"
        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(correo))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, asunto)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)

        try {
            startActivity(Intent.createChooser(mIntent, "Choose email client..."))

        }
        catch (e: Exception){
            //Si no funciona
            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }
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
        menuInflater.inflate(R.menu.detalle_club, menu)
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
                val intent2 = Intent(this, ProfesoresActivity::class.java)
                startActivity(intent2)
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

            }
            R.id.nav_configuracion -> {

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
