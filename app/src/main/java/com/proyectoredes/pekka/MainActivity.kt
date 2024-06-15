package com.proyectoredes.pekka

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.proyectoredes.pekka.detectar_ataques.*
import com.proyectoredes.pekka.mitigar_ataques.*
import com.proyectoredes.pekka.realizar_ataques.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val botonDetectar: Button = findViewById(R.id.detectarAtaquesBotonMMenu2)

        botonDetectar.setOnClickListener{

            val intent: Intent = Intent(this, detectar_ataque::class.java)
            startActivity(intent)

        }

        val botonMitigar: Button = findViewById(R.id.MitigarAtaquesBotonMMenu)

        botonMitigar.setOnClickListener{

            val intent: Intent = Intent(this, mitigar_ataque::class.java)
            startActivity(intent)

        }

        val botonRealizar: Button = findViewById(R.id.realizarAtaquesBotonMMenu)

        botonRealizar.setOnClickListener{

            val intent: Intent = Intent(this, realizar_ataque::class.java)
            startActivity(intent)


        }

    }
}