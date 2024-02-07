/**
 * Representa la informacion de un Plato
 *
 * @param nombre String - El nombre del plato. No puede ser vacío.
 * @param precio Double - El precio del plato. Debe ser mayor que 0.
 * @param tiempoPreparacion Int - Tiempo estimado para preparar el plato (en minutos).
 * @param ingredientes List - Lista de ingredientes necesarios para el plato.
 */
class Plato(nombre: String,precio: Double,tiempoPreparacion:Int,ingredientes: MutableList<String>) {

    var nombre: String = nombre
        set(value) {
            field = value
            requireNombre()
        }

    var precio: Double = precio
        set(value) {
            field = value
            requirePrecio()
        }

    var tiempoPreparacion: Int = tiempoPreparacion
        set(value) {
            field = value
            requireTiempoPreparacion()
        }

    var ingredientes: MutableList<String> = ingredientes
        set(value) {
            field = value
            requireIngrediente()
        }

    init {
        requireNombre()
        requirePrecio()
        requireTiempoPreparacion()
        requireIngrediente()
    }

    fun agregarIngrediente(ingrediente: String) {
        ingredientes.add(ingrediente)
    }

    private fun requireNombre() {
        require(nombre.isNotBlank()) { "El nombre no puede estar vacio." }
    }

    private fun requirePrecio() {
        require(precio > 0) { "El precio debe ser positivo" }
    }

    private fun requireTiempoPreparacion() {
        require(tiempoPreparacion >= 1) { "El tiempo de preparación no podrá ser igual o inferior a 1." }
    }

    private fun requireIngrediente() {
        require(ingredientes.isNotEmpty()) { "Un ingrediente no puede ser vacío." }
    }

    override fun toString(): String {
        return "$nombre ($tiempoPreparacion)min. -> $precio€ (${ingredientes.joinToString(", ")})"
    }


}