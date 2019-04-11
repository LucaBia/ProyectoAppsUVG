package com.example.gianlucariverabiagioni.proyectoapps

import android.app.Dialog
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_tutorias.*
import android.support.v7.widget.RecyclerView
import android.view.View
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import com.example.gianlucariverabiagioni.proyectoapps.adapters.EstudianteAdapter
import com.example.gianlucariverabiagioni.proyectoapps.adapters.TutoresData
import com.example.gianlucariverabiagioni.proyectoapps.classes.Estudiante
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AlertDialog
import android.widget.ImageButton
import android.widget.Toast

class TutoriasActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private var adapter: RecyclerView.Adapter<*>? = null
    private var layoutManager: RecyclerView.LayoutManager? = null
    private var recyclerView: RecyclerView? = null
    private var data: ArrayList<Estudiante>? = null
    private var mDrawerLayout: DrawerLayout? = null

    var telefonoEmergencia: Int =  59781736

    companion object {
        @JvmStatic var myOnClickListener: View.OnClickListener? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tutorias)
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
        mDrawerLayout = findViewById(R.id.drawer_layout)
        val drawerButton = findViewById<ImageButton>(R.id.drawerOpen)
        drawerButton.setOnClickListener {
            mDrawerLayout?.openDrawer(GravityCompat.START)
        }

        nav_view.setNavigationItemSelectedListener(this)
        class MyOnClickListener (private val context: Context) : View.OnClickListener {

            override fun onClick(v: View) {
                var correo = TutoresData.correoArray[recyclerView!!.getChildPosition(v)]
                var alertDialog: AlertDialog
                alertDialog = AlertDialog.Builder(this.context,R.style.Base_Theme_MaterialComponents_Dialog_Alert).create()
                alertDialog.setTitle("Contactar")
                alertDialog.setMessage("Desea contactar al tutor?")
                alertDialog.setButton(Dialog.BUTTON_POSITIVE,"Contactar") {  dialog, which ->
                    var mail = correo
                    var asunto:String = "Tutoria"
                    var mensaje:String = "Buen día, me gustaría recibir tutorias: "

                    sendEmail(mail,asunto,mensaje)     }
                alertDialog.setButton(Dialog.BUTTON_NEGATIVE,"Cancelar") { dialog, which ->
                    alertDialog.cancel()
                }
                alertDialog.show()
                //var correo = TutoresData.correoArray[recyclerView!!.getChildPosition(v)]
                //Toast.makeText(context, correo , Toast.LENGTH_SHORT).show()
            }
        }

        myOnClickListener = MyOnClickListener(this)

        recyclerView = (findViewById(R.id.recyclerView) as RecyclerView)
        recyclerView!!.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        recyclerView!!.setLayoutManager(layoutManager)
        recyclerView!!.setItemAnimator(DefaultItemAnimator())

        data = ArrayList<Estudiante>()
        for (i in 0 until TutoresData.nameArray.size) {
            data!!.add(
                Estudiante(
                    TutoresData.nameArray[i],
                    TutoresData.carneArray[i],
                    TutoresData.correoArray[i],
                    TutoresData.contrasenaArray[i]
                )
            )
        }

        adapter = EstudianteAdapter(data!!)
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
        menuInflater.inflate(R.menu.tutorias, menu)
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
                mDrawerLayout?.closeDrawers()

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

    /*private class MyOnClickListener (private val context: Context) : View.OnClickListener {

        override fun onClick(v: View) {

            TutoresData.correoArray[]
            Toast.makeText(context, "Hola" , Toast.LENGTH_SHORT).show()
        }
    }*/
}
