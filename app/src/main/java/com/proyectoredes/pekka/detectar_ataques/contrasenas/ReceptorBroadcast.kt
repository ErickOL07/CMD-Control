package com.proyectoredes.pekka.detectar_ataques.contrasenas

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.net.wifi.WifiInfo
import android.net.wifi.WifiManager
import android.util.Log

class ReceptorBroadcast : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (WifiManager.NETWORK_STATE_CHANGED_ACTION == intent.action) {
            val wifiInfo = intent.getParcelableExtra<WifiInfo>(WifiManager.EXTRA_WIFI_INFO)
            Log.d("ReceptorBroadcast", "Cambio en el estado del WiFi: ${wifiInfo?.ssid}")
        }
    }

    fun registerNetworkReceiver(context: Context) {
        val filter = IntentFilter()
        filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION)
        context.registerReceiver(this, filter)
    }

    fun unregisterNetworkReceiver(context: Context) {
        context.unregisterReceiver(this)
    }
}
