package com.proyectoredes.pekka.detectar_ataques

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.wifi.WifiManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.proyectoredes.pekka.R
import com.proyectoredes.pekka.detectar_ataques.conexiones.ReceptorConexionExitosa
import com.proyectoredes.pekka.detectar_ataques.conexiones.ReceptorConexionFallida
import com.proyectoredes.pekka.detectar_ataques.contrasenas.ReceptorBroadcast
import com.proyectoredes.pekka.detectar_ataques.contrasenas.DescubrirRedes

class detectar_ataque : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.detectar_ataques)

        receptorBroadcast.registerNetworkReceiver(this)

        val filterConexionExitosa = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        registerReceiver(receptorConexionExitosa, filterConexionExitosa)

        val filterConexionFallida = IntentFilter(WifiManager.SUPPLICANT_STATE_CHANGED_ACTION)
        registerReceiver(receptorConexionFallida, filterConexionFallida)

        startContinuousTasks()
    }

}
