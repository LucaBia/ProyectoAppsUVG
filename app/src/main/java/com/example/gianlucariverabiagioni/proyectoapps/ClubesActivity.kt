package com.example.gianlucariverabiagioni.proyectoapps

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import com.example.gianlucariverabiagioni.proyectoapps.adapters.ClubData
import com.example.gianlucariverabiagioni.proyectoapps.classes.Club
import com.example.gianlucariverabiagioni.proyectoapps.classes.Estudiante
import com.example.gianlucariverabiagioni.proyectoapps.classes.Horario
import kotlinx.android.synthetic.main.activity_clubes.*
import kotlinx.android.synthetic.main.app_bar_clubes.*
import android.widget.TextView



class ClubesActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var data: ArrayList<Club>? = null
    //var nombre = findViewById<TextView>(R.id.tvNombre)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_clubes)
        //setSupportActionBar(toolbar)

        /*fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }*/

        val toggle = ActionBarDrawerToggle(
            this, drawer_layout,  R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)

        val basquetButton = findViewById<Button>(R.id.btnBaloncesto)
        basquetButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","baloncesto")
            startActivity(intent) }
        val futbolButton = findViewById<Button>(R.id.btnFutbol)
        futbolButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","futbol")
            startActivity(intent) }
        val acondicionamientoButton = findViewById<Button>(R.id.btnAcondicionamiento)
        acondicionamientoButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","acondicionamiento")
            startActivity(intent) }
        val ajedrezButton = findViewById<Button>(R.id.btnAjedrez)
        ajedrezButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","ajedrez")
            startActivity(intent) }
        val coroButton = findViewById<Button>(R.id.btnCoro)
        coroButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","coro")
            startActivity(intent) }
        val debateButton = findViewById<Button>(R.id.btnDebates)
        debateButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","desbates")
            startActivity(intent) }
        val danzaButton = findViewById<Button>(R.id.btnDanza)
        danzaButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","danza")
            startActivity(intent) }
        val guitarraButton = findViewById<Button>(R.id.btnGuitarra)
        guitarraButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","guitarra")
            startActivity(intent) }
        val marimbaButton = findViewById<Button>(R.id.btnMarimba)
        marimbaButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","marimba")
            startActivity(intent) }
        val teatroButton = findViewById<Button>(R.id.btnTeatro)
        teatroButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","teatro")
            startActivity(intent) }
        val tenisButton = findViewById<Button>(R.id.btnTT)
        tenisButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","tenis")
            startActivity(intent) }
        val voleibolButton = findViewById<Button>(R.id.btnVoleibol)
        voleibolButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","voleibol")
            startActivity(intent) }
        val yogaButton = findViewById<Button>(R.id.btnYoga)
        yogaButton.setOnClickListener {
            val intent = Intent(this, DetalleClubActivity::class.java)
            intent.putExtra("club","yoga")
            startActivity(intent) }
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
        menuInflater.inflate(R.menu.clubes, menu)
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
                val intent6 = Intent(this, ConfiguracionActivity::class.java)
                startActivity(intent6)
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    @SuppressLint("SetTextI18n")
    fun metodoBaloncesto(view: View){
        //val nombreTextView = findViewById<TextView>(R.id.tvNombre)
        val intentBaloncesto = Intent(this, DetalleClubActivity::class.java)
        //intentBaloncesto.extras()

        /*for (i in 0 until ClubData.nombreArray.size) {
            data!!.add(
                Club(
                    ClubData.nombreArray[i],
                    Horario(),
                    Estudiante(),
                    ClubData.descripcionArray[i]
                )
            )
        }*/
        //nombreTextView.setText("Maldita U");
        startActivity(intentBaloncesto)

    }
}
