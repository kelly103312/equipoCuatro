package com.example.miniproyecto1.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.miniproyecto1.R
import android.widget.ImageView
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashActivity : AppCompatActivity() {

    private val splashScope = MainScope() // Crea un scope de corutinas

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        // Cargar el GIF animado en la ImageView
        val bottleIcon: ImageView = findViewById(R.id.bottle_icon)
        Glide.with(this)
            .load(R.drawable.ic_bottle_anim) // Asegúrate de que este sea el nombre correcto de tu GIF
            .into(bottleIcon)

        // Iniciar una corutina para manejar el tiempo de espera
        splashScope.launch {
            delay(3000) // Cambia el tiempo a 3000 ms (3 segundos) o lo que consideres necesario

            // Navegar al MainActivity después del splash
            val intent = Intent(this@SplashActivity, HomeActivity::class.java)
            startActivity(intent)
            finish() // Cierra la actividad para que no se pueda volver al splash con el botón atrás
        }
    }


}

