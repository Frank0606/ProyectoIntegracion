package com.example.clientemovil

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.clientemovil.databinding.ActivityVerPerfilBinding
import com.example.clientemovil.poko.Colaborador
import com.example.clientemovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import java.io.ByteArrayOutputStream

class VerPerfil : AppCompatActivity() {

    private lateinit var binding: ActivityVerPerfilBinding
    private lateinit var colaborador: Colaborador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        colaborador = Gson().fromJson(
            intent.getStringExtra("colaborador"),
            Colaborador::class.java
        )

        cargarColaborador(colaborador.noPersonal)

        binding.btnCerrarSesion.setOnClickListener { cerrarSesion() }
        binding.btnActualizar.setOnClickListener {
            val intent = Intent(this, EditarPerfil::class.java)
            intent.putExtra("colaborador", Gson().toJson(colaborador))
            startActivity(intent)
        }

        binding.imEditarFoto.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK).apply {
                type = "image/*"
            }
            seleccionarFoto.launch(intent)
        }
    }

    private fun cerrarSesion() {
        val intent = Intent(
            this,
            Login::class.java
        )
        intent.flags =
            Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
    }

    private fun colocarDatos() {
        binding.apply {
            tvNombre.text = colaborador.nombreColaborador + " " + colaborador.apellidoPaterno + " " + colaborador.apellidoMaterno
            tvCorreo.text = colaborador.correoElectronico
            tvNoPersonal.text = colaborador.noPersonal
            tvCURP.text = colaborador.curp
            tvLicencia.text = colaborador.licencia
            tvVIN.text = colaborador.vin

            cargarFotografia(colaborador.noPersonal)
        }
    }

    private fun cargarColaborador(noPersonal: String) {
        Ion.with(this)
            .load("GET", "${Constantes.URL_WS}/colaborador/no-personal/$noPersonal")
            .asJsonObject()
            .setCallback { e, result ->
                if (e == null) {
                    colaborador = Gson().fromJson(result, Colaborador::class.java)
                    colocarDatos()
                } else {
                    Toast.makeText(this, "Error al cargar el perfil", Toast.LENGTH_SHORT).show()
                    e.printStackTrace()
                }
            }
    }

    private fun cargarFotografia(noPersonal: String) {
        Log.d("Nopersonal: ", noPersonal)
        Ion.with(this)
            .load("GET", "${Constantes.URL_WS}/colaborador/obtener-foto/$noPersonal")
            .withBitmap()
            .intoImageView(binding.imFoto)
    }

    private val seleccionarFoto = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == RESULT_OK) {
            val data = result.data
            val imageUri = data?.data
            if (imageUri != null) {
                binding.imFoto.setImageURI(imageUri)

                // Convertir el URI en un ByteArray y actualizar la fotografía
                actualizarFotografia(imageUri)
            }
        }
    }

    private fun actualizarFotografia(uri: Uri) {
        val fotoBytes = uriToByteArray(uri)
        if (fotoBytes != null) {
            Ion.with(this@VerPerfil)
                .load("PUT", "${Constantes.URL_WS}/colaborador/actualizar-foto/${colaborador.noPersonal}")
                .setHeader("Content-Type", "application/octet-stream")
                .setByteArrayBody(fotoBytes)
                .asString()
                .setCallback { e, result ->
                    Log.d("EditarPerfil", "Respuesta del servidor: $result")
                    if (e == null) {
                        Log.d("EditarPerfil", "Respuesta del servidor: $result")
                    } else {
                        Log.e("Error: ", e.message.toString(), e)
                        Toast.makeText(this@VerPerfil, "Error al actualizar la foto: ${e.message}", Toast.LENGTH_LONG).show()
                    }
                }
        } else {
            Toast.makeText(this, "Error al procesar la imagen", Toast.LENGTH_SHORT).show()
        }
    }

    private fun uriToByteArray(uri: Uri): ByteArray? {
        return try {
            contentResolver.openInputStream(uri)?.use { inputStream ->
                val originalBitmap = BitmapFactory.decodeStream(inputStream)

                // Redimensionar la imagen (ajustar el tamaño según tus necesidades)
                val resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, 300, 300, true)

                // Comprimir la imagen a JPEG con una calidad de 80 (ajustar la calidad según tus necesidades)
                val byteArrayOutputStream = ByteArrayOutputStream()
                resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream)
                byteArrayOutputStream.toByteArray()
            }
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

}
