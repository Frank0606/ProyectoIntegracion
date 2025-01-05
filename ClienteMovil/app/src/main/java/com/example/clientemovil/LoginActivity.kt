package com.example.clientemovil

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.clientemovil.databinding.ActivityLoginBinding
import com.example.clientemovil.poko.LoginColaborador
import com.example.clientemovil.util.Contantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class LoginActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        binding.btnEntrar.setOnClickListener {
            val noPersonal = binding.txtUsuario.text.toString()
            val contrasenia = binding.txtContrasenia.text.toString()
            if (camposValidos(noPersonal, contrasenia)){
                verificarCredenciales(noPersonal, contrasenia)
            }
        }
    }

    fun camposValidos(noPersonal : String, contrasenia : String) : Boolean {
        var camposValidos = true
        if(noPersonal.isEmpty()){
            camposValidos = false
            binding.tvErrorUsuario.setError("¡Numero de personal obligatorio!")
        }
        if(contrasenia.isEmpty()){
            camposValidos = false
            binding.tvErrorContrasenia.setError("Contraseña obligatoria")
        }
        return camposValidos
    }

    fun verificarCredenciales(noPersonal: String, contrasenia: String) {
        Ion.getDefault(this@LoginActivity).conscryptMiddleware.enable(false)

        val credencialesJson = """
            {
                "noPersonal": "$noPersonal",
                "contrasenia": "$contrasenia"
            }
        """.trimIndent()

        Ion.with(this@LoginActivity)
            .load("POST", "${Contantes().URL_WS}iniciar-sesion/conductor")
            .setHeader("Content-Type", "application/json")
            .setStringBody(credencialesJson)
            .asString()
            .setCallback { e, result ->
                if (e == null) {
                    serializarInformacion(result)
                } else {
                    Toast.makeText(this@LoginActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    fun serializarInformacion(json: String) {
        val gson = Gson()

        try {
            val respuestaLoginColaborador = gson.fromJson(json, LoginColaborador::class.java)
            Toast.makeText(this@LoginActivity, respuestaLoginColaborador.mensaje, Toast.LENGTH_LONG).show()
            if (!respuestaLoginColaborador.error) {
                val colaboradorGSON = gson.toJson(respuestaLoginColaborador.colaborador)
                irPantallaPrincipal(colaboradorGSON)
            }
        } catch (exception: Exception) {
            Toast.makeText(this@LoginActivity, "Error al procesar la respuesta JSON: ${exception.message}", Toast.LENGTH_LONG).show()
        }
    }


    fun irPantallaPrincipal(colaborador : String){
        val intent = Intent(this@LoginActivity, MainActivity::class.java)
        intent.putExtra("colaborador", colaborador)
        startActivity(intent)
        finish()
    }
}