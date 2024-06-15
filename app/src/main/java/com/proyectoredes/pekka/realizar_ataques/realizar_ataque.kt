package com.proyectoredes.pekka.realizar_ataques

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.proyectoredes.pekka.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import com.proyectoredes.pekka.realizar_ataques.DoSAttack

public class realizar_ataque : AppCompatActivity() {

    private lateinit var etTargetUrl: EditText
    private lateinit var etIterations: EditText
    private lateinit var btnRealizarDoS: Button
    private lateinit var btnBackToMain: Button
    private lateinit var dosAttack: DoSAttack

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.realizar_ataques)

        etTargetUrl = findViewById(R.id.et_target_url)
        etIterations = findViewById(R.id.et_iterations)
        btnRealizarDoS = findViewById(R.id.btn_realizar_dos)
        btnBackToMain = findViewById(R.id.btn_back_to_main)

        dosAttack = DoSAttack()

        btnRealizarDoS.setOnClickListener {
            val targetUrl = etTargetUrl.text.toString().trim()
            val iterationsStr = etIterations.text.toString().trim()

            if (targetUrl.isNotEmpty() && iterationsStr.isNotEmpty()) {
                val iterations = iterationsStr.toIntOrNull() ?: 100 // Por defecto 100 iteraciones si la conversión falla
                val packetSize = 1024 // Puedes ajustar el tamaño del paquete según sea necesario

                // Iniciar la nueva actividad para mostrar los detalles del ataque
                val intent = Intent(this, DosDetailsActivity::class.java).apply {
                    putExtra("targetUrl", targetUrl)
                    putExtra("protocol", "HTTP POST")
                    putExtra("packetSize", packetSize)
                    putExtra("iterations", iterations)
                }

                startActivity(intent)

                // Iniciar el ataque DoS
                dosAttack.startDosAttack(targetUrl, packetSize, iterations) { result ->
                    runOnUiThread {
                        // Aquí puedes actualizar los detalles del ataque en la nueva pantalla si es necesario
                        Toast.makeText(this@realizar_ataque, result, Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                // Manejar el caso donde no se ingresa ninguna URL o número de iteraciones
                runOnUiThread {
                    Toast.makeText(
                        this@realizar_ataque,
                        "Por favor ingrese una URL válida y un número de iteraciones",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }

        btnBackToMain.setOnClickListener {
            finish() // Vuelve al menú principal o actividad anterior
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        dosAttack.stopDosAttack() // Detener el ataque DoS si la actividad se destruye
    }
}
