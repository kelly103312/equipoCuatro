package com.example.miniproyecto1.view

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.miniproyecto1.R
import kotlin.random.Random

class HomeActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var botella: ImageView
    private lateinit var botonGirar: ImageButton
    private lateinit var countdownText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mediaPlayer = MediaPlayer.create(this, R.raw.fondo_musica)

        botella = findViewById(R.id.bottleImage)
        botonGirar = findViewById(R.id.spinButton)
        countdownText = findViewById(R.id.countdownText)

        botonGirar.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
            iniciarCuentaRegresiva()
        }
    }

    private fun iniciarCuentaRegresiva() {
        object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val segundosRestantes = (millisUntilFinished / 1000).toInt() + 1
                countdownText.text = segundosRestantes.toString()
            }

            override fun onFinish() {
                countdownText.text = "0"
                girarBotella()
            }
        }.start()
    }

    private fun girarBotella() {
        val anguloAleatorio = Random.nextInt(1800, 3600).toFloat()

        val rotateAnimation = RotateAnimation(
            0f, anguloAleatorio,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 3000
            fillAfter = true
        }

        botella.startAnimation(rotateAnimation)
        resetCountdown()

        mediaPlayer.setOnCompletionListener {
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        }
    }

    private fun resetCountdown() {
        countdownText.text = "3"
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}



