package com.example.gianlucariverabiagioni.proyectoapps

import android.app.Dialog
import android.content.ContentValues
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
import android.util.Log
import android.widget.EditText
import android.widget.ImageButton
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import android.content.ContentValues.TAG
import android.support.annotation.NonNull
import com.example.gianlucariverabiagioni.proyectoapps.classes.Curso
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.DocumentSnapshot


class HomeActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var horario : Horario
    lateinit var horarioSaber : Horario
    var telefonoEmergencia: Int =  59781736
    private var mDrawerLayout: DrawerLayout? = null
    private lateinit var dbRefer: DatabaseReference
    private lateinit var gridView: GridView
    private lateinit var adapter: CursoAdapter
    private lateinit var database: FirebaseDatabase
    private lateinit var db: FirebaseFirestore
    private lateinit var user: FirebaseUser


    companion object {
        @JvmStatic var myOnClickListener: View.OnClickListener? = null
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        database = FirebaseDatabase.getInstance()
        dbRefer = database.reference.child("Estudiantes")
        db = FirebaseFirestore.getInstance()
        user = FirebaseAuth.getInstance().currentUser!!

        db.collection("Estudiantes")
            .whereEqualTo("correo", user?.email)
            .get().addOnSuccessListener {
                it.forEach {
                    //horario = it.get("horario")
                    horarioSaber = toHorario(it.getData().get("horario") as Map<String, Any>)
                    horario = it.get("horario", Horario::class.java)!!

                    modificar.setOnClickListener { view ->
                        val alertDialog: AlertDialog
                        alertDialog = AlertDialog.Builder(this,R.style.Base_Theme_MaterialComponents_Dialog_Alert).create()
                        alertDialog.setTitle("Agregar Curso")
                        alertDialog.setMessage("Dia")
                        val inputDia = EditText(this)
                        inputDia.hint = "dia"
                        alertDialog.setView(inputDia)

                        alertDialog.setButton(Dialog.BUTTON_POSITIVE,"Aceptar") { dialog, which ->
                            val dia =inputDia.text.toString()
                            val alertDialog1: AlertDialog
                            alertDialog1 = AlertDialog.Builder(this,R.style.Base_Theme_MaterialComponents_Dialog_Alert).create()
                            alertDialog1.setTitle("Agregar Curso")
                            alertDialog1.setMessage("Num Periodo")
                            val inputPer= EditText(this)
                            inputPer.hint= "num"
                            alertDialog1.setView(inputPer)

                            alertDialog1.setButton(Dialog.BUTTON_POSITIVE,"Aceptar"){ dialog, which ->
                                val periodo = inputPer.text.toString().toInt()

                                val alertDialog2: AlertDialog
                                alertDialog2 = AlertDialog.Builder(this,R.style.Base_Theme_MaterialComponents_Dialog_Alert).create()
                                alertDialog2.setTitle("Agregar Curso")
                                alertDialog2.setMessage("Nombre Curso")
                                val inputNom= EditText(this)
                                inputNom.hint= "Nombre"
                                alertDialog2.setView(inputNom)

                                alertDialog2.setButton(Dialog.BUTTON_POSITIVE,"Aceptar"){ dialog, which ->
                                    val nombre = inputNom.text.toString()
                                    val alertDialog3: AlertDialog
                                    alertDialog3 = AlertDialog.Builder(this,R.style.Base_Theme_MaterialComponents_Dialog_Alert).create()
                                    alertDialog3.setTitle("Agregar Curso")
                                    alertDialog3.setMessage("Salon")
                                    val inputSalon= EditText(this)
                                    inputSalon.hint= "Salon"
                                    alertDialog3.setView(inputSalon)

                                    alertDialog3.setButton(Dialog.BUTTON_POSITIVE,"Aceptar"){ dialog, which ->
                                        val salon = inputSalon.text.toString()
                                        horario.addCurso(dia, periodo, nombre, salon)
                                        db.collection("Estudiantes").document(it.id).update("horario", horario.toMap())
                                        cargar(horario)
                                    }
                                    alertDialog3.show()
                                }
                                alertDialog2.show()
                            }
                            alertDialog1.show()
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
                    cargar(horario)
                }
            }
    }

    fun cargar(horario: Horario) {
        adapter = CursoAdapter(this, horario)
        gridView.adapter = adapter
    }

    fun toHorario(horarioMapa: Map<String, Any>): Horario {
        val horario = Horario()

        //horario.horasP = horarioMapa["Lunes"]
        /*horario.lunes = horarioMapa["Lunes"] as MutableMap<Int, Curso?>
        horario.martes = horarioMapa["Martes"] as MutableMap<Int, Curso?>
        horario.miercoles = horarioMapa["Miercoles"] as MutableMap<Int, Curso?>
        horario.jueves = horarioMapa["Jueves"] as MutableMap<Int, Curso?>
        horario.viernes = horarioMapa["Viernes"] as MutableMap<Int, Curso?>*/

        return horario
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
