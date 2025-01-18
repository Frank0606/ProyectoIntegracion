package com.example.clientemovil.poko

data class Paquete(
    val idPaquete: String = "",
    val descripcion: String = "",
    val peso: Float = 0f,
    val profundidad: Float = 0f,
    val alto: Float = 0f,
    val ancho: Float = 0f,
    val idEnvio: String = "",
    val numeroGuia: String = ""
)
