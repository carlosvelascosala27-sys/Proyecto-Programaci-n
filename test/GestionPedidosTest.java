import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para comprobar la gestión de clientes y productos.
 */
class GestionPedidosTest {

    //Comprobamos que se puede añadir un cliente
    @Test
    void testAgregarCliente() {

        GestionPedidos g = new GestionPedidos();

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");

        g.agregarCliente(c);

        assertNotNull(g.buscarCliente("600000000"));
    }

    //Comprobamos que buscar un cliente inexistente devuelve null
    @Test
    void testBuscarClienteIncorrecto() {

        GestionPedidos g = new GestionPedidos();

        assertNull(g.buscarCliente("123456"));
    }

    //Comprobamos que se puede obtener un producto
    @Test
    void testObtenerProducto() {

        GestionPedidos g = new GestionPedidos();

        Producto p = new Producto("Agua",0.35);

        g.agregarProducto(p);

        assertNotNull(g.obtenerProducto(1));
    }

}