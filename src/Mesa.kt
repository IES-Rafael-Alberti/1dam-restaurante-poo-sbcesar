class Mesa(val numero: Int, capacidad: Int, var estado: EstadoMesa = EstadoMesa.LIBRE, val pedidos: MutableList<Pedido> = mutableListOf()) {
    private var capacidad: Int = capacidad
        set(value) {
            field = value
            requireCapacidad()
        }

    init {
        requireCapacidad()
    }

    private fun requireCapacidad() {
        require(capacidad in 0..6) { "La capacidad de comensañes en una mesa oscila entre 0 y 6" }
        require(capacidad > 0) { "Debe ser un numero positivo" }
    }

    fun ocuparMesa() {
        if (estado == EstadoMesa.LIBRE) {
            estado = EstadoMesa.OCUPADA
        }
    }

    fun ocuparReserva() {
        if (estado == EstadoMesa.RESERVADA) {
            estado = EstadoMesa.OCUPADA
        }
    }

    fun liberarMesa() {
        estado = EstadoMesa.LIBRE
    }

    fun agregarPedido(pedido: Pedido) {
        pedidos.add(pedido)
    }

    override fun toString(): String {
        return "$numero, estado=$estado, pedidos=$pedidos, capacidad=$capacidad)"
    }


}

enum class EstadoMesa {
    LIBRE, RESERVADA, OCUPADA
}