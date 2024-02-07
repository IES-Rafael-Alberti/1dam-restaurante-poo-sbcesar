class Pedido {

    companion object {
        var contPedidos: Int = 0
    }

    val numero: Int = ++contPedidos

    //Al declarar esta variable como una propiedad de la clase en vez de pasarla como parametro al constructor, permite acceder a ella desde otros metodos sin necesidad de pasarla explícitamente como argumento
    val platos: MutableList<Plato> = mutableListOf()

    var estado: EstadoPedido = EstadoPedido.PENDIENTE

    fun agregarPlato(plato: Plato) {
        platos.add(plato)
    }

    fun eliminarPlato(nombrePlato: String) {
        val comparador = platos.find { it.nombre == nombrePlato }
        platos.remove(comparador)
    }

    fun calcularPrecio(): Double {
        return platos.sumOf { it.precio }
    }

    fun calcularTiempo(): Int {
        return platos.sumOf { it.tiempoPreparacion }
    }

    override fun toString(): String {
        val platos = platos.joinToString("\n") { it.toString() }
        return "Nº Mesa: $numero || $platos || $estado"
    }
}

enum class EstadoPedido {
    PENDIENTE, PREPARACION, LISTO, SERVIDO
}