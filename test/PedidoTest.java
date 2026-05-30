import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para comprobar la clase Pedido.
 *
 * @author Carlos Velasco Sala
 * @since 1.0
 */
class PedidoTest {

    //Comprobamos que se puede añadir un producto al pedido
    @Test
    void testAgregarProducto() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        Producto prod = new Producto("Agua", 0.35);
        assertTrue(p.agregarProducto(prod));
    }

    //Comprobamos que el importe total se actualiza
    @Test
    void testImporteTotal() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        Producto prod = new Producto("Agua", 0.35);
        p.agregarProducto(prod);
        assertTrue(p.getImporteTotal() == 0.35);
    }

    //No se puede añadir producto si el pedido está pagado
    @Test
    void testAgregarProductoPagado() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        Producto prod = new Producto("Agua", 0.35);
        p.agregarProducto(prod);
        p.pagar(2, "4111111111111111");
        assertFalse(p.agregarProducto(prod));
    }

    //Comprobamos que eliminar un producto funciona
    @Test
    void testEliminarProducto() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        Producto prod = new Producto("Agua", 0.35);
        p.agregarProducto(prod);
        assertTrue(p.eliminarProducto(0));
    }

    //Si eliminamos una posición incorrecta devuelve false
    @Test
    void testEliminarProductoIncorrecto() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        assertFalse(p.eliminarProducto(5));
    }

    //No se puede eliminar si el pedido está pagado
    @Test
    void testEliminarProductoPagado() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        Producto prod = new Producto("Agua", 0.35);
        p.agregarProducto(prod);
        p.pagar(2, "4111111111111111");
        assertFalse(p.eliminarProducto(0));
    }

    //Comprobamos que no se puede pagar si no hay productos
    @Test
    void testPagarSinProductos() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        assertFalse(p.pagar(1, "10"));
    }

    //Comprobamos que se puede pagar correctamente en efectivo
    @Test
    void testPagarEfectivoCorrecto() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        p.agregarProducto(new Producto("Agua", 0.35));
        assertTrue(p.pagar(1, "1"));
    }

    //Comprobamos que el pago con tarjeta funciona
    @Test
    void testPagarTarjetaCorrecta() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        p.agregarProducto(new Producto("Pizza", 7.55));
        assertTrue(p.pagar(2, "4123456789012345"));
    }

    //Comprobamos que una tarjeta incorrecta devuelve false
    @Test
    void testPagarTarjetaIncorrecta() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        p.agregarProducto(new Producto("Pizza", 7.55));
        assertFalse(p.pagar(2, "123"));
    }

    //Comprobamos que no se puede pagar dos veces el mismo pedido
    @Test
    void testPagarDosVeces() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        p.agregarProducto(new Producto("Agua", 0.35));
        p.pagar(1, "1");
        assertFalse(p.pagar(1, "1"));
    }

    //El estado cambia a PAGADO tras pagar
    @Test
    void testPagarCambiaEstado() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        p.agregarProducto(new Producto("Agua", 0.35));
        p.pagar(2, "4111111111111111");
        assertEquals(EstadoPedido.PAGADO, p.getEstado());
    }

    //Metodo de pago invalido devuelve false
    @Test
    void testPagarMetodoInvalido() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = new Pedido(c);
        p.agregarProducto(new Producto("Agua", 0.35));
        assertFalse(p.pagar(9, "dato"));
    }
}