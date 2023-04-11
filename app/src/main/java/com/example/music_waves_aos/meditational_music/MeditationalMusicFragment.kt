package com.example.music_waves_aos.meditational_music

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.music_waves_aos.R


class MeditationalMusicFragment : Fragment() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        val view = inflater.inflate(R.layout.fragment_meditational_music, container, false)
        showMusicTrack(view)

        return view
    }

    fun showMusicTrack(view : View) {

        val recyclerView  = view.findViewById<RecyclerView>(R.id.musicRecylerView)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val tracks = getAvailableTrakc()

        val productAdapter = MeditationalMusicAdapter(view.context, tracks)
        recyclerView.adapter = productAdapter


    }

    companion object {
        fun getAvailableTrakc() : List<MotivationMusicData> {

            return listOf<MotivationMusicData>(
                MotivationMusicData(id = 1, title = "1 minute relaxing Meditation", description = "World music is a musical genre that encompasses all the traditional genres of various ethnic groups and cultures of the world in a single category. This type of meditation music allows you to enjoy the sound of little-known instruments. Thanks to world music, we can discover the sounds emitted by Tibetan singing bowls, wind chimes, kalimba, ukulele, bamboo flute, among others. This music is highly beneficial when meditating and trying to calm the mind and body. These sounds have a positive impact on the individual, improving his or her mood considerably. The meditation practitioner discovers new sounds, which generates new neural connections. It benefits meditation, as this practice requires powerful mental energy.", duration = 70, track = "meditation1", image = "med_1"),
                MotivationMusicData(
                    id = 2,
                    title = "Coniferous Forest Meditation",
                    description= "Meditation is an exercise that relaxes and concentrates the mind allowing the practitioner to gain a clearer perspective of what he or she is experiencing. Meditation is becoming an increasingly popular practice due to the great benefits it provides. Meditation music enhances and potentiates this mental exercise, making the practice more enjoyable. In this article, we’ll take a look at the ten different types of meditation music and the benefits you can get from each style.",
                    duration= 70,
                    track= "meditation2",
                    image= "med_2"),

                MotivationMusicData(
                    id = 3,
                    title = "Ambient Classical Guitar",
                    description = "Classical music has the virtue of transmitting emotions to the listener. This meditation music directly benefits the mind by relaxing it, combats stress, enhances intelligence, develops creativity, and favors learning. Classical music transmits high energetic vibrations, which lifts the mood of the listener. Composers of classical music have the goal of creating uplifting melodies, and they do so quite effortlessly. Listening to classical music while meditating allows the individual to reach the maximum effectiveness of his or her practice. This point is extremely beneficial, as the practitioner has the ultimate goal of achieving peace of mind while meditating. Classical music facilitates the meditation process, making the practice pleasurable.",
                    duration = 70, track = "meditation3", image= "med_3"),

                MotivationMusicData(
                    id = 4,
                    title = "Love Mellow Piano",
                    description = "Ambient music is a genre of music that prioritizes tone and atmosphere over traditional musical structure. This type of music is simply perfect for meditation, as it enhances mental concentration to the maximum. The practitioner frees himself from all the distractions that bother him. He can focus his mind on his inner world to find the existential answers he is looking for. The ambient music is aesthetically beautiful, which is an excellent stimulus for meditation practice. This type of meditation music is especially effective for those who find it difficult to concentrate when approaching the practice of meditation. Ambient music helps to master this mental exercise in a short time.",
                    duration = 70, track = "meditation4", image= "med_4"),

                MotivationMusicData(
                    id = 5,
                    title = "Evening Improvisation with ethera",
                    description = "Binaural sounds generate when you hear two tones in each ear at two slightly different frequencies. The brain creates a third sound, a product of the difference in frequencies, the binaural sound. This type of meditation music makes the meditation practitioner quickly reach deep relaxation. Binaural sounds work so that the mind is at peace, and meditation is effective. Binaural sounds remove anxiety and tribulations from the mind. The practitioner can explore his inner world and connects with his or her higher self. In this way, meditation evolves day by day, reaching more complex goals.",
                    duration = 70, track = "meditation5", image= "med_5"),

                MotivationMusicData(
                    id = 6,
                    title = "Heart of the Ocean",
                    description = "Isochronic tones are sounds that are repeated at a specific time interval. An isochronous tone is formed by a sound and a silence of equal duration. This meditation music is a powerful tool to relieve stress and thus facilitate the meditation practice. Listening to isochronic tones while meditating helps to achieve a deep inner peace. This happens because the mind is emptied of negative thoughts that prevent the individual from connecting with his/her inner perfection. The benefits of isochronic tones positively influence the individual’s physical health, alleviating all kinds of pain.",
                    duration = 70, track = "meditation6", image= "med_6"),

                MotivationMusicData(
                    id = 7,
                    title = "Motivational Beat for up stream...",
                    description = "Music has been a source of entertainment, motivation, and mood buster since historical times. Over the years, artists have written songs about life, experiences, love, pain, etc. Depending on the phase of your life, you might have a special inclination towards a specific melody, such as motivational music, romantic lyrics, or inspirational songs.\n When you feel discouraged or helpless and don’t feel like discussing it with anyone, finding comfort in motivational songs becomes necessary. While sometimes you just need the inspiration to reach your goals, these songs also provide an emotional lift to a goalless life.",
                    duration= 70,
                    track= "meditation7",
                    image= "med_7")

            )

        }

        fun getSelectedTrack (id : Int) : MotivationMusicData {
            return getAvailableTrakc()[id - 1]
        }

    }



}