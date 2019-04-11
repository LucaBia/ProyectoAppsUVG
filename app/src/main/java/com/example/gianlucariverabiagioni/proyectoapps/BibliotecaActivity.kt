package com.example.gianlucariverabiagioni.proyectoapps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_biblioteca.*
import kotlinx.android.synthetic.main.app_bar_biblioteca.*

class BibliotecaActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var telefonoEmergencia: Int =  59781736
    private var mDrawerLayout: DrawerLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_biblioteca)


        val textMensaje = findViewById<TextView>(R.id.textMensaje)

        mail.setOnClickListener { view ->
            var correo = "cas18040@uvg.edu.gt"
            var asunto:String = "Reserva de libro"
            var mensaje:String = "Buen día, me gustaría reservar el libro: "+textMensaje.text.toString()

            sendEmail(correo,asunto,mensaje)
        }


        nav_view.setNavigationItemSelectedListener(this)

        val librosWebView = findViewById<WebView>(R.id.BookRes)
        librosWebView.webViewClient = MyBrowser()
        librosWebView.loadUrl("https://koha.uvg.edu.gt/#")
        mDrawerLayout = findViewById(R.id.drawer_layout)
        val drawerButton = findViewById<ImageButton>(R.id.drawerOpen)
        drawerButton.setOnClickListener {
            mDrawerLayout?.openDrawer(GravityCompat.START)
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
    private inner class MyBrowser : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
            view.loadUrl(url)
            return true
        }
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
        menuInflater.inflate(R.menu.biblioteca, menu)
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
                mDrawerLayout?.closeDrawers()

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
}
