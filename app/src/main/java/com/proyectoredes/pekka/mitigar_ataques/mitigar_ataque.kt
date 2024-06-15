package com.proyectoredes.pekka.mitigar_ataques

import android.content.Context
import android.content.Intent
import android.net.wifi.WifiManager
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
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.*

public class mitigar_ataque : AppCompatActivity() {

    private lateinit var etTargetIp: EditText
    private lateinit var btnImplementACL: Button
    private lateinit var aclImplementation: ACLImplementation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mitigar_ataques)

        aclImplementation = ACLImplementation()

        etTargetIp = findViewById(R.id.et_target_ip)
        btnImplementACL = findViewById(R.id.btn_implement_acl)

        btnImplementACL.setOnClickListener {
            val targetIp = etTargetIp.text.toString().trim()
            if (targetIp.isNotEmpty()) {
                // Mostrar la pantalla rosada inmediatamente
                val timestamp = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()).format(Date())
                val initialDetails = """
                    Gateway: ${getGatewayIp()}
                    GRACIAS POR USAR PEKKA
                """.trimIndent()

                val intent = Intent(this, AclDetailsActivity::class.java).apply {
                    putExtra("targetIp", targetIp)
                    putExtra("timestamp", timestamp)
                    putExtra("additionalDetails", initialDetails)
                }
                startActivity(intent)

                // Realizar la operación de ACL en segundo plano
                CoroutineScope(Dispatchers.IO).launch {
                    val aclResult = aclImplementation.implementACL(this@mitigar_ataque, targetIp)
                    withContext(Dispatchers.Main) {
                        // Actualizar los detalles en la pantalla rosada
                        val updatedIntent = Intent("UPDATE_ACL_DETAILS").apply {
                            putExtra("aclResult", aclResult)
                        }
                        sendBroadcast(updatedIntent)
                    }
                }
            } else {
                Toast.makeText(this, "Por favor ingrese una IP válida", Toast.LENGTH_SHORT).show()
            }
        }

        val btnBackToMain: Button = findViewById(R.id.btn_back_to_main)
        btnBackToMain.setOnClickListener {
            finish() // Vuelve al menú principal o actividad anterior
        }
    }

    private fun getGatewayIp(): String {
        val wifiManager = applicationContext.getSystemService(Context.WIFI_SERVICE) as WifiManager
        val dhcpInfo = wifiManager.dhcpInfo
        val gateway = dhcpInfo.gateway
        return (gateway and 0xFF).toString() + "." +
                ((gateway shr 8) and 0xFF) + "." +
                ((gateway shr 16) and 0xFF) + "." +
                ((gateway shr 24) and 0xFF)
    }
}