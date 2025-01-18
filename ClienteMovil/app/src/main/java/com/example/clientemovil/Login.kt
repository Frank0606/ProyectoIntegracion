package com.example.clientemovil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clientemovil.databinding.ActivityLoginBinding
import com.example.clientemovil.poko.LoginColaborador
import com.example.clientemovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class Login : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private var TAG = "LoginActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    override fun onStart() {
        super.onStart()
        binding.btnEntrar.setOnClickListener {
            val noPersonal = binding.etNoPersonal.text.toString().uppercase()
            val contrasena = binding.etContrasena.text.toString()
            if (validarCamposCliente(noPersonal, contrasena)) {
                validarCredenciales(noPersonal, contrasena)
            }
        }

    }

    private fun validarCamposCliente(noPersonal: String, contrasena: String): Boolean {
        var camposValidos = true
        if (noPersonal.isEmpty() || noPersonal.length != 4 || !noPersonal.startsWith("C")) {
            camposValidos = false
            binding.etNoPersonal.error = "Campo no. personal vacío o incorrecto"
        }
        if (contrasena.isEmpty() || contrasena.length > 50) {
            camposValidos = false
            binding.etContrasena.error = "Campo contraseña vacío o demasiado largo"
        }

        return camposValidos
    }

    private fun validarCredenciales(noPersonal: String, contrasena: String) {
        //Configuracion de la biblioteca, pero solo se tiene que hacer una vez
        Ion.getDefault(this).conscryptMiddleware.enable(false)
        val credencialesJson = """
            {
                "noPersonal": "$noPersonal",
                "contrasenia": "$contrasena"
            }
        """.trimIndent()

        Log.d(TAG, "Credenciales JSON: $credencialesJson")
        // Configuracion del WS
        Ion.with(this@Login)
            .load("POST", "${Constantes.URL_WS}/iniciar-sesion/conductor")
            .setHeader("Content-Type", "application/json")
            .setStringBody(credencialesJson)
            .asString()
            .setCallback { e, result ->
                if (e == null) {
                    Log.d(TAG, "Respuesta del servidor: $result")
                    serializarInformacion(result)
                } else {
                    Toast.makeText(this@Login, "Error: ${e.message}", Toast.LENGTH_LONG)
                        .show()
                }
            }
    }

    private fun serializarInformacion(respuesta: String) {
        val gson = Gson()
        try {
            val respuestaLoginColaborador = gson.fromJson(respuesta, LoginColaborador::class.java)
            Toast.makeText(this@Login, respuestaLoginColaborador.mensaje, Toast.LENGTH_LONG)
                .show()
            if (!respuestaLoginColaborador.error) {
                val colaboradorGSON = gson.toJson(respuestaLoginColaborador.colaborador)
                irPantallaPrincipal(colaboradorGSON)
                finish()
            }
        } catch (exception: Exception) {
            Toast.makeText(
                this@Login,
                "Error al procesar la respuesta JSON: ${exception.message}",
                Toast.LENGTH_LONG
            ).show()
            Log.e(TAG, "Error al procesar la respuesta JSON", exception)
        }
    }

    private fun irPantallaPrincipal(colaboradorJson: String) {
        val intent = Intent(this@Login, Principal::class.java)
        intent.putExtra("colaborador", colaboradorJson)
        startActivity(intent)
        finish()
    }

}