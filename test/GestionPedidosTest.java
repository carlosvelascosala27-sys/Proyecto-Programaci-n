import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para comprobar la gestión de clientes y productos.
 *
 * @author Carlos Velasco Sala
 * @since 1.0
 */
class GestionPedidosTest {

    //Comprobamos que se puede añadir un cliente
    @Test
    void testAgregarCliente() {
        GestionPedidos g = new GestionPedidos();
        Cliente c = new Cliente("Juan", "Perez", "600000000", "Calle A");
        g.agregarCliente(c);
        assertNotNull(g.buscarCliente("600000000"));
    }

    //Varios clientes se añaden correctamente
    @Test
    void testAgregarVariosClientes() {
        GestionPedidos g = new GestionPedidos();
        g.agregarCliente(new Cliente("Juan", "Perez", "600000000", "Calle A"));
        g.agregarCliente(new Cliente("Maria", "Garcia", "600000001", "Calle B"));
        g.agregarCliente(new Cliente("Pedro", "Lopez", "600000002", "Calle C"));
        assertNotNull(g.buscarCliente("600000000"));
        assertNotNull(g.buscarCliente("600000001"));
        assertNotNull(g.buscarCliente("600000002"));
    }

    //Comprobamos que buscar un cliente inexistente devuelve null
    @Test
    void testBuscarClienteIncorrecto() {
        GestionPedidos g = new GestionPedidos();
        assertNull(g.buscarCliente("123456"));
    }

    //Telefono vacio devuelve null
    @Test
    void testBuscarClienteTelefonoVacio() {
        GestionPedidos g = new GestionPedidos();
        assertNull(g.buscarCliente(""));
    }

    //Comprobamos que se puede obtener un producto
    @Test
    void testObtenerProducto() {
        GestionPedidos g = new GestionPedidos();
        g.agregarProducto(new Producto("Agua", 0.35));
        assertNotNull(g.obtenerProducto(1));
    }

    //Posicion 0 devuelve null
    @Test
    void testObtenerProductoPosicionCero() {
        GestionPedidos g = new GestionPedidos();
        g.agregarProducto(new Producto("Agua", 0.35));
        assertNull(g.obtenerProducto(0));
    }

    //Posicion negativa devuelve null
    @Test
    void testObtenerProductoPosicionNegativa() {
        GestionPedidos g = new GestionPedidos();
        g.agregarProducto(new Producto("Agua", 0.35));
        assertNull(g.obtenerProducto(-1));
    }

    //Posicion mayor que la lista devuelve null
    @Test
    void testObtenerProductoPosicionGrande() {
        GestionPedidos g = new GestionPedidos();
        g.agregarProducto(new Producto("Agua", 0.35));
        assertNull(g.obtenerProducto(99));
    }

    //Encuentra producto por nombre
    @Test
    void testBuscarProductoExistente() {
        GestionPedidos g = new GestionPedidos();
        g.agregarProducto(new Producto("Agua", 0.35));
        assertNotNull(g.buscarProducto("AGUA"));
    }

    //Devuelve null si el producto no existe
    @Test
    void testBuscarProductoNoExiste() {
        GestionPedidos g = new GestionPedidos();
        assertNull(g.buscarProducto("PATATAS"));
    }

    //Nombre en minusculas no encuentra el producto
    @Test
    void testBuscarProductoMinusculas() {
        GestionPedidos g = new GestionPedidos();
        g.agregarProducto(new Producto("Agua", 0.35));
        assertNull(g.buscarProducto("agua"));
    }

    //Guardar y recuperar devuelve los mismos datos
    @Test
    void testGuardarYRecuperar() {
        GestionPedidos g = new GestionPedidos();
        g.agregarProducto(new Producto("Agua", 0.35));
        g.agregarCliente(new Cliente("Juan", "Perez", "600000000", "Calle A"));
        g.guardar("testFichero.dat");
        GestionPedidos g2 = new GestionPedidos();
        g2.recuperar("testFichero.dat");
        assertNotNull(g2.buscarCliente("600000000"));
        assertNotNull(g2.buscarProducto("AGUA"));
        new File("testFichero.dat").delete();
    }

    //Fichero inexistente no lanza excepcion
    @Test
    void testRecuperarFicheroNoExiste() {
        GestionPedidos g = new GestionPedidos();
        g.recuperar("noexiste.dat");
    }

    //No duplica clientes al recuperar y agregar
    @Test
    void testRecuperarAgregarSinDuplicar() {
        GestionPedidos g = new GestionPedidos();
        g.agregarCliente(new Cliente("Juan", "Perez", "600000000", "Calle A"));
        g.agregarProducto(new Producto("Agua", 0.35));
        g.guardar("testFichero2.dat");
        g.recuperarAgregar("testFichero2.dat");
        assertNull(g.buscarCliente("999999999"));
        new File("testFichero2.dat").delete();
    }

    //Anade clientes nuevos sin duplicar
    @Test
    void testRecuperarAgregarNuevoCliente() {
        GestionPedidos g = new GestionPedidos();
        g.agregarProducto(new Producto("Agua", 0.35));
        g.agregarCliente(new Cliente("Juan", "Perez", "600000000", "Calle A"));
        g.guardar("testFichero3.dat");
        GestionPedidos g2 = new GestionPedidos();
        g2.agregarCliente(new Cliente("Maria", "Garcia", "600000001", "Calle B"));
        g2.recuperarAgregar("testFichero3.dat");
        assertNotNull(g2.buscarCliente("600000000"));
        assertNotNull(g2.buscarCliente("600000001"));
        new File("testFichero3.dat").delete();
    }

    //No duplica productos al recuperar y agregar
    @Test
    void testRecuperarAgregarProductosDuplicados() {
        GestionPedidos g = new GestionPedidos();
        g.agregarProducto(new Producto("Agua", 0.35));
        g.guardar("testFichero4.dat");
        g.recuperarAgregar("testFichero4.dat");
        assertNotNull(g.buscarProducto("AGUA"));
        new File("testFichero4.dat").delete();
    }
}