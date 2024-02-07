
class SistemaGestionRestaurante(private val mesas: List<Mesa>) {

    fun realizarPedido(numeroMesa: Int, pedido: Pedido) {
        val mesa = mesas.find { it.numero == numeroMesa }
        mesa?.let {
            if (it.estado == EstadoMesa.OCUPADA) {
                it.agregarPedido(pedido)
            } else {
                println("La mesa $numeroMesa no está ocupada, no se puede hacer el pedido")
            }
        } ?: println("La mesa $numeroMesa no existe")
    }

    fun cerrarPedido(numeroMesa: Int, numeroPedido: Int? = null) {
        val mesa = mesas.find { it.numero == numeroMesa }
        mesa?.let {
            val pedido = numeroPedido?.let { numeroPedido -> it.pedidos.find { it.numero == numeroPedido } } ?: it.pedidos.lastOrNull()
            if (pedido != null) {
                pedido.estado = EstadoPedido.SERVIDO
                println("Pedido $numeroPedido cerrado correctamente")
                if (pedido == it.pedidos.lastOrNull()) cerrarMesa(numeroMesa)
            } else {
                println("No se encontró el pedido")
            }
        } ?: println("La mesa $numeroMesa no existe")
    }

    fun cerrarMesa(numeroMesa: Int) {
        val mesa = mesas.find { it.numero == numeroMesa }
        mesa?.let {
            if (it.pedidos.all { pedido: Pedido -> pedido.estado == EstadoPedido.SERVIDO }) {
                it.liberarMesa()
                println("Mesa $numeroMesa cerrada")
            } else {
                println("No se pueden cerrar todos los pedidos de la mesa $numeroMesa")
            }
        } ?: println("La mesa $numeroMesa no existe")
    }

    fun buscarPlatos(): List<String>? {
        val platos = mesas.flatMap { it.pedidos }.flatMap { it.platos }.map { it.nombre }
        return platos.ifEmpty { null }
    }

    fun contarPlato(nombre: String): Int? {
        val count = mesas.flatMap { it.pedidos }
            .flatMap { it.platos }
            .count { it.nombre == nombre }
        return if (count > 0) count else null
    }

    fun buscarPlatoMasPedido(): List<String>? {
        val platoCounts = mesas.flatMap { it.pedidos }
            .flatMap { it.platos }
            .groupingBy { it.nombre }
            .eachCount()

        val maxCount = platoCounts.maxByOrNull { it.value }?.value
        return maxCount?.let { max -> platoCounts.filter { it.value == max }.keys.toList() }
    }
}