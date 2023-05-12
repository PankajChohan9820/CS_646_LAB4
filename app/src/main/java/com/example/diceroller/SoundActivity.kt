// SoundActivity.kt
package com.example.diceroller

import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class SoundActivity : AppCompatActivity() {

    private lateinit var mediaPlayer: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sound)

        val playButton = findViewById<Button>(R.id.playButton)
        playButton.setOnClickListener {
            playSound()
        }
    }

    private fun playSound() {
        mediaPlayer = MediaPlayer.create(this, R.raw.sound_file) // Replace "sound_file" with your audio file resource name
        mediaPlayer.start()
    }

    override fun onStop() {
        super.onStop()
        mediaPlayer.release()
    }
}
