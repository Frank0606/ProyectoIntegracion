package com.example.clientemovil

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clientemovil.databinding.ActivityEditarPerfilBinding
import com.example.clientemovil.poko.Colaborador
import com.example.clientemovil.poko.Mensaje
import com.example.clientemovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import java.io.ByteArrayOutputStream

class EditarPerfil : AppCompatActivity() {

    private lateinit var binding: ActivityEditarPerfilBinding
    private lateinit var colaborador: Colaborador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditarPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cargarDatos()
    }

    private fun cargarDatos() {
        colaborador = Gson().fromJson(intent.getSerializableExtra("colaborador").toString(), Colaborador::class.java)
        binding.apply {
            etNombreColaborador.setText(colaborador.nombreColaborador)
            etApellidoPaterno.setText(colaborador.apellidoPaterno)
            etApellidoMaterno.setText(colaborador.apellidoMaterno)
            etCurp.setText(colaborador.curp)
            etCorreoElectronico.setText(colaborador.correoElectronico)
            etNoPersonal.setText(colaborador.noPersonal)
            etContrasenia.setText(colaborador.contrasenia)
            etLicencia.setText(colaborador.licencia)
            btnGuardar.setOnClickListener {
                editarColaborador()
            }
        }
    }

    private fun editarColaborador() {
        if (validarCampos()) {
            val colaboradorEditado = Colaborador(
                colaborador.idColaborador,
                binding.etNombreColaborador.text.toString(),
                binding.etApellidoPaterno.text.toString(),
                binding.etApellidoMaterno.text.toString(),
                binding.etCurp.text.toString(),
                binding.etCorreoElectronico.text.toString(),
                binding.etNoPersonal.text.toString(),
                binding.etContrasenia.text.toString(),
                colaborador.licencia,
                colaborador.fotografia,
                colaborador.idRol,
                colaborador.tipoRol,
                colaborador.idUnidad,
                colaborador.vin
            )

            actualizarDatos(colaboradorEditado)
        } else {
            Toast.makeText(this@EditarPerfil, "Campos inválidos", Toast.LENGTH_LONG).show()
        }
    }

    private fun actualizarDatos(colaboradorEditado: Colaborador) {
        Ion.with(this@EditarPerfil)
            .load("PUT", "${Constantes.URL_WS}/colaborador/actualizar")
            .setHeader("Content-Type", "application/json")
            .setStringBody(Gson().toJson(colaboradorEditado))
            .asString()
            .setCallback { e, result ->
                if (e == null) {
                    Log.d("EditarPerfil", "Respuesta del servidor: $result")
                    val mensaje = Gson().fromJson(result, Mensaje::class.java)
                    if (!mensaje.error) {
                        Toast.makeText(this@EditarPerfil, "Datos actualizados", Toast.LENGTH_LONG).show()
                        val intent = Intent(this@EditarPerfil, VerPerfil::class.java)
                        intent.putExtra("colaborador", Gson().toJson(colaboradorEditado))
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@EditarPerfil, mensaje.mensaje, Toast.LENGTH_LONG).show()
                    }
                } else {
                    Log.e("Error: ", e.message.toString(), e)
                    Toast.makeText(this@EditarPerfil, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun validarCampos(): Boolean {
        var valid = true
        binding.apply {

            // Validar nombre del colaborador
            if (etNombreColaborador.text.toString().isEmpty() || etNombreColaborador.text.toString().length > 50) {
                etNombreColaborador.error = "Nombre inválido"
                valid = false
            }

            // Validar apellido paterno
            if (etApellidoPaterno.text.toString().isEmpty() || etApellidoPaterno.text.toString().length > 50) {
                etApellidoPaterno.error = "Apellido Paterno inválido"
                valid = false
            }

            // Validar apellido materno
            if (etApellidoMaterno.text.toString().isEmpty() || etApellidoMaterno.text.toString().length > 50) {
                etApellidoMaterno.error = "Apellido Materno inválido"
                valid = false
            }

            // Validar correo electrónico
            val emailPattern = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,3}$"
            if (etCorreoElectronico.text.toString().isEmpty() ||
                etCorreoElectronico.text.toString().length > 50 ||
                !etCorreoElectronico.text.toString().matches(emailPattern.toRegex())) {
                etCorreoElectronico.error = "Correo electrónico inválido"
                valid = false
            }

            // Validar contraseña
            if (etContrasenia.text.toString().isEmpty() || etContrasenia.text.toString().length > 50) {
                etContrasenia.error = "Contraseña inválida"
                valid = false
            }

            // Validar CURP
            if (etCurp.text.toString().isEmpty() || etCurp.text.toString().length > 18) {
                etCurp.error = "CURP inválido"
                valid = false
            }

            // Validar Licencia
            if (etLicencia.text.toString().isEmpty() || etLicencia.text.toString().length > 9) {
                etLicencia.error = "Número de licencia inválido"
                valid = false
            }
        }
        return valid
    }
}