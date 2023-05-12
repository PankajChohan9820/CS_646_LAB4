// AnimationActivity.kt
package com.example.diceroller

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class AnimationActivity : AppCompatActivity() {

    private lateinit var animationDrawable: AnimationDrawable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)

        val animatedImageView = findViewById<ImageView>(R.id.animatedImageView)
        animationDrawable = animatedImageView.background as AnimationDrawable

        // Start the animation
        animationDrawable.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop the animation to release resources
        animationDrawable.stop()
    }
}
