package com.proyectoredes.pekka.detectar_ataques.conexiones

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
import android.util.Log

class ReceptorConexionFallida : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val suplError = intent?.getIntExtra(WifiManager.EXTRA_SUPPLICANT_ERROR, -1)
        if (suplError == WifiManager.ERROR_AUTHENTICATING) {
            Log.d("ReceptorConexionFallida", "Error de autenticación: intento fallido de conexión a la red WiFi.")
            // registrar la conexión fallida
        }
    }
}
