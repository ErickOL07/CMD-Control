package com.proyectoredes.pekka.ingresar_comandos

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.proyectoredes.pekka.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okio.buffer
import okio.sink
import okio.source
import java.net.Socket
import com.proyectoredes.pekka.MainActivity

class ingresar_comandos : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mitigar_ataques)

        val botonEnviar: Button = findViewById(R.id.botonEnviar)
        val campoComando: EditText = findViewById(R.id.campoComando)
        val textoSalida: TextView = findViewById(R.id.textoSalida)

        botonEnviar.setOnClickListener {
            val comando = campoComando.text.toString()
            CoroutineScope(Dispatchers.IO).launch {
                val resultado = enviarComando(comando)
                runOnUiThread {
                    textoSalida.text = resultado
                }
            }
        }
    }

    private fun enviarComando(comando: String): String {
        return try {
            val pref = getSharedPreferences("ipv4", Context.MODE_PRIVATE)
            val IPv4 = pref.getString("ipv4", "")

            Socket(IPv4, 9999).use { socket ->
                val sink = socket.sink().buffer()
                val source = socket.source().buffer()

                sink.writeUtf8(comando)
                sink.writeUtf8("\n")
                sink.flush()

                val resultado = StringBuilder()
                while (true) {
                    val line = source.readUtf8Line()
                    if (line == "FinRespuestaComando") break
                    resultado.append(line).append("\n")
                }
                resultado.toString().trim()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            "Error: ${e.message}"
        }
    }


}

