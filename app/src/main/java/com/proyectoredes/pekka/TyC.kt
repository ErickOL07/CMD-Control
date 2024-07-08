package com.proyectoredes.pekka

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

            Propósito Educativo: Esta aplicación ha sido diseñada exclusivamente con fines educativos y de prueba. Su objetivo es brindar a los usuarios la oportunidad de aprender sobre ciberseguridad, concienciarse sobre los riesgos de los ciberataques y adquirir conocimientos en el campo de la protección de datos y sistemas informáticos.

            Uso Responsable: Al utilizar esta aplicación, el usuario se compromete a hacer un uso responsable y ético de sus funcionalidades. No debe utilizar la aplicación para llevar a cabo actividades ilícitas, vulnerar la seguridad de sistemas informáticos ajenos o comprometer la integridad de datos de terceros.

            Responsabilidad del Usuario: El usuario es el único responsable de sus acciones al utilizar la aplicación. Debe asegurarse de no infringir ninguna ley o regulación vigente en materia de delitos informáticos y ciberseguridad. Cualquier mal uso de la aplicación que resulte en actividades ilícitas será responsabilidad exclusiva del usuario.

            Cooperación con las Autoridades: En caso de detectar un uso indebido de la aplicación para cometer delitos informáticos, los desarrolladores se reservan el derecho de cooperar con las autoridades competentes y proporcionar la información necesaria para esclarecer la situación y prevenir posibles consecuencias legales.

            Protección de Datos: PEKKA respeta la privacidad del usuario y se compromete a proteger sus datos personales.
        """.trimIndent()

        val BotonAceptar: Button = findViewById(R.id.BotonAceptar)
        BotonAceptar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
