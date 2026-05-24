import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para comprobar la clase Pedido.
 */
class PedidoTest {

    //Comprobamos que se puede añadir un producto al pedido
    @Test
    void testAgregarProducto() {

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");
        Pedido p = new Pedido(c);

        Producto prod = new Producto("Agua",0.35);

        boolean resultado = p.agregarProducto(prod);

        assertTrue(resultado);
    }

    //Comprobamos que el importe total se actualiza
    @Test
    void testImporteTotal() {

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");
        Pedido p = new Pedido(c);

        Producto prod = new Producto("Agua",0.35);

        p.agregarProducto(prod);

        assertTrue(p.getImporteTotal() == 0.35);
    }

    //Comprobamos que eliminar un producto funciona
    @Test
    void testEliminarProducto() {

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");
        Pedido p = new Pedido(c);

        Producto prod = new Producto("Agua",0.35);

        p.agregarProducto(prod);

        boolean eliminado = p.eliminarProducto(0);

        assertTrue(eliminado);
    }

    //Si eliminamos una posición incorrecta debería devolver false
    @Test
    void testEliminarProductoIncorrecto() {

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");
        Pedido p = new Pedido(c);

        boolean eliminado = p.eliminarProducto(5);

        assertFalse(eliminado);
    }

    //Comprobamos que no se puede pagar si no hay productos
    @Test
    void testPagarSinProductos() {

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");
        Pedido p = new Pedido(c);

        boolean pagado = p.pagar(1,"10");

        assertFalse(pagado);
    }

    //Comprobamos que se puede pagar correctamente en efectivo
    @Test
    void testPagarEfectivoCorrecto() {

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");
        Pedido p = new Pedido(c);

        Producto prod = new Producto("Agua",0.35);

        p.agregarProducto(prod);

        boolean resultado = p.pagar(1,"1");

        assertTrue(resultado);
    }

    //Comprobamos que el pago con tarjeta funciona
    @Test
    void testPagarTarjetaCorrecta() {

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");
        Pedido p = new Pedido(c);

        Producto prod = new Producto("Pizza",7.55);

        p.agregarProducto(prod);

        boolean resultado = p.pagar(2,"4123456789012345");

        assertTrue(resultado);
    }

    //Comprobamos que una tarjeta incorrecta devuelve false
    @Test
    void testPagarTarjetaIncorrecta() {

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");
        Pedido p = new Pedido(c);

        Producto prod = new Producto("Pizza",7.55);

        p.agregarProducto(prod);

        boolean resultado = p.pagar(2,"123");

        assertFalse(resultado);
    }

    //Comprobamos que no se puede pagar dos veces el mismo pedido
    @Test
    void testPagarDosVeces() {

        Cliente c = new Cliente("Juan","Perez","600000000","Calle A");
        Pedido p = new Pedido(c);

        Producto prod = new Producto("Agua",0.35);

        p.agregarProducto(prod);

        p.pagar(1,"1");

        boolean segundoPago = p.pagar(1,"1");

        assertFalse(segundoPago);
    }



}