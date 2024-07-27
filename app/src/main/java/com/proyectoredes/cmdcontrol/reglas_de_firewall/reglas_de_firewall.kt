package com.proyectoredes.cmdcontrol.reglas_de_firewall

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.proyectoredes.cmdcontrol.R

class reglas_de_firewall : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reglas_de_firewall)

        val botonReglas_de_entrada: Button = findViewById(R.id.botonReglas_de_entrada)
        val botonReglas_de_salida: Button = findViewById(R.id.botonReglas_de_salida)

        botonReglas_de_entrada.setOnClickListener {
            startActivity(Intent(this, reglas_de_entrada::class.java))
        }

        botonReglas_de_salida.setOnClickListener {
            startActivity(Intent(this, reglas_de_salida::class.java))
        }
    }

}
