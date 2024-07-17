package com.proyectoredes.pekka

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.proyectoredes.pekka.implementar_acl.*
import com.proyectoredes.pekka.ingresar_comandos.*
import android.util.Log


class MainActivity : AppCompatActivity() {

    lateinit var campoIPv4: EditText
    lateinit var botonRegistrar: Button
    var IPv4 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        campoIPv4 = findViewById(R.id.campoIPv4)
        botonRegistrar = findViewById(R.id.botonRegistrar)
        botonRegistrar.setOnClickListener {
            IPv4 = campoIPv4.text.toString()

            val sharedPref = getSharedPreferences("ipv4", Context.MODE_PRIVATE)
            val editor = sharedPref.edit()
            editor.putString("ipv4", IPv4)
            editor.apply()
            Log.d("IPv4", "IPv4: $IPv4")
        }


        val botonMitigar: Button = findViewById(R.id.implementarACLBotonMMenu)
        botonMitigar.setOnClickListener {
            val intent: Intent = Intent(this, implementar_acl::class.java)
            startActivity(intent)
        }

        val botonRealizar: Button = findViewById(R.id.ingresarComandosBotonMMenu)
        botonRealizar.setOnClickListener {
            val intent: Intent = Intent(this, ingresar_comandos::class.java)
            startActivity(intent)
        }
    }
}

