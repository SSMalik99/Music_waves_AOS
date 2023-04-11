package com.example.music_waves_aos.meditational_music

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.music_waves_aos.R
import com.example.music_waves_aos.databinding.FragmentProfileBinding
import com.example.music_waves_aos.databinding.FragmentTrackDetailBinding

class TrackDetailFragment : Fragment() {

    lateinit var binding : FragmentTrackDetailBinding
    private var trackId : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trackId = arguments?.getInt("trackId")!!



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        val view = inflater.inflate(R.layout.fragment_track_detail, container, false)

        binding = FragmentTrackDetailBinding.inflate(layoutInflater)


        val track = MeditationalMusicFragment.getSelectedTrack(trackId)

        binding.trackTitle.text = track.title
        Log.i("trackDetail", track.title)
        binding.trackDescription.text = track.description

        val resourceId = resources.getIdentifier(track.image, "drawable", context?.packageName)
        binding.trackImageView.setBackgroundResource(resourceId)



        binding.viewAllButton.setOnClickListener{
//            view.findNavController().navigate(R.id.meditationalMusicFragment)

            findNavController().navigateUp()
        }


        return binding.root
    }



    companion object {

    }
}