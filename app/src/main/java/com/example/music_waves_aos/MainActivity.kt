package com.example.music_waves_aos

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.music_waves_aos.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase


data class User (val UserEmail : String, val UserName: String, val UserId: String ) {

}
class MainActivity : AppCompatActivity() {

    private lateinit var email: EditText
    private lateinit var password: EditText

    private lateinit var auth: FirebaseAuth
    private lateinit var binding: ActivityMainBinding



    fun checkUserIsLoggedInOrNot() {

        val user = dataFromSharedPreferences()
        if (user.UserId != "N/A") {
            moveToHomeScreen()
        }

    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        checkUserIsLoggedInOrNot()


        email = binding.email
        password = binding.password
        auth = Firebase.auth


        binding.register?.setOnClickListener {
            val intent = Intent(this, RegisterScreen::class.java)
            startActivity(intent)
        }

        binding.loginButton!!.setOnClickListener(View.OnClickListener {
            FirabaseLogin()
        })



        setContentView(view)

    }

    // Login With firebase
    private fun FirabaseLogin() {




        auth.signInWithEmailAndPassword(email?.text.toString(), password?.text.toString())
            .addOnCompleteListener(this) { task ->



                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d("SignINSuccess", "signInWithEmail:success")
                    val user = auth.currentUser
                    user?.let { saveDataForApplication(it) }

                    moveToHomeScreen()

                } else {

                    // If sign in fails, display a message to the user.
                    Log.w("SignINFailed", "signInWithEmail:failure", task.exception)

                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()

                    val builder = AlertDialog.Builder(this)

                    builder.setMessage("Authentication failed.")
//                        .setPositiveButton(R.string.start,
//                            DialogInterface.OnClickListener { dialog, id ->
//                                // START THE GAME!
//                            })
                        .setNegativeButton("Ok") { _, _ -> }

                    builder.create()
                    builder.show()

                }
            }

    }


    fun saveDataForApplication(user: FirebaseUser) {
        val sharedPref = this?.getSharedPreferences("UserDetail", Context.MODE_PRIVATE) ?: return

        with (sharedPref.edit()) {

            putString("UserEmail", user.email)
            putString("UserName", user.displayName ?: "N/A")
            putString("UserId", user.uid)
            apply()
        }
    }



    private fun dataFromSharedPreferences(): User {
        val sharedPref =  this?.getSharedPreferences("UserDetail",Context.MODE_PRIVATE)

        return User(
                UserEmail = sharedPref?.getString("UserEmail", "N/A")!!,
                UserId = sharedPref?.getString("UserId", "N/A")!!,
                UserName = sharedPref?.getString("UserId", "N/A")!!
            )

    }

    private fun moveToHomeScreen() {
        val intent = Intent(this, HomeScreen::class.java)
        startActivity(intent)
    }
}
