package com.example.music_waves_aos

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.example.music_waves_aos.databinding.FragmentProfileBinding
import com.google.firebase.auth.FirebaseUser


class ProfileFragment : Fragment() {

    lateinit var binding : FragmentProfileBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_profile, container, false)
        binding = FragmentProfileBinding.inflate(layoutInflater)

        val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
            // Callback is invoked after the user selects a media item or closes the
            // photo picker.
            if (uri != null) {
                Log.d("PhotoPicker", "Selected URI: $uri")
                binding.imageView.setImageURI(uri)
            } else {
                Log.d("PhotoPicker", "No media selected")
            }
        }

        binding.imageView.setOnClickListener{
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageAndVideo))
        }

        binding.logoutButton.setOnClickListener{
            resetSharedPrefrences()
            val intent = Intent(this.context, MainActivity::class.java)
            startActivity(intent)

        }

        binding.userEmailValue.text = dataFromSharedPreferences().UserEmail
        return binding.root

    }

    private fun resetSharedPrefrences() {
        val sharedPref = this.activity?.getSharedPreferences("UserDetail", Context.MODE_PRIVATE) ?: return

        with (sharedPref.edit()) {

            putString("UserEmail", null)
            putString("UserName", null)
            putString("UserId", null)
            apply()
        }
    }

    private fun dataFromSharedPreferences(): User {
        val sharedPref = this.activity?.getSharedPreferences("UserDetail", Context.MODE_PRIVATE)

        return User(
            UserEmail = sharedPref?.getString("UserEmail", "N/A")!!,
            UserId = sharedPref?.getString("UserId", "N/A")!!,
            UserName = sharedPref?.getString("UserId", "N/A")!!
        )

    }

}