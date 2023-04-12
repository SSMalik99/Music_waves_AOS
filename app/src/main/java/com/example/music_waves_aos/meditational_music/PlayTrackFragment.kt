package com.example.music_waves_aos.meditational_music

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.music_waves_aos.R
import com.example.music_waves_aos.databinding.FragmentPlayTrackBinding


class PlayTrackFragment : Fragment() {


    lateinit var binding : FragmentPlayTrackBinding
    private var trackId = 1
    private lateinit var mediaPlayer : MediaPlayer
    private var isPlaying = false
    lateinit var track : MotivationMusicData

    lateinit var mHandler : Handler
    lateinit var runnable : Runnable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        trackId = arguments?.getInt("trackId")!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_play_track, container, false)
        binding = FragmentPlayTrackBinding.inflate(layoutInflater)

        track = MeditationalMusicFragment.getSelectedTrack(trackId )


        // get media player
        val resourceId = resources.getIdentifier(track.track, "raw", context?.packageName)
        mediaPlayer = MediaPlayer.create(context, resourceId)

        binding.sliderTrack.max = (mediaPlayer.duration / 1000)
        binding.trackTitle.text = track.title


        val imageId = resources.getIdentifier(track.image, "drawable", context?.packageName)
        binding.mainContainerForPlayTrakc.background = context?.getDrawable(imageId)
        binding.trackImage.setImageResource(imageId)
        val minutes = mediaPlayer.duration.toLong() / 1000 / 60
        val seconds = mediaPlayer.duration.toLong() / 1000 % 60
        binding.endTime.text = "$minutes:$seconds"



        binding.loopButton.setOnClickListener{
            if (!mediaPlayer.isLooping) {
                binding.loopButton.setImageResource(R.drawable.baseline_loop_teal)
            }else {
                binding.loopButton.setImageResource(R.drawable.baseline_loop_24)
            }
            mediaPlayer.isLooping = !mediaPlayer.isLooping
        }



        binding.stopButton.setOnClickListener{
//            stopMusic()
            findNavController().navigate(PlayTrackFragmentDirections.actionPlayTrackFragmentToTrackDetailFragment(trackId))
        }

        seekBarChangeApply()
        playPauseMusic()
        setThread()



        return binding.root
    }

    fun seekBarChangeApply() {
        binding.sliderTrack.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (mediaPlayer != null && fromUser) {

                    mediaPlayer.seekTo(progress * 1000)
                    val minutes = mediaPlayer.currentPosition / 1000 / 60
                    val seconds = mediaPlayer.currentPosition / 1000 % 60
                    binding.startTime.text = "$minutes:$seconds"

                }
            }
        })
    }

    fun setThread() {

        mHandler = Handler()
        runnable = object : Runnable {
            override fun run() {
                if (mediaPlayer != null) {
                    val mCurrentPosition: Int = mediaPlayer.currentPosition / 1000
                    binding.sliderTrack.progress = mCurrentPosition
                    val minutes = mediaPlayer.currentPosition / 1000 / 60
                    val seconds = mediaPlayer.currentPosition / 1000 % 60
                    binding.startTime.text = "$minutes:$seconds"

                }
                mHandler.postDelayed(this, 1000)
            }

        }
        activity?.runOnUiThread(runnable)
    }


    fun playPauseMusic() {

        if (!isPlaying) {
            mediaPlayer.start()
            isPlaying = true
        }else {
            mediaPlayer.pause()
            isPlaying = false
        }

    }

    fun stopMusic() {
        if (isPlaying){
            mediaPlayer.stop()
        }
        mediaPlayer.release()
//        mediaPlayer.release()
    }

    fun showTrackTiming() {
        mediaPlayer.duration
    }

    companion object {
    }

    override fun onStop() {
        stopMusic()
        mHandler.removeCallbacks(runnable)
        super.onStop()



    }
}