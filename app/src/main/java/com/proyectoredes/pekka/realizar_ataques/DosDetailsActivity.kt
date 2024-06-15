package com.proyectoredes.pekka.realizar_ataques

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.proyectoredes.pekka.R

class DosDetailsActivity : AppCompatActivity() {

    private lateinit var tvDosDetails: TextView
    private lateinit var btnBackToMain: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dos_details)

        tvDosDetails = findViewById(R.id.tvDosDetails)
        btnBackToMain = findViewById(R.id.btnBackToMain)

        // Obtener los datos pasados desde la actividad anterior
        val targetUrl = intent.getStringExtra("targetUrl")
        val protocol = intent.getStringExtra("protocol")
        val packetSize = intent.getIntExtra("packetSize", 0)

        // Mostrar los datos iniciales
        tvDosDetails.text = """
            URL objetivo: $targetUrl
            Protocolo: "HTTP-HTTPS"
            Tamaño del paquete: $packetSize bytes
        """.trimIndent()

        // Recibir actualizaciones en tiempo real
        val details = intent.getStringExtra("details")
        if (details != null) {
            tvDosDetails.append("\n\n$details")
        }

        // Manejar clic del botón para regresar
        btnBackToMain.setOnClickListener {
            finish() // Regresa a la actividad anterior (realizar_ataque)
        }
    }
}
