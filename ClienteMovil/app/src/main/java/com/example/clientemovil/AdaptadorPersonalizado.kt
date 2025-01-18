package com.example.clientemovil

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import com.example.clientemovil.poko.Colaborador
import com.example.clientemovil.poko.Envio
import com.example.clientemovil.poko.Mensaje
import com.example.clientemovil.util.Constantes
import com.google.gson.Gson
import com.koushikdutta.ion.Ion
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class AdaptadorPersonalizado(
    private val context: Context,
    private val items: List<Envio>,
    private val colaborador: String
) : BaseAdapter() {
    private val checkedStates = BooleanArray(items.size)

    override fun getCount(): Int = items.size

    override fun getItem(position: Int): Any = items[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View
        val holder: ViewHolder

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.listviewbox, parent, false)
            holder = ViewHolder(
                view.findViewById(R.id.tvItemText),
                view.findViewById(R.id.tvSubItemText),
                view.findViewById(R.id.cbItemCheck)
            )
            view.tag = holder
        } else {
            view = convertView
            holder = view.tag as ViewHolder
        }

        val envio = items[position]
        holder.tvItemText.text = envio.destino
        holder.tvSubItemText.text = envio.numeroGuia + " - " + envio.estatus

        if (envio.estatus == "ENTREGADO" || envio.estatus == "CANCELADO") {
            holder.checkBox.isChecked = true
            holder.checkBox.isEnabled = false
        } else {
            holder.checkBox.isChecked = checkedStates[position]
            holder.checkBox.isEnabled = true
        }

        holder.tvItemText.setOnClickListener {
            val jsonEnvio = Gson().toJson(envio)
            val intent = Intent(context, DetallesEnvio::class.java).apply {
                putExtra("envio", jsonEnvio)
                putExtra("colaborador", colaborador)
            }
            context.startActivity(intent)
        }

        holder.checkBox.setOnCheckedChangeListener { _, isChecked ->
            checkedStates[position] = isChecked
            Log.d("AdaptadorPersonalizado", "${colaborador}")
            val colaboradorObj = Gson().fromJson(colaborador, Colaborador::class.java)
            if (isChecked) {
                envio.estatus = "ENTREGADO"
                actualizarEstatusEnvio(envio, colaboradorObj.noPersonal)
            }
        }

        return view
    }

    private fun actualizarEstatusEnvio(envio: Envio, noPersonal: String) {
        val horaActual = obtenerFechaHoraActual()
        val historialEstatusTemp =
            envio.historialEstados + envio.estatus + "_" + horaActual + "_" + noPersonal + ":"
        envio.historialEstados = historialEstatusTemp

        Ion.with(context)
            .load("PUT", "${Constantes.URL_WS}/envio/estatus")
            .setJsonPojoBody(envio)
            .asJsonObject()
            .setCallback { e, result ->
                if (e == null) {
                    val msj = Gson().fromJson(result, Mensaje::class.java)
                    if (!msj.error) {
                        Toast.makeText(
                            context,
                            "Estatus actualizado exitosamente",
                            Toast.LENGTH_SHORT
                        ).show()
                        recargarActivityPrincipal()
                    } else {
                        Toast.makeText(
                            context,
                            "Error al actualizar el estatus",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                } else {
                    e.printStackTrace()
                    Toast.makeText(context, "Error al llamar al servicio.", Toast.LENGTH_SHORT)
                        .show()
                }
            }
    }

    fun obtenerFechaHoraActual(): String {
        val fechaHoraActual = LocalDateTime.now()
        val formato = DateTimeFormatter.ofPattern("dd/MM/yyyy-HH:mm")
        return fechaHoraActual.format(
            formato
        )
    }

    private fun recargarActivityPrincipal() {
        val intent = Intent(context, Principal::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        if (context is Activity) {
            (context as Activity).finish()
        }
    }

    private data class ViewHolder(
        val tvItemText: TextView,
        val tvSubItemText: TextView,
        val checkBox: CheckBox
    )
}