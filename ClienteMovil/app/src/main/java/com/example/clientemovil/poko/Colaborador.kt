package com.example.clientemovil.poko

data class Colaborador(
    val idColaborador: Int = 0,
    val nombreColaborador: String = "",
    val apellidoMaterno: String = "",
    val apellidoPaterno: String = "",
    val curp: String = "",
    val correoElectronico: String = "",
    val noPersonal: String = "",
    val contrasenia: String = "",
    val licencia: String = "",
    var fotografia: ByteArray? = null,
    val idRol: Int = 0,
    val tipoRol: String = "",
    val idUnidad: Int = 0,
    val vin: String = ""
)

