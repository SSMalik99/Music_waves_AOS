package com.example.music_waves_aos.meditational_music

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.music_waves_aos.R
import kotlin.math.log

class MeditationalMusicAdapter (
    val context: Context, val tracks : List<MotivationMusicData>)
    :RecyclerView.Adapter<MyViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.music_tile,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val track: MotivationMusicData = tracks[position]

//        Glide.with(this.context).load(product.image).into(holder.productImage);
        holder.musicTrack.text = track.track
        holder.musicTitle.text = track.title


    }

    override fun getItemCount(): Int {
        return tracks.size
    }
}

class MyViewHolder(val view: View) :RecyclerView.ViewHolder(view){

    val listContainer = view.findViewById<CardView>(R.id.musicTileContainer)!!

    val musicTitle = view.findViewById<TextView>(R.id.meditationalMusicTitle)
    val musicTrack = view.findViewById<TextView>(R.id.meditationalMusicTrack)

    init {
        // Define click listener for the ViewHolder's View.
        listContainer.setOnClickListener{
//            view.findNavController().navigate(FavouriteFragmentDirections.actionFavouriteFragmentToProductDetailFragment(productId))
            Log.i("recylceViewClick","Clicked from reculcerview")
        }
    }

}