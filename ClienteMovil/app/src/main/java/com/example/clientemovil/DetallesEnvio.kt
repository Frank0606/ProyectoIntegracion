package com.example.clientemovil

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.clientemovil.databinding.ActivityDetallesEnvioBinding
import com.example.clientemovil.poko.Cliente
import com.example.clientemovil.poko.Envio
import com.example.clientemovil.poko.Mensaje
import com.example.clientemovil.poko.Paquete
import com.example.clientemovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class DetallesEnvio : AppCompatActivity() {

    private lateinit var binding: ActivityDetallesEnvioBinding
    private var clientes = listOf<Cliente>()
    private lateinit var envio: Envio

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetallesEnvioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        envio = Gson().fromJson(intent.getStringExtra("envio"), Envio::class.java)
        val colaborador = intent.getStringExtra("colaborador")

        binding.ibPerfil2.setOnClickListener {
            val intent = Intent(this, VerPerfil::class.java)
            intent.putExtra("colaborador", colaborador)
            startActivity(intent)
        }

        cargarClientes()

        cargarPaquetes(envio.idEnvio)
    }

    fun cargarClientes() {
        binding.progressBar.visibility = View.VISIBLE
        Ion.with(this)
            .load("GET", "${Constantes.URL_WS}/cliente/todos")
            .asJsonArray()
            .setCallback { e, result ->
                binding.progressBar.visibility = View.GONE
                if (e == null) {
                    clientes = result.map { json ->
                        Cliente(
                            idCliente = Integer.parseInt(json.asJsonObject.get("idCliente").asString),
                            nombreCliente = json.asJsonObject.get("nombreCliente").asString,
                            apellidoPaterno = json.asJsonObject.get("apellidoPaterno").asString,
                            apellidoMaterno = json.asJsonObject.get("apellidoMaterno").asString,
                            telefono = json.asJsonObject.get("telefono").asString,
                            correoElectronico = json.asJsonObject.get("correoElectronico").asString
                        )
                    }
                    actualizarUIconCliente()
                } else {
                    e.printStackTrace()
                    Toast.makeText(this, "Error al cargar los clientes", Toast.LENGTH_SHORT).show()
                }
            }
    }

    private fun actualizarUIconCliente() {
        binding.apply {
            tvNumeroGuia.text = envio.numeroGuia
            tvOrigen.text =
                envio.calle + " " + envio.numero + ", " + envio.colonia + ", " + envio.ciudad + ", " + envio.estado
            tvDestino.text = envio.destino
            tvEstado.text = envio.estatus
            clientes.forEach { cliente ->
                if (cliente.idCliente == envio.idCliente) {
                    tvContacto.text =
                        "${cliente.nombreCliente} ${cliente.apellidoPaterno} ${cliente.apellidoMaterno}\n${cliente.telefono}\n${cliente.correoElectronico}"
                }
            }
            if (envio.estatus == "ENTREGADO") {
                btnCambiar.setOnClickListener {
                    Toast.makeText(
                        this@DetallesEnvio,
                        "El envio ya fue entregado",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                btnCambiar.setOnClickListener { mostrarDialogoCambiarEstado(envio) }
            }
        }
    }

    fun cargarPaquetes(idEnvio: String) {
        binding.progressBar.visibility = View.VISIBLE
        Ion.with(this)
            .load("GET", "${Constantes.URL_WS}/paquete/id-envio/${idEnvio}")
            .asJsonArray()
            .setCallback { e, result ->
                binding.progressBar.visibility = View.GONE
                if (e == null) {
                    val paquetes = result.map { json ->
                        Paquete(
                            idPaquete = json.asJsonObject.get("idPaquete").asString,
                            idEnvio = json.asJsonObject.get("idEnvio").asString,
                            peso = json.asJsonObject.get("peso").asFloat,
                            descripcion = json.asJsonObject.get("descripcion").asString,
                            profundidad = json.asJsonObject.get("profundidad").asFloat,
                            alto = json.asJsonObject.get("alto").asFloat,
                            ancho = json.asJsonObject.get("ancho").asFloat,
                            numeroGuia = json.asJsonObject.get("numeroGuia").asString
                        )
                    }
                    mostrarPaquetes(paquetes)
                } else {
                    Log.e("DetallesEnvio", "Error al cargar los paquetes", e)
                    e.printStackTrace()
                    Toast.makeText(this, "Error al cargar los paquetes", Toast.LENGTH_SHORT).show()
                }
            }
    }


    fun mostrarPaquetes(paquetes: List<Paquete>) {
        val sb = StringBuilder()
        for (paquete in paquetes) {
            sb.append("\u2022 ${paquete.idPaquete} - ${paquete.descripcion} kg\n")
        }
        binding.tvPaquetes.text = sb.toString()
    }

    private fun mostrarDialogoCambiarEstado(envio: Envio) {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(true)
        dialog.setContentView(R.layout.popup)
        dialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val tfEstado = dialog.findViewById<TextView>(R.id.tfEstadoActual)
        val spinnerEstado = dialog.findViewById<Spinner>(R.id.spinnerEstadoNuevo)
        val btnActualizar = dialog.findViewById<Button>(R.id.btnActualizar)

        tfEstado.text = envio.estatus

        val estados = listOf("DETENIDO", "PENDIENTE", "ENTREGADO", "CANCELADO", "EN TRÁNSITO")
        val adapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            estados
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerEstado.adapter = adapter

        btnActualizar.setOnClickListener {
            val nuevoEstado = spinnerEstado.selectedItem.toString()

            envio.estatus = nuevoEstado
            Ion.with(this)
                .load("PUT", "${Constantes.URL_WS}/envio/estatus")
                .setJsonPojoBody(envio)
                .asJsonObject()
                .setCallback { e, result ->
                    if (e == null) {
                        val msj = Gson().fromJson(result, Mensaje::class.java)
                        if (!msj.error) {
                            Toast.makeText(
                                this,
                                "Estatus actualizado exitosamente",
                                Toast.LENGTH_SHORT
                            ).show()
                            val intent = Intent(this@DetallesEnvio, Principal::class.java)
                            Log.d("DetallesEnvio", "Lanzando la actividad Principal")
                            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
                            startActivity(intent)
                            finish()

                        } else {
                            Toast.makeText(
                                this,
                                "Error al actualizar el estatus",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    } else {
                        e.printStackTrace()
                        Toast.makeText(this, "Error al llamar al servicio.", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            dialog.dismiss()
        }

        dialog.show()
    }
}