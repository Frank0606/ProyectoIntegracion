package com.example.clientemovil

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.clientemovil.databinding.ActivityPrincipalBinding
import com.example.clientemovil.poko.Colaborador
import com.example.clientemovil.poko.Envio
import com.example.clientemovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion

class Principal : AppCompatActivity() {

    private lateinit var binding: ActivityPrincipalBinding
    private var TAG = "PrincipalActivity"
    private var colaborador: Colaborador? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPrincipalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ibPerfil2.setOnClickListener {
            val intent = Intent(this, VerPerfil::class.java)
            intent.putExtra("colaborador", Gson().toJson(colaborador))
            startActivity(intent)
        }

        val colaboradorJson = intent.getStringExtra("colaborador")
        val gson = Gson()
        colaborador = gson.fromJson(colaboradorJson, Colaborador::class.java)

        cargarEnvios()
    }


    override fun onResume() {
        super.onResume()
        cargarEnvios()
    }

    fun cargarEnvios() {
        binding.progressBar.visibility = View.VISIBLE
        Ion.with(this)
            .load("${Constantes.URL_WS}/envio/todos")
            .asJsonArray()
            .setCallback { e, result ->
                binding.progressBar.visibility = View.GONE
                if (e == null) {
                    val envios = result.map { json ->
                        Log.d(TAG, "JSON recibido: $json")
                        Envio(
                            idEnvio = json.asJsonObject.get("idEnvio").asString,
                            destino = json.asJsonObject.get("destino").asString,
                            calle = json.asJsonObject.get("calle").asString,
                            numeroGuia = json.asJsonObject.get("numeroGuia").asString,
                            costoEnvio = json.asJsonObject.get("costoEnvio").asDouble,
                            numero = json.asJsonObject.get("numero").asString,
                            colonia = json.asJsonObject.get("colonia").asString,
                            cp = json.asJsonObject.get("cp").asString,
                            ciudad = json.asJsonObject.get("ciudad").asString,
                            estado = json.asJsonObject.get("estado").asString,
                            estatus = json.asJsonObject.get("estatus").asString,
                            historialEstados = json.asJsonObject.get("historialEstados").asString,
                            idCliente = json.asJsonObject.get("idCliente").asInt,
                            nombreCliente = json.asJsonObject.get("nombreCliente").asString,
                            idColaborador = json.asJsonObject.get("idColaborador").asInt,
                            nombreColaborador = json.asJsonObject.get("nombreColaborador").asString
                        )
                    }.filter { it.idColaborador == colaborador?.idColaborador }

                    val adapter = colaborador?.let {
                        AdaptadorPersonalizado(
                            this,
                            envios,
                            it.noPersonal
                        )
                    }
                    binding.lv.adapter = adapter
                } else {
                    e.printStackTrace()
                }
            }
    }
}
