package com.proyectoredes.pekka.mitigar_ataques

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.proyectoredes.pekka.R

class AclDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_acl_details)

        val targetIp = intent.getStringExtra("targetIp")
        val timestamp = intent.getStringExtra("timestamp")
        val additionalDetails = intent.getStringExtra("additionalDetails")
        val aclResult = intent.getStringExtra("aclResult")

        val tvDetails: TextView = findViewById(R.id.tv_acl_details)
        val btnBackToMitigar: Button = findViewById(R.id.btn_back_to_mitigar_ataques)

        tvDetails.text = """
            IP Bloqueada: $targetIp
            Hora de Bloqueo: $timestamp
            $additionalDetails

        """.trimIndent()

        btnBackToMitigar.setOnClickListener {
            finish() // Vuelve a la actividad de mitigar ataques
        }
    }
}




