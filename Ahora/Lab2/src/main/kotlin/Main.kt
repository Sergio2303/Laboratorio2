fun main() {
    val perfilesUsuarios: MutableList<PerfilUsuario> = mutableListOf()


    fun buscarPerfil(): PerfilUsuario? {
        print("Ingrese el ID del perfil,los nombres o apellidos (favor separalos por espacios) del perfil que desea buscar: ")
        val input = readLine() ?: return null
        val palabrasClave = input.split(" ")

        // Para buscar por ID
        val perfilPorID = perfilesUsuarios.find { it.ID == palabrasClave[0].toIntOrNull() }
        if (perfilPorID != null) return perfilPorID

        // si no quiere usar id y desea usar nombre y apellido
        val perfilesPorNombreApellido = perfilesUsuarios.filter {
            it.Nombre.contains(palabrasClave[0], ignoreCase = true) ||
                    it.Apellidos.contains(palabrasClave[0], ignoreCase = true)
        }

        // Si solo hay un perfil que coincide con los nombres o apellidos, se retorna
        if (perfilesPorNombreApellido.size == 1) return perfilesPorNombreApellido[0]

        // Si hay más de un perfil que coincide con los nombres o apellidos, mostramos opciones y pedimos al usuario que escoga el que desea
        if (perfilesPorNombreApellido.isNotEmpty()) {
            println("Se encontraron varios perfiles que coinciden con la búsqueda:")
            perfilesPorNombreApellido.forEachIndexed { index, perfil ->
                println("${index + 1}. ${perfil.Nombre} ${perfil.Apellidos}")
            }
            print("Seleccione un perfil por su número de opción: ")
            val opcion = readLine()?.toIntOrNull() ?: return null
            if (opcion in 1..perfilesPorNombreApellido.size) {
                return perfilesPorNombreApellido[opcion - 1]
            }
        }

        return null
    }

    // Función para eliminar un perfil por ID
    fun eliminarPerfil(): Boolean {
        print("Ingrese el ID del perfil que desea eliminar: ")
        val id = readLine()?.toIntOrNull() ?: return false
        val perfil = perfilesUsuarios.find { it.ID == id } ?: return false
        return perfilesUsuarios.remove(perfil)
    }

    // agregar un hobby a un perfil
    fun agregarHobbyAPerfil() {
        val perfil = buscarPerfil()
        if (perfil == null) {
            println("Perfil no encontrado.")
            return
        }

        println("Ingrese los datos del hobby:")
        print("Título: ")
        val título = readLine() ?: return
        print("Descripción: ")
        val descripción = readLine() ?: return
        print("URL de la foto (opcional): ")
        val urlPhoto = readLine()

        val nuevoHobby = Hobby(título, descripción, urlPhoto)
        perfil.agregarHobby(nuevoHobby)
        println("Hobby agregado exitosamente.")
    }
    //Crear Perfiles
    fun crearPerfil(): PerfilUsuario {
        println("Ingrese los datos del nuevo perfil:")
        print("ID: ")
        val id = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("ID inválido.")
        print("Nombres: ")
        val nombres = readLine() ?: throw IllegalArgumentException("Nombres no válidos.")
        print("Apellidos: ")
        val apellidos = readLine() ?: throw IllegalArgumentException("Apellidos no válidos.")
        print("URL de la foto (opcional): ")
        val urlFoto = readLine()
        print("Edad: ")
        val edad = readLine()?.toIntOrNull() ?: throw IllegalArgumentException("Edad inválida.")
        print("Correo: ")
        val correo = readLine() ?: throw IllegalArgumentException("Correo no válido.")
        print("Biografía (opcional): ")
        val biografía = readLine()

        println("Seleccione el estado del perfil (Activo/Pendiente/Inactivo): ")
        val estado = readLine() ?: throw IllegalArgumentException("Estado no válido.")

        return PerfilUsuario(
            ID = id,
            Nombre = nombres,
            Apellidos = apellidos,
            UrlPhoto = urlFoto,
            Edad = edad,
            Correo = correo,
            Biografía = biografía,
            estado = estado
        )
    }



    // Perfiles precargados
    perfilesUsuarios.add(
        PerfilUsuario(
            ID = 1,
            Nombre = "Juan",
            Apellidos = "Pérez",
            UrlPhoto = "https://ejmplo.com/perfil1.jpg",
            Edad = 30,
            Correo = "juan@gmail.com",
            Biografía = "Hola, Soy juan, me gusta comer y tambien la bicicleta",
            estado = "Activo"
        )
    )

    perfilesUsuarios.add(
        PerfilUsuario(
            ID = 2,
            Nombre = "María",
            Apellidos = "González",
            UrlPhoto = "https://ejemplo.com/perfil2.jpg",
            Edad = 25,
            Correo = "maria@gmail.com",
            Biografía = "Hola, Soy María, me gusta mucho el chocolate y los delfines",
            estado = "Pendiente"
        )
    )

    // Menú principal
    menu@ while (true) {
        println("\n--- Menú ---")
        println("1. Crear perfil")
        println("2. Buscar perfil de usuario(s)")
        println("3. Eliminar perfil")
        println("4. Agregar Hobby")
        println("5. Salir")
        print("Ingrese el número de la opción deseada: ")
        val opcion = readLine()?.toIntOrNull() ?: continue

        when (opcion) {
            1 -> {
                println("Creando perfil nuevo...")
                val nuevoPerfil = crearPerfil()
                perfilesUsuarios.add(nuevoPerfil)
                println("Perfil creado exitosamente.")
            }

            2 -> {
                val perfilEncontrado = buscarPerfil()
                if (perfilEncontrado != null) {
                    println("Perfil encontrado:")
                    println("ID: ${perfilEncontrado.ID}")
                    println("Nombres: ${perfilEncontrado.Nombre}")
                    println("Apellidos: ${perfilEncontrado.Apellidos}")
                    println("URL de la foto: ${perfilEncontrado.UrlPhoto ?: "N/A"}")
                    println("Edad: ${perfilEncontrado.Edad}")
                    println("Correo: ${perfilEncontrado.Correo}")
                    println("Biografía: ${perfilEncontrado.Biografía ?: "N/A"}")

                    if (perfilEncontrado.Hobbies.isNotEmpty()) {
                        println("Hobbies:")
                        perfilEncontrado.Hobbies.forEach { hobby ->
                            println("- Título: ${hobby.título}")
                            println("  Descripción: ${hobby.descripción}")
                            println("  URL de la foto: ${hobby.urlPhoto ?: "N/A"}")
                        }
                    } else {
                        println("No tiene hobbies registrados.")
                    }
                } else {
                    println("Perfil no encontrado.")
                }
            }

            3 -> {
                val eliminado = eliminarPerfil()
                if (eliminado) {
                    println("Perfil eliminado exitosamente.")
                } else {
                    println("Perfil no encontrado")
                }
            }

            4 -> {
                agregarHobbyAPerfil()
            }

            5 -> {
                println("Saliendo del programa.")
                break@menu
            }

            else ->
                println("Opción no válida. Por favor vuelve a intentar nuevamente.")}
    }
}





