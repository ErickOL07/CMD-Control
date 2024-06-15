package com.proyectoredes.pekka.detectar_ataques.contrasenas

import android.content.Context
import android.net.nsd.NsdManager
import android.net.nsd.NsdServiceInfo
import android.util.Log

class DescubrirRedes {
    fun registrarServicio(port: Int, context: Context) {
        val info = NsdServiceInfo().apply {
            serviceName = "Servicio"
            serviceType = "_http._tcp."
            setPort(port)
        }

        val nsdManager = context.getSystemService(Context.NSD_SERVICE) as NsdManager
        nsdManager.registerService(info, NsdManager.PROTOCOL_DNS_SD, object : NsdManager.RegistrationListener {
            override fun onServiceRegistered(NsdServiceInfo: NsdServiceInfo) {
                Log.d("NSD", "Service registered: ${NsdServiceInfo.serviceName}")
            }

            override fun onRegistrationFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
                Log.d("NSD", "Service registration failed: $errorCode")
            }

            override fun onServiceUnregistered(arg0: NsdServiceInfo) {
                Log.d("NSD", "Service unregistered: ${arg0.serviceName}")
            }

            override fun onUnregistrationFailed(serviceInfo: NsdServiceInfo, errorCode: Int) {
                Log.d("NSD", "Service unregistration failed: $errorCode")
            }
        })
    }

    fun descubrimientoServicios(context: Context) {
        val nsdManager = context.getSystemService(Context.NSD_SERVICE) as NsdManager
        val discoveryListener = object : NsdManager.DiscoveryListener {
            override fun onDiscoveryStarted(regType: String) {
                Log.d("NSD", "NSD iniciado.")
            }

            override fun onServiceFound(service: NsdServiceInfo) {
                Log.d("NSD", "NSD exitoso: $service")
                // Implement your logic to check if the service is one you are interested in
            }

            override fun onServiceLost(service: NsdServiceInfo) {
                Log.d("NSD", "NSD perdido: $service")
            }

            override fun onDiscoveryStopped(serviceType: String) {
                Log.d("NSD", "NSD detenido: $serviceType")
            }

            override fun onStartDiscoveryFailed(serviceType: String, errorCode: Int) {
                Log.d("NSD", "Error al iniciar NSD: $errorCode")
            }

            override fun onStopDiscoveryFailed(serviceType: String, errorCode: Int) {
                Log.d("NSD", "Error al detener NSD: $errorCode")
            }
        }

        nsdManager.discoverServices("_http._tcp.", NsdManager.PROTOCOL_DNS_SD, discoveryListener)
    }

    fun escaneoRed() {
        val ipBase = "192.168.1." // Ajusta esto según la subred de tu red
        for (i in 1..254) {
            val ip = "$ipBase$i"
            try {
                val p = Runtime.getRuntime().exec("ping -c 1 $ip")
                val r = p.waitFor()
                if (r == 0) {
                    Log.d("Escaneo de red", "Dispositivo encontrado: $ip")
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
