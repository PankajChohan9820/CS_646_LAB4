package com.example.diceroller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val startButton = findViewById<Button>(R.id.startButton)
        startButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        val locationButton = findViewById<Button>(R.id.locationButton)
        locationButton.setOnClickListener {
            val intent = Intent(this, LocationActivity::class.java)
            startActivity(intent)
        }
//        val cameraButton = findViewById<Button>(R.id.cameraButton)
//        cameraButton.setOnClickListener {
//            val intent = Intent(this, CameraActivity::class.java)
//            startActivity(intent)
//        }
        val soundButton = findViewById<Button>(R.id.soundButton)
        soundButton.setOnClickListener {
            val intent = Intent(this, SoundActivity::class.java)
            startActivity(intent)
        }

        val animationButton = findViewById<Button>(R.id.animationButton)
        animationButton.setOnClickListener {
            val intent = Intent(this, AnimationActivity::class.java)
            startActivity(intent)
        }
    }
}
