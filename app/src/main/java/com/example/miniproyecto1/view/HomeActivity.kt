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
    private var countdownTimer: CountDownTimer? = null // Variable para el temporizador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        mediaPlayer = MediaPlayer.create(this, R.raw.fondo_musica)

        botella = findViewById(R.id.bottleImage)
        botonGirar = findViewById(R.id.spinButton)
        countdownText = findViewById(R.id.countdownText)

        // Iniciar la cuenta regresiva al presionar el botón
        botonGirar.setOnClickListener {
            if (!mediaPlayer.isPlaying) {
                mediaPlayer.start()
            }
            reiniciarCuentaRegresiva()
        }
    }

    private fun reiniciarCuentaRegresiva() {
        // Reiniciar el texto del temporizador a 3
        countdownText.text = "3"

        // Cancelar el temporizador anterior si está en ejecución
        countdownTimer?.cancel()

        // Crear un nuevo temporizador que comienza desde 3
        countdownTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val segundosRestantes = (millisUntilFinished / 1000).toInt()
                countdownText.text = segundosRestantes.toString()
            }

            override fun onFinish() {
                countdownText.text = "0" // Cambiar a 0 al finalizar
                girarBotella() // Llamar a girarBotella
            }
        }.start() // Iniciar el temporizador
    }

    private fun girarBotella() {
        // Generar un ángulo aleatorio para la animación de giro
        val anguloAleatorio = Random.nextInt(1800, 3600).toFloat()

        // Crear la animación de rotación
        val rotateAnimation = RotateAnimation(
            0f, anguloAleatorio,
            Animation.RELATIVE_TO_SELF, 0.5f,
            Animation.RELATIVE_TO_SELF, 0.5f
        ).apply {
            duration = 3000 // Duración de la animación
            fillAfter = true // Mantener el estado final de la animación
        }

        botella.startAnimation(rotateAnimation) // Iniciar la animación

        // Establecer un listener para detectar el final de la animación
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // No se usa en este caso
            }

            override fun onAnimationEnd(animation: Animation?) {
                // Al finalizar la animación, el texto se establece en 3
                countdownText.text = "3"
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // No se usa en este caso
            }
        })

        // Configurar el comportamiento del reproductor de música
        mediaPlayer.setOnCompletionListener {
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release() // Liberar el reproductor de música
        countdownTimer?.cancel() // Cancelar el temporizador al destruir la actividad
    }
}











