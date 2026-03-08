import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la pasarela de pagos.
 */
class PasarelaDePagoTest {

    //Pago correcto en efectivo
    @Test
    void testPagoEfectivoCorrecto() {

        PasarelaDePago pago = new PasarelaDePago(10);

        boolean resultado = pago.pagoEfectivo(20);

        assertTrue(resultado);
    }

    //Pago incorrecto porque no hay suficiente dinero
    @Test
    void testPagoEfectivoIncorrecto() {

        PasarelaDePago pago = new PasarelaDePago(10);

        boolean resultado = pago.pagoEfectivo(5);

        assertFalse(resultado);
    }

    //Tarjeta válida
    @Test
    void testTarjetaValida() {

        PasarelaDePago pago = new PasarelaDePago(10);

        assertTrue(pago.pagoTarjeta("4123456789012345"));
    }

    //Tarjeta incorrecta por longitud
    @Test
    void testTarjetaInvalida() {

        PasarelaDePago pago = new PasarelaDePago(10);

        assertFalse(pago.pagoTarjeta("1234"));
    }

    //Cuenta bancaria válida
    @Test
    void testCuentaValida() {

        PasarelaDePago pago = new PasarelaDePago(10);

        assertTrue(pago.pagoCuenta("12345678901234567890"));
    }

    //Cuenta incorrecta
    @Test
    void testCuentaInvalida() {

        PasarelaDePago pago = new PasarelaDePago(10);

        assertFalse(pago.pagoCuenta("123"));
    }

}