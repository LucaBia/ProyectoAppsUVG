package com.example.gianlucariverabiagioni.proyectoapps

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import com.example.gianlucariverabiagioni.proyectoapps.classes.Estudiante
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import android.widget.ImageButton
import kotlinx.android.synthetic.main.activity_configuracion.*
import com.google.firebase.database.GenericTypeIndicator
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.android.gms.tasks.Task
import android.support.annotation.NonNull
import android.support.v4.app.FragmentActivity
import android.widget.Button
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.CollectionReference
import kotlinx.android.synthetic.main.content_configuracion.*
import org.w3c.dom.Text


@Suppress("UNREACHABLE_CODE")
class ConfiguracionActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var telefonoEmergencia: Int =  59781736
    private var mDrawerLayout: DrawerLayout? = null

    private lateinit var txtNombre: TextView
    private lateinit var txtCarnet: TextView
    private lateinit var txtCorreo: TextView
    private lateinit var auth: FirebaseAuth

    private lateinit var ref: DatabaseReference

    var fdb = FirebaseDatabase.getInstance()
    var myRef = fdb.getReference("Estudiantes")




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_configuracion)
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

        ref = FirebaseDatabase.getInstance().getReference("Estudiantes")

        auth= FirebaseAuth.getInstance()
        txtNombre=findViewById(R.id.txtNombre)
        txtCarnet=findViewById(R.id.txtCarnet)
        txtCorreo=findViewById(R.id.txtCorreo)


       //var hola = LoginActivity().correo

        val myDB = FirebaseFirestore.getInstance()
        //var recuperarCorreo: String = intent.getStringExtra("correoIngreso")
        val user : FirebaseUser? = FirebaseAuth.getInstance().currentUser

        myDB.collection("Estudiantes")
                //TODO en el value "Andy" cambiar a el correo que el usuario ingresa
            .whereEqualTo("correo", user?.email)
            .get().addOnSuccessListener {
                it.forEach {
                    txtNombre.text = it.get("nombre").toString()
                    txtCarnet.text = it.get("carne").toString()
                    txtCorreo.text = it.get("correo").toString()
                    /*println(
                        "Gravity of ${it.get("nombre")} is ${it.get("carne")} m/s/s"
                    )*/
                }
            }

        val newCont = findViewById<TextView>(R.id.newCont)
        val cambiar = findViewById<Button>(R.id.buttonCambiar)
        cambiar.setOnClickListener {
            var cont = newCont.text
            user!!.updatePassword(cont.toString())
            Toast.makeText(this, "Se cambio la contraseña a ${cont}" , Toast.LENGTH_SHORT).show()
        }

        /*myRef.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(p0: DataSnapshot) {
                val t = object : GenericTypeIndicator<Estudiante>() {
                }
                //var estudiante = Estudiante()
                var estudiante : Estudiante? = p0.getValue(t)

                //estudiante = p0.getValue(t)

                txtNombre.text = estudiante?.nombre
            }

            override fun onCancelled(p0: DatabaseError) {
                Log.e("ERROR FIREBASE",p0.getMessage())
            }

        })*/



        //loadUserInfo()
    }

    fun loadUserInfo(){
        val user: FirebaseUser? =auth.currentUser
        val us = FirebaseFirestore.getInstance()
        us.collection("Estudiantes").get().addOnSuccessListener { OnSuccessListener<QuerySnapshot>() {
            Log.e("SHOW",it.toObjects(Estudiante::class.java).size.toString())

        }}
        getuserinfo(us)
        if (user != null) {
            if (user.displayName!=null) {

                var nombre = user.displayName
                txtNombre.text = nombre
            }
        }
    }

    fun getuserinfo(mFirebaseFirestore: FirebaseFirestore) {
        val TAG = "SS"
        val correo = intent.getStringExtra("CORREO")
        mFirebaseFirestore.collection("Estudiantes").whereEqualTo("correo", correo).get()
            .addOnSuccessListener(OnSuccessListener { documentSnapshots ->
                if (documentSnapshots.isEmpty) {
                    Log.e(TAG, "onSuccess: LIST EMPTY")
                    return@OnSuccessListener
                } else {
                    val types = documentSnapshots.toObjects(Estudiante::class.java)

                    txtNombre.text = types[0].nombre!!
                    txtCarnet.text = types[0].carne!!
                    txtCorreo.text = types[0].correo!!
                    Log.e(TAG, "onSuccess: " + types[0].nombre!!)
                }
            })
        auth = FirebaseAuth.getInstance()

    }





    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            val intentAtras = Intent(this, HomeActivity::class.java)
            startActivity(intentAtras)
            //super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.configuracion, menu)
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
                val intent = Intent(Intent.ACTION_DIAL)
                intent.data = Uri.parse("tel:"+telefonoEmergencia)
                startActivity(intent)
            }
            R.id.nav_configuracion -> {
                mDrawerLayout?.closeDrawers()
            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
