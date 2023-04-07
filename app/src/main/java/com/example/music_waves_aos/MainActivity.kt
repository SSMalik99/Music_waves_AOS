package com.example.music_waves_aos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    var email: EditText? = null
    var password:EditText? = null
    var register: Button? = null
    var login_button: Button? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val databaseClass = DatabaseClass(this)
        email = findViewById<View>(R.id.email) as EditText
        password = findViewById<View>(R.id.password) as EditText
        register = findViewById<View>(R.id.register) as Button
        login_button = findViewById<View>(R.id.login_button) as Button
        login_button!!.setOnClickListener {
            val intent = Intent(this@MainActivity, RegisterScreen::class.java)
            startActivity(intent)
        }
        login_button!!.setOnClickListener(View.OnClickListener {
            val email = email!!.text.toString()
            val password: String = password!!.getText().toString()
            val checklogin = databaseClass!!.CheckLogin(email, password)
            if (checklogin) {
                //Toast.makeText(getApplicationContext(), "Login Successful", Toast.LENGTH_SHORT).show();
                val i = Intent(applicationContext, Welcome::class.java)
                startActivity(i)
            } else {
                Toast.makeText(
                    applicationContext,
                    "Invalid email or password",
                    Toast.LENGTH_SHORT
                ).show()
            }
        })

    }

}
