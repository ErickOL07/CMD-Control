package com.proyectoredes.pekka.mitigar_ataques;

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.proyectoredes.pekka.R

public class mitigar_ataque : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.mitigar_ataques)
    }
}
