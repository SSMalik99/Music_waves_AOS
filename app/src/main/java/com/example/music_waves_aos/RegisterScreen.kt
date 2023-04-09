package com.example.music_waves_aos

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.music_waves_aos.databinding.ActivityMainBinding
import com.example.music_waves_aos.databinding.ActivityRegisterScreenBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class RegisterScreen : AppCompatActivity() {
      private lateinit var email: EditText
      private lateinit var password: EditText
      private lateinit var confirm_password: EditText

      lateinit var binding : ActivityRegisterScreenBinding
      private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterScreenBinding.inflate(layoutInflater)
        val view = binding.root
         email = binding.email
         password = binding.password
         confirm_password = binding.confirmPassword

         auth = Firebase.auth

        binding.registerButton.setOnClickListener {

            if (email.text.toString() == "" || password.text.toString() == "" || confirm_password.text.toString() == "") {
                createAlert("Please Insert value for each field.")

            } else {

                if (password.text.toString() == confirm_password.text.toString()) {

                    firebaseRegister()

                } else {
                   createAlert("Password Doesn't Match")
                }
            }
        }

        binding.login.setOnClickListener {
            goToLoginPage()
        }

        setContentView(view)
    }

    private fun goToLoginPage() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun firebaseRegister() {

        auth.createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("UserCreation Succesfull", "createUserWithEmail:success")
                    val user = auth.currentUser

                    val builder = AlertDialog.Builder(this)
                    builder.setMessage("You are register successfully You can login now.").setNegativeButton("Ok") { _, _ ->
                        goToLoginPage()
                    }
                    builder.create()
                    builder.show()

                } else {
                    // If sign in fails, display a message to the user.
                    Log.w("Registration Failed", "createUserWithEmail:failure", task.exception)
                    createAlert("Registration failed, Please Try later")

                }
            }

    }
    fun createAlert(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage(message).setNegativeButton("Ok") { _, _ -> }
        builder.create()
        builder.show()
    }
}
