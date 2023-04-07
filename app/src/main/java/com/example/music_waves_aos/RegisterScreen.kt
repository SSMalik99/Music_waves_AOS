package com.example.music_waves_aos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class RegisterScreen : AppCompatActivity() {
      var email: EditText? = null
      var password: EditText? = null
      var confirm_password: EditText? = null
      var login: Button? = null
      var register_button: Button? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_screen)
        val databaseClass = DatabaseClass(this)
         email = findViewById<View>(R.id.email) as EditText
         password = findViewById<View>(R.id.password) as EditText
         confirm_password = findViewById<View>(R.id.confirm_password) as EditText
         register_button = findViewById<View>(R.id.register_button) as Button
         login = findViewById<View>(R.id.login) as Button
         login!!.setOnClickListener {
            val intent = Intent(this@RegisterScreen, MainActivity::class.java)
            startActivity(intent)
        }
        register_button!!.setOnClickListener {
            val email = email!!.text.toString()
            val password = password!!.text.toString()
            val confirm_password = confirm_password!!.text.toString()
            if (email == "" || password == "" || confirm_password == "") {
                Toast.makeText(applicationContext, "Fields Required", Toast.LENGTH_SHORT).show()
            } else {
                if (password == confirm_password) {
                    val checkemail: Boolean = databaseClass.CheckEmail(email)
                    if (checkemail == true) {
                        val insert: Boolean = databaseClass.Insert(email, password)
                        if (insert == true) {
                            Toast.makeText(applicationContext, "Registered", Toast.LENGTH_SHORT)
                                .show()
                            email?.setText("")
                            password!!.setText("")
                            confirm_password!!.setText("")
                        }
                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Email already taken",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Password does not match",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        login!!.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)

        }
    }

    private fun String.setText(s: String) {

    }
}
