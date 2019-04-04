package com.example.gianlucariverabiagioni.proyectoapps

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.design.widget.TextInputEditText
import android.support.design.widget.TextInputLayout
import android.support.v4.widget.NestedScrollView
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.AppCompatButton
import android.support.v7.widget.AppCompatTextView
import android.view.View
import android.widget.Toast
import com.example.gianlucariverabiagioni.proyectoapps.classes.Estudiante
import com.example.gianlucariverabiagioni.proyectoapps.classes.InputValidation
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.FirebaseFirestore


class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    private val activity = this@RegisterActivity

    private lateinit var nestedScrollView: NestedScrollView

    private lateinit var textInputLayoutName: TextInputLayout
    private lateinit var textInputLayoutEmail: TextInputLayout
    private lateinit var textInputLayoutPassword: TextInputLayout
    private lateinit var textInputLayoutConfirmPassword: TextInputLayout
    private lateinit var textInputLayoutCarne: TextInputLayout

    private lateinit var textInputEditTextName: TextInputEditText
    private lateinit var textInputEditTextEmail: TextInputEditText
    private lateinit var textInputEditTextPassword: TextInputEditText
    private lateinit var textInputEditTextConfirmPassword: TextInputEditText
    private lateinit var textInputEditTextCarne: TextInputEditText

    private lateinit var appCompatButtonRegister: AppCompatButton
    private lateinit var appCompatTextViewLoginLink: AppCompatTextView

    private lateinit var inputValidation: InputValidation
    //private lateinit var databaseHelper: DbHelperEstudiantes


    //private lateinit var dbRefer: DatabaseReference
    private lateinit var db: FirebaseFirestore
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_register)

        // hiding the action bar
        supportActionBar!!.hide()

        // initializing the views
        initViews()

        // initializing the listeners
        initListeners()

        // initializing the objects
        initObjects()
        onClick(appCompatButtonRegister)

        db = FirebaseFirestore.getInstance()
        auth = FirebaseAuth.getInstance()
    }

    /**
     * This method is to initialize views
     */
    private fun initViews() {
        nestedScrollView = findViewById<View>(R.id.nestedScrollView) as NestedScrollView

        textInputLayoutName = findViewById<View>(R.id.textInputLayoutName) as TextInputLayout
        textInputLayoutEmail = findViewById<View>(R.id.textInputLayoutEmail) as TextInputLayout
        textInputLayoutPassword = findViewById<View>(R.id.textInputLayoutPassword) as TextInputLayout
        textInputLayoutConfirmPassword = findViewById<View>(R.id.textInputLayoutConfirmPassword) as TextInputLayout
        textInputLayoutCarne = findViewById(R.id.textInputLayoutCarne) as TextInputLayout

        textInputEditTextName = findViewById<View>(R.id.textInputEditTextName) as TextInputEditText
        textInputEditTextEmail = findViewById<View>(R.id.textInputEditTextEmail) as TextInputEditText
        textInputEditTextPassword = findViewById<View>(R.id.textInputEditTextPassword) as TextInputEditText
        textInputEditTextConfirmPassword = findViewById<View>(R.id.textInputEditTextConfirmPassword) as TextInputEditText
        textInputEditTextCarne = findViewById<View>(R.id.textInputEditTextCarne) as TextInputEditText

        appCompatButtonRegister = findViewById<View>(R.id.appCompatButtonRegister) as AppCompatButton

        appCompatTextViewLoginLink = findViewById<View>(R.id.appCompatTextViewLoginLink) as AppCompatTextView

    }

    /**
     * This method is to initialize listeners
     */
    private fun initListeners() {
        appCompatButtonRegister!!.setOnClickListener(this)
        appCompatTextViewLoginLink!!.setOnClickListener(this)

    }

    /**
     * This method is to initialize objects to be used
     */
    private fun initObjects() {
        inputValidation = InputValidation(activity)
        //databaseHelper = DbHelperEstudiantes(activity)


    }


    /**
     * This implemented method is to listen the click on view
     *
     * @param v
     */
    override fun onClick(v: View) {
        when (v.id) {

            //R.id.appCompatButtonRegister -> postDataToSQLite()
            R.id.appCompatButtonRegister -> createAccount()

            R.id.appCompatTextViewLoginLink -> finish()
        }
    }


    private fun createAccount() {

        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextName, textInputLayoutName, getString(R.string.error_message_name))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextEmail(textInputEditTextEmail, textInputLayoutEmail, getString(R.string.error_message_email))) {
            return
        }
        if (!inputValidation!!.isInputEditTextFilled(textInputEditTextPassword, textInputLayoutPassword, getString(R.string.error_message_password))) {
            return
        }
        if (!inputValidation!!.isInputEditTextMatches(textInputEditTextPassword, textInputEditTextConfirmPassword,
                textInputLayoutConfirmPassword, getString(R.string.error_password_match))) {
            return
        }

        /*
        if (!db.checkUser(textInputEditTextEmail.text.toString().trim(),textInputEditTextPassword.text.toString().trim())) {
            var user = Estudiante(
                name = textInputEditTextName.text.toString().trim(),
                carne = textInputEditTextCarne.text.toString().trim(),
                email = textInputEditTextEmail.text.toString().trim(),
                password = textInputEditTextPassword.text.toString().trim())
            databaseHelper.addEstudiante(user)
            // Snack Bar to show success message that record saved successfully
            Snackbar.make(nestedScrollView!!, getString(R.string.success_message), Snackbar.LENGTH_LONG).show()
            emptyInputEditText()
        } else {
            // Snack Bar to show error message that record already exists
            Snackbar.make(nestedScrollView!!, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show()
        }
        */

        val name = textInputEditTextName.text.toString().trim()
        val carne = textInputEditTextCarne.text.toString().trim()
        val email = textInputEditTextEmail.text.toString().trim()
        val password = textInputEditTextPassword.text.toString().trim()

        auth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) {
                task ->

                if (task.isComplete) {
                    val user: FirebaseUser? = auth.currentUser
                    verifyEmail(user)

                    val usuario = Estudiante(name, carne, email, password).toMap()
                    db.collection("Estudiantes")
                        .add(usuario)
                        .addOnSuccessListener { documentReference ->
                            emptyInputEditText()
                            // Snack Bar to show success message that record saved successfully
                            Snackbar.make(nestedScrollView!!, getString(R.string.success_message), Snackbar.LENGTH_LONG).show()

                        }
                        .addOnFailureListener { e ->
                            // Snack Bar to show error message that record already exists
                            Snackbar.make(nestedScrollView!!, getString(R.string.error_email_exists), Snackbar.LENGTH_LONG).show()
                        }

                    startActivity(Intent(this, LoginActivity::class.java))
                }
            }
    }

    /**
     * This method is to empty all input edit text
     */
    private fun emptyInputEditText() {
        textInputEditTextName!!.text = null
        textInputEditTextEmail!!.text = null
        textInputEditTextPassword!!.text = null
        textInputEditTextConfirmPassword!!.text = null
    }

    private fun verifyEmail(user:FirebaseUser?) {
        user?.sendEmailVerification()
            ?.addOnCompleteListener(this) {
                    task ->

                if (task.isComplete) {
                    Toast.makeText(this, "Enviado correctamente", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Ha ocurrido un error", Toast.LENGTH_LONG).show()
                }
            }
    }
}