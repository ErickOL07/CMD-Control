package com.proyectoredes.cmdcontrol

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class TyC : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.tyc)

        val termsText: TextView = findViewById(R.id.tycTexto)
        termsText.text = """            
            Al acceder y utilizar la aplicación, el usuario acepta cumplir con estos términos y condiciones, así como con las leyes y regulaciones aplicables en materia de delitos informáticos y ciberseguridad, lo cual se detalla a continuación:

            Uso Responsable: Al utilizar esta aplicación, el usuario se compromete a hacer un uso responsable y ético de sus funcionalidades. No debe utilizar la aplicación para llevar a cabo actividades ilícitas, vulnerar la seguridad de sistemas informáticos ajenos o comprometer la integridad de datos de terceros.

            Riesgos de Seguridad: Esta aplicación permite el acceso al CMD de la computadora ingresada con privilegios de administrador, lo cual puede presentar riesgos significativos para la seguridad del sistema. El usuario debe asegurarse de utilizar esta funcionalidad únicamente en sistemas que posee o tiene permiso expreso para administrar. El mal uso de esta característica podría resultar en acceso no autorizado a información sensible, alteraciones del sistema operativo o daños a la integridad del sistema.

            Responsabilidad del Usuario: El usuario es el único responsable de sus acciones al utilizar la aplicación. Debe asegurarse de no infringir ninguna ley o regulación vigente en materia de delitos informáticos y ciberseguridad. Cualquier mal uso de la aplicación que resulte en actividades ilícitas será responsabilidad exclusiva del usuario.

            Protección de Datos: CMD Control respeta la privacidad del usuario y se compromete a proteger sus datos personales.
        """.trimIndent()

        val BotonAceptar: Button = findViewById(R.id.BotonAceptar)
        BotonAceptar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
