import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests para la pasarela de pagos.
 *
 * @author Carlos Velasco Sala
 * @since 1.0
 */
class PasarelaDePagoTest {

    //Pago correcto en efectivo
    @Test
    void testPagoEfectivoCorrecto() {
        PasarelaDePago pago = new PasarelaDePago(10);
        assertTrue(pago.pagoEfectivo(20));
    }

    //Pago incorrecto porque no hay suficiente dinero
    @Test
    void testPagoEfectivoIncorrecto() {
        PasarelaDePago pago = new PasarelaDePago(10);
        assertFalse(pago.pagoEfectivo(5));
    }

    //Importe negativo se guarda como 0
    @Test
    void testConstructorImporteNegativo() {
        PasarelaDePago pago = new PasarelaDePago(-5);
        assertEquals(0, pago.getImporte());
    }

    //El codigo de pago inicial es 0
    @Test
    void testConstructorCodigoInicial() {
        PasarelaDePago pago = new PasarelaDePago(10);
        assertEquals(0, pago.getCodigoPago());
    }

    //No se puede pagar dos veces en efectivo
    @Test
    void testPagoEfectivoYaPagado() {
        PasarelaDePago pago = new PasarelaDePago(10);
        pago.pagoEfectivo(10);
        assertFalse(pago.pagoEfectivo(10));
    }

    //Pago correcto genera codigo distinto de 0
    @Test
    void testPagoEfectivoGeneraCodigo() {
        PasarelaDePago pago = new PasarelaDePago(10);
        pago.pagoEfectivo(10);
        assertTrue(pago.getCodigoPago() != 0);
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

    //Tarjeta con letras devuelve false
    @Test
    void testTarjetaInvalidaLetras() {
        PasarelaDePago pago = new PasarelaDePago(10);
        assertFalse(pago.pagoTarjeta("411111111111111A"));
    }

    //No se puede pagar con tarjeta dos veces
    @Test
    void testTarjetaYaPagada() {
        PasarelaDePago pago = new PasarelaDePago(10);
        pago.pagoTarjeta("4111111111111111");
        assertFalse(pago.pagoTarjeta("4111111111111111"));
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

    //Cuenta con letras devuelve false
    @Test
    void testCuentaConLetras() {
        PasarelaDePago pago = new PasarelaDePago(10);
        assertFalse(pago.pagoCuenta("1234567890123456789A"));
    }

    //No se puede pagar con cuenta dos veces
    @Test
    void testCuentaYaPagada() {
        PasarelaDePago pago = new PasarelaDePago(10);
        pago.pagoCuenta("12345678901234567890");
        assertFalse(pago.pagoCuenta("12345678901234567890"));
    }

    //Pago con cuenta genera codigo distinto de 0
    @Test
    void testCuentaGeneraCodigo() {
        PasarelaDePago pago = new PasarelaDePago(10);
        pago.pagoCuenta("12345678901234567890");
        assertTrue(pago.getCodigoPago() != 0);
    }
}