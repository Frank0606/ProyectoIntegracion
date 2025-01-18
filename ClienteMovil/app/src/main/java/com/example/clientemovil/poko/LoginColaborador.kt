package com.example.clientemovil.poko

data class LoginColaborador(
    val error: Boolean = false,
    val mensaje: String = "",
    val colaborador: Colaborador = Colaborador()
)

