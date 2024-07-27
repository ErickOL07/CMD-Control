package com.proyectoredes.cmdcontrol.reglas_de_firewall

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.proyectoredes.cmdcontrol.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.Socket

class reglas_de_salida : AppCompatActivity() {

    var texto = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.reglas_de_salida)

        val botonBloquear: Button = findViewById(R.id.botonBloquear)
        val botonDesbloquear: Button = findViewById(R.id.botonDesbloquear)
        val campoIP: EditText = findViewById(R.id.campoIP)
        val textoSalida: TextView = findViewById(R.id.textoSalida)

        botonBloquear.setOnClickListener {
            val ip = campoIP.text.toString()
            texto = "Bloqueo de IP $ip\n\n"
            val comando = "netsh advfirewall firewall add rule name=\"Bloquear IP $ip\" dir=out  action=block remoteip=$ip"
            enviarComando(comando, textoSalida)
        }

        botonDesbloquear.setOnClickListener {
            val ip = campoIP.text.toString()
            texto = "Desbloqueo de IP $ip\n"
            val comando = "netsh advfirewall firewall delete rule name=\"Bloquear IP $ip\""
            enviarComando(comando, textoSalida)
        }
    }

    private fun enviarComando(comando: String, textoSalida: TextView) {
        CoroutineScope(Dispatchers.IO).launch {
            val resultado = try {
                val pref = getSharedPreferences("ipv4", Context.MODE_PRIVATE)
                val IPv4 = pref.getString("ipv4", "")

                Socket(IPv4, 9999).use { socket ->
                    val sink = socket.getOutputStream().bufferedWriter()
                    val source = socket.getInputStream().bufferedReader()

                    sink.write(comando)
                    sink.newLine()
                    sink.flush()

                    val resultado = StringBuilder()
                    resultado.append(texto)
                    while (true) {
                        val line = source.readLine() ?: break
                        if (line == "FinRespuestaComando") break
                        resultado.append(line).append("\n")
                    }
                    resultado.toString().trim()
                }
            } catch (e: Exception) {
                e.printStackTrace()
                "Error: ${e.message}"
            }
            runOnUiThread {
                textoSalida.text = resultado
            }
        }
    }
}
