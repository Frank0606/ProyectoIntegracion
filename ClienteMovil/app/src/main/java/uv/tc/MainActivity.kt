package uv.tc

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import uv.tc.databinding.ActivityMainBinding
import uv.tc.poko.Cliente
import uv.tc.poko.Mensaje
import uv.tc.util.Constantes
import java.io.ByteArrayOutputStream
import java.io.InputStream

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var cliente: Cliente
    private var fotoPerfilBytes: ByteArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        obtenerDatosCliente()
        cargarDatosCliente()

        binding.ivEditarCliente.setOnClickListener {
            val intent = Intent(this, EditarCliente::class.java)
            intent.putExtra("cliente", Gson().toJson(cliente))
            startActivityForResult(intent, REQUEST_EDITAR_CLIENTE)
        }
    }

    override fun onStart() {
        super.onStart()
        obtenerFotoCliente(cliente.idCliente)
        binding.ivEditarImagen.setOnClickListener {
            val intent = Intent(Intent.ACTION_PICK)
            intent.type = "image/*"
            seleccionarFotoPerfil.launch(intent)
        }
    }

    private fun obtenerDatosCliente() {
        val jsonCliente = intent.getStringExtra("cliente")
        if (jsonCliente != null) {
            cliente = Gson().fromJson(jsonCliente, Cliente::class.java)
        }
    }

    private fun cargarDatosCliente() {
        binding.tvNombreCliente.text = "${cliente.nombreC} ${cliente.apellidoPaterno} ${cliente.apellidoMaterno}"
        binding.tvCorreo.text = cliente.correo
        binding.tvTelefono.text = cliente.telefono
        binding.tvFechaNacimiento.text = cliente.fechaNacimiento
        binding.tvFechaInscripcion.text = cliente.fechaInscripcion
        binding.tvEstatura.text = cliente.estatura.toString()
        binding.tvPeso.text = cliente.peso.toString()
        binding.tvEntrenadorD.text = cliente.nombreCo
    }

    private fun obtenerFotoCliente(idCliente: Int) {
        Ion.with(this@MainActivity)
            .load("GET", "${Constantes().URL_WS}cliente/Obtener-Foto/${idCliente}")
            .asString()
            .setCallback { e, result ->
                if (e == null) {
                    cargarFotoCliente(result)
                } else {
                    Toast.makeText(this@MainActivity, "Error: ${e.message}", Toast.LENGTH_LONG).show()
                }
            }
    }

    private fun cargarFotoCliente(json: String) {
        if (json.isNotEmpty()) {
            val gson = Gson()
            val clienteFoto = gson.fromJson(json, Cliente::class.java)
            if (clienteFoto.foto != null) {
                try {
                    val imgBytes = Base64.decode(clienteFoto.foto, Base64.DEFAULT)
                    val imgBitmap = BitmapFactory.decodeByteArray(imgBytes, 0, imgBytes.size)
                    binding.ivFoto.setImageBitmap(imgBitmap)
                } catch (e: Exception) {
                    Toast.makeText(this@MainActivity, "Error img: ${e.message}", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(this@MainActivity, "No cuenta con una foto de perfil", Toast.LENGTH_LONG).show()
            }
        }
    }

    private val seleccionarFotoPerfil = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val data = result.data
            val imgURI = data?.data
            if (imgURI != null) {
                fotoPerfilBytes = uriToByteArray(imgURI)
                if (fotoPerfilBytes != null) {
                    subirFotoPerfil(cliente.idCliente)
                }
            }
        }
    }

    private fun uriToByteArray(uri: Uri): ByteArray? {
        return try {
            val inputStream: InputStream? = contentResolver.openInputStream(uri)
            val bitmap = BitmapFactory.decodeStream(inputStream)
            val byteArrayOutputStream = ByteArrayOutputStream()
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream)
            byteArrayOutputStream.toByteArray()
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun subirFotoPerfil(idCliente: Int) {
        Ion.with(this@MainActivity)
            .load("PUT", "${Constantes().URL_WS}cliente/Subir-Foto/${idCliente}")
            .setByteArrayBody(fotoPerfilBytes)
            .asString()
            .setCallback { e, result ->
                if (e == null) {
                    val gson = Gson()
                    val mensaje = gson.fromJson(result, Mensaje::class.java)
                    Toast.makeText(this@MainActivity, mensaje.mensaje, Toast.LENGTH_LONG).show()
                    if (!mensaje.error) {
                        obtenerFotoCliente(cliente.idCliente)
                    }
                } else {
                    Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
                }
            }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_EDITAR_CLIENTE && resultCode == Activity.RESULT_OK) {
            val jsonClienteActualizado = data?.getStringExtra("clienteActualizado")
            if (jsonClienteActualizado != null) {
                cliente = Gson().fromJson(jsonClienteActualizado, Cliente::class.java)
                cargarDatosCliente()
            }
        }
    }

    companion object {
        private const val REQUEST_EDITAR_CLIENTE = 1
    }
}
