package com.proyectoredes.pekka.detectar_ataques

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.proyectoredes.pekka.R

class detectar_ataque : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detectar_ataques)

        val btnAbrirNetGuard: Button = findViewById(R.id.botonAbrirNetGuard)
        btnAbrirNetGuard.setOnClickListener {
            val abrirNetGuard = AbrirNetGuard(this)
            abrirNetGuard.abrirApp()
        }

        val btnRegresarMenu: Button = findViewById(R.id.botonVolver)
        btnRegresarMenu.setOnClickListener {
            finish()
        }

        val botonMostrarCreditos: Button = findViewById(R.id.botonMostrarCreditos)
        botonMostrarCreditos.setOnClickListener {
            mostrarCreditosNetGuardBoton()
        }

        mostrarCreditosNetGuard()
    }

    private fun mostrarCreditosNetGuard() {
        val creditos = """
            NetGuard es una aplicación de firewall sin necesidad de root para Android, desarrollada por Marcel Bokhorst (M66B).            
            Visita el repositorio de NetGuard en GitHub para más detalles: https://github.com/M66B/NetGuard
        """.trimIndent()

        val textoCreditosNetGuard: TextView = findViewById(R.id.textoCreditosNetGuard)
        textoCreditosNetGuard.text = creditos
    }


    private fun mostrarCreditosNetGuardBoton() {
        val url = "https://github.com/M66B/NetGuard"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }
}
