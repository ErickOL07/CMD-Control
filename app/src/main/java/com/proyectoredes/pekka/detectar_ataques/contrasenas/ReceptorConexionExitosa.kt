package com.proyectoredes.pekka.detectar_ataques.conexiones

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.NetworkInfo
import android.net.wifi.WifiManager
import android.util.Log

class ReceptorConexionExitosa : BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val cm = context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = intent?.getParcelableExtra<NetworkInfo>(WifiManager.EXTRA_NETWORK_INFO)

        if (networkInfo?.isConnected == true) {
            val wifiManager = context.getSystemService(Context.WIFI_SERVICE) as WifiManager
            val wifiInfo = wifiManager.connectionInfo
            Log.d("ReceptorConexionExitosa", "Conexión exitosa a la red: ${wifiInfo.ssid}")
            // registrar la conexión exitosa
        }
    }
}
