import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para comprobar que la clase Producto funciona correctamente.
 *
 * @author Carlos Velasco Sala
 * @since 1.0
 */
class ProductoTest {

    //Comprobamos que el nombre se guarda en mayúsculas
    @Test
    void testNombreMayusculas() {
        Producto p = new Producto("agua", 0.35);
        assertEquals("AGUA", p.getNombre());
    }

    //Comprobamos que el precio se guarda correctamente
    @Test
    void testPrecioCorrecto() {
        Producto p = new Producto("Agua", 0.35);
        assertTrue(p.getPrecio() == 0.35);
    }

    //Si el precio es negativo debería ponerse a 0
    @Test
    void testPrecioNegativo() {
        Producto p = new Producto("Agua", -5);
        assertEquals(0, p.getPrecio());
    }

    //Probamos el setter del nombre
    @Test
    void testSetNombre() {
        Producto p = new Producto("agua", 0.35);
        p.setNombre("pizza");
        assertEquals("PIZZA", p.getNombre());
    }

    //Comprobamos que el setter del precio funciona
    @Test
    void testSetPrecio() {
        Producto p = new Producto("Agua", 0.35);
        p.setPrecio(2.5);
        assertTrue(p.getPrecio() == 2.5);
    }

    //Precio cero se guarda como 0
    @Test
    void testConstructorPrecioCero() {
        Producto p = new Producto("Agua", 0);
        assertEquals(0, p.getPrecio());
    }

    //El precio se redondea a 2 decimales
    @Test
    void testConstructorPrecioRedondeado() {
        Producto p = new Producto("Agua", 1.556);
        assertEquals(1.56, p.getPrecio());
    }

    //Comprobamos el formato del toString
    @Test
    void testToString() {
        Producto p = new Producto("AGUA", 0.35);
        assertEquals("AGUA - 0.35 €", p.toString());
    }
}