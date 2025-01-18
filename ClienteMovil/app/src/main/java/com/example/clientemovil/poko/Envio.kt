package com.example.clientemovil.poko

data class Envio(
    val idEnvio: String = "",
    val destino: String = "",
    val calle: String = "",
    val numeroGuia: String = "",
    val costoEnvio: Double = 0.0,
    val numero: String = "",
    val colonia: String = "",
    val cp: String = "",
    val ciudad: String = "",
    val estado: String = "",
    var estatus: String = "",
    var historialEstados: String = "",
    val idCliente: Int = 0,
    val nombreCliente: String = "",
    val idColaborador: Int = 0,
    val nombreColaborador: String = ""
)

