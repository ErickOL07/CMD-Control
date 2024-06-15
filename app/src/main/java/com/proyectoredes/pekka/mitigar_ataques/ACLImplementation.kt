package com.proyectoredes.pekka.mitigar_ataques

import android.content.Context
import android.net.wifi.WifiManager
import android.util.Log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.InetAddress
import java.net.Socket

class ACLImplementation {
    suspend fun implementACL(context: Context, targetIp: String): String {
        return withContext(Dispatchers.IO) {
            try {
                val wifiManager = context.applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
                val dhcpInfo = wifiManager.dhcpInfo
                val gateway = dhcpInfo.gateway

                Log.d("ACLImplementation", "Bloqueando tráfico hacia: $targetIp a través del gateway: $gateway")

                // Intentar conectar a la IP bloqueada
                val address = InetAddress.getByName(targetIp)
                val socket = Socket(address, 80)
                if (socket.isConnected) {
                    socket.close()
                    Log.d("ACLImplementation", "Conexión permitida a: $targetIp")
                    return@withContext "Conexión permitida a: $targetIp"
                } else {
                    Log.d("ACLImplementation", "Acceso denegado a: $targetIp")
                    return@withContext "Acceso denegado a: $targetIp"
                }
            } catch (e: Exception) {
                e.printStackTrace()
                return@withContext "Error: ${e.message}"
            }
        }
    }
}