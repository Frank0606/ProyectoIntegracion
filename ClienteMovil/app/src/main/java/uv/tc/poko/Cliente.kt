package uv.tc.poko

data class Cliente(
    val idCliente : Int,
    var nombreC : String,
    var apellidoPaterno : String,
    var apellidoMaterno : String,
    var fechaNacimiento : String,
    var telefono : String,
    var peso : Float,
    var estatura : Int,
    val correo : String,
    val password : String,
    val idColaborador : Int,
    val nombreCo : String,
    val fechaInscripcion : String,
    val foto : String,
    val fotoBase64 : String ?
)