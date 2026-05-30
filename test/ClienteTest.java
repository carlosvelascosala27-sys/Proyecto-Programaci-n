import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Cliente.
 *
 * @author Carlos Velasco Sala
 * @since 1.0
 */
class ClienteTest {

    //Comprobamos que el cliente se crea correctamente
    @Test
    void testCrearCliente() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        assertEquals("600000000", c.getTelefono());
    }

    //Comprobamos que se puede crear un pedido
    @Test
    void testRealizarPedido() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = c.realizarPedido();
        assertNotNull(p);
    }

    //El nombre se guarda en minusculas
    @Test
    void testConstructorNombreMinusculas() {
        Cliente c = new Cliente("ANTONIO", "Perez", "600000000", "Calle A");
        assertEquals("antonio", c.getNombre());
    }

    //Los apellidos se guardan en mayusculas
    @Test
    void testConstructorApellidosMayusculas() {
        Cliente c = new Cliente("Juan", "garcia", "600000000", "Calle A");
        assertEquals("GARCIA", c.getApellidos());
    }

    //El historial inicial está vacío
    @Test
    void testConstructorHistorialVacio() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        assertEquals(0, c.getHistorial().size());
    }

    //Pedido nulo devuelve false
    @Test
    void testAgregarPedidoNulo() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        assertFalse(c.agregarPedido(null));
    }

    //Pedido sin pagar no se añade
    @Test
    void testAgregarPedidoNoPagado() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = c.realizarPedido();
        assertFalse(c.agregarPedido(p));
    }

    //Pedido pagado se añade al historial
    @Test
    void testAgregarPedidoPagado() {
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Pedido p = c.realizarPedido();
        p.agregarProducto(new Producto("Agua", 0.35));
        p.pagar(2, "4111111111111111");
        assertTrue(c.agregarPedido(p));
    }

    //Pedido de otro cliente no se añade
    @Test
    void testAgregarPedidoOtroCliente() {
        Cliente c1 = new Cliente("Juan", "Perez", "600000000", "Calle A");
        Cliente c2 = new Cliente("Maria", "Garcia", "600000001", "Calle B");
        Pedido p = c2.realizarPedido();
        p.agregarProducto(new Producto("Agua", 0.35));
        p.pagar(2, "4111111111111111");
        assertFalse(c1.agregarPedido(p));
    }
}