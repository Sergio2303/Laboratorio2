import java.lang.IllegalArgumentException

class PerfilUsuario (

    val ID: Int,
    val Nombre: String,
    val Apellidos: String,
    val UrlPhoto: String?,
    val Edad: Int,
    val Correo: String,
    val Biografía: String?,
    estado: String,



    ){
        var Estado: String = estado
            set (value){
                if(value in listOf("Activo", "Pendiente", "Inactivo")){
                    field = value
                } else{
                    throw IllegalArgumentException("Por favor coloque un estado correcto, Activo, Pendiente o Inactivo")
                }
            }
    val Hobbies: MutableCollection<Hobby> = mutableListOf()
    fun agregarHobby(hobby: Hobby){
        Hobbies.add(hobby)
    }

}
