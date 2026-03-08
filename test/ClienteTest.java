import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la clase Cliente.
 */
class ClienteTest {

    //Comprobamos que el cliente se crea correctamente
    @Test
    void testCrearCliente() {

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");

        assertEquals("600000000", c.getTelefono());
    }

    //Comprobamos que se puede crear un pedido
    @Test
    void testRealizarPedido() {

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");

        Pedido p = c.realizarPedido();

        assertNotNull(p);
    }

}