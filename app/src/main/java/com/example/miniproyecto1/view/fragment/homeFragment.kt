package com.example.miniproyecto1.view.fragment

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.miniproyecto1.R
import com.example.miniproyecto1.databinding.FragmentChallengeBinding
import com.example.miniproyecto1.databinding.FragmentHomeBinding
import kotlin.random.Random

/**
 * A simple [Fragment] subclass.
 * Use the [homeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding
    private var isAudioOn = true
    private lateinit var mediaPlayer: MediaPlayer
    private var countdownTimer: CountDownTimer? = null // Variable para el temporizador

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater)
        binding.lifecycleOwner = this
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.fondo_musica)

        setupAudioToggle()
        navigationFragmentB()
        navigationInstruccionFragment()
        navigationChallengeFragment()
        shareFunction()






        // Iniciar la cuenta regresiva y animación al presionar el botón de giro
        binding.spinButton.setOnClickListener {
            if (!mediaPlayer.isPlaying && isAudioOn) {
                mediaPlayer.start()
            }
            reiniciarCuentaRegresiva()
        }
    }



    private fun navigationChallengeFragment(){
        binding.iconChallenges.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_challengeFragment)
        }
    }

    private fun navigationInstruccionFragment(){
        binding.iconInstructions.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_instructionFragment)
        }
    }

    private fun navigationFragmentB() {
        binding.StarFragmentB.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_fragmentB)
        }
    }

    private fun setupAudioToggle() {
        binding.iconAudio.setOnClickListener {
            toggleAudio()
        }
    }

    private fun toggleAudio() {
        if (isAudioOn) {
            binding.iconAudio.setImageResource(R.drawable.audiooff)
            pauseAudio()
            Log.d("AudioToggle", "audio pausado")
        } else {
            binding.iconAudio.setImageResource(R.drawable.audio_icon)
            playAudio()
            Log.d("AudioToggle", "audio encendido")
        }
        isAudioOn = !isAudioOn
    }

    private fun playAudio() {
        if (!mediaPlayer.isPlaying) {
            mediaPlayer.start()
        }
    }

    private fun pauseAudio() {
        if (mediaPlayer.isPlaying) {
            mediaPlayer.pause()
            mediaPlayer.seekTo(0)
        }
    }

    private fun shareFunction() {
        binding.iconShare.setOnClickListener {
            val shareIntent = Intent(Intent.ACTION_SEND).apply {
                type = "text/plain"
                putExtra(Intent.EXTRA_SUBJECT, "App pico botella")
                putExtra(
                    Intent.EXTRA_TEXT,
                    "App pico botella - Solo los valientes lo juegan !!\nDescarga la app aquí: https://play.google.com/store/apps/details?id=com.nequi.MobileApp&hl=es_419&gl=es"
                )
            }

            startActivity(Intent.createChooser(shareIntent, "Compartir App Pico Botella"))
        }
    }

    private fun reiniciarCuentaRegresiva() {
        // Reiniciar el texto del temporizador a 3
        binding.countdownText.text = "3"

        // Cancelar el temporizador anterior si está en ejecución
        countdownTimer?.cancel()

        // Crear un nuevo temporizador que comienza desde 3
        countdownTimer = object : CountDownTimer(3000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val segundosRestantes = (millisUntilFinished / 1000).toInt()
                binding.countdownText.text = segundosRestantes.toString()
            }

            override fun onFinish() {
                binding.countdownText.text = "0" // Cambiar a 0 al finalizar
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

        binding.bottleImage.startAnimation(rotateAnimation) // Iniciar la animación

        // Establecer un listener para detectar el final de la animación
        rotateAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                // No se usa en este caso
            }

            override fun onAnimationEnd(animation: Animation?) {
                // Al finalizar la animación, el texto se establece en 3
                binding.countdownText.text = "3"
                findNavController().navigate(R.id.action_homeFragment_to_dialogFragment2)
            }

            override fun onAnimationRepeat(animation: Animation?) {
                // No se usa en este caso
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mediaPlayer.release() // Liberar el reproductor de música al destruir el fragmento
        countdownTimer?.cancel() // Cancelar el temporizador
    }
}