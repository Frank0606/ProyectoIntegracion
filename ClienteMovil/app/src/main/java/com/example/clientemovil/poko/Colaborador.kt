package com.example.clientemovil.poko

data class Colaborador(
    val idColaborador : Int,
    val nombreColaborador : String,
    val apellidoMaterno : String,
    val apellidoPaterno : String,
    val curp : String,
    val correoElectronico : String,
    val contrasenia : String,
    val noPersonal : String,
    val idRol : Int,
    val tipoRol : String,
    val idUnidad: Int,
    val vin : String,
    val fotografia : String ?
)
