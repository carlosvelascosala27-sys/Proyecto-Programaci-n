import java.util.Date;
import java.io.Serializable;
/**
 * Clase que simula una pasarela de pagos.
 * Se encarga de realizar el pago de un pedido mediante efectivo, tarjeta o cuenta bancaria.
 */
public class PasarelaDePago implements Serializable {

    //Importe que se debe pagar
    private double importe;

    //Código del pago generado cuando se realiza correctamente
    private long codigoPago;

    /**
     * Constructor de la pasarela de pago.
     * Recibe el importe que se debe pagar.
     */
    public PasarelaDePago(double importe) {

        //Comprobamos que el importe no sea negativo
        if (importe <= 0) {
            this.importe = 0;
        } else {
            //Redondeamos el importe a dos decimales
            this.importe = Math.round(importe * 100.0) / 100.0;
        }

        //El código de pago se genera cuando se realiza el pago
        this.codigoPago = 0;
    }

    /**
     * Devuelve el importe del pago.
     */
    public double getImporte() {
        return importe;
    }

    /**
     * Devuelve el código del pago.
     */
    public long getCodigoPago() {
        return codigoPago;
    }

    /**
     * Método para pagar en efectivo.
     * Calcula el cambio y lo muestra por pantalla.
     */
    public boolean pagoEfectivo(double cantidadEntrega) {

        //Si el importe ya es 0 significa que ya está pagado
        if (this.importe == 0) {
            return false;
        }

        //Si el dinero entregado es menor no se puede pagar
        if (cantidadEntrega < this.importe) {
            return false;
        }

        //Calculamos el cambio
        double cambio = cantidadEntrega - this.importe;
        cambio = Math.round(cambio * 100.0) / 100.0;

        //Pasamos el cambio a céntimos para calcular billetes y monedas
        int centimos = (int) Math.round(cambio * 100);
        int billetes50 = centimos / 5000;
        centimos %= 5000;
        int billetes20 = centimos / 2000;
        centimos %= 2000;
        int billetes10 = centimos / 1000;
        centimos %= 1000;
        int billetes5 = centimos / 500;
        centimos %= 500;
        int monedas1 = centimos / 100;
        centimos %= 100;

        //Mostrar el cambio
        System.out.println("\nSU CAMBIO");
        System.out.println("******************");
        System.out.println("50€ -> " + billetes50);
        System.out.println("20€ -> " + billetes20);
        System.out.println("10€ -> " + billetes10);
        System.out.println("5€ -> " + billetes5);
        System.out.println("1€ -> " + monedas1);
        System.out.println("cent -> " + centimos);

        //Se genera el código del pago usando la fecha actual
        this.codigoPago = new Date().getTime();

        //El importe pasa a 0 porque ya está pagado
        this.importe = 0;
        return true;
    }

    /**
     * Método para pagar con tarjeta.
     * Comprueba que el número de tarjeta sea válido.
     */
    public boolean pagoTarjeta(String numeroTarjeta) {

        if (this.importe == 0) {
            return false;
        }

        //Eliminar espacios o guiones
        numeroTarjeta = numeroTarjeta.trim().replace(" ", "").replace("-", "");

        //Comprobamos que tenga 16 dígitos
        if (numeroTarjeta.length() != 16) {
            return false;
        }

        //Comprobamos que todos sean números
        if (numeroTarjeta.charAt(0) < '0' || numeroTarjeta.charAt(0) > '9') {
            return false;
        }

        for (int i = 1; i < numeroTarjeta.length(); i++) {
            char c = numeroTarjeta.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        //Detectamos el tipo de tarjeta según el primer número
        char tipoTarjeta = numeroTarjeta.charAt(0);

        if (tipoTarjeta == '4') {
            System.out.println("Visa");
        } else if (tipoTarjeta == '5') {
            System.out.println("MasterCard");
        } else if (tipoTarjeta == '3') {
            System.out.println("American Express");
        }

        //Generamos el código de pago
        this.codigoPago = new Date().getTime();
        this.importe = 0;
        return true;
    }

    /**
     * Método para pagar con número de cuenta bancaria.
     */
    public boolean pagoCuenta(String numeroCuenta) {
        if (this.importe == 0) {
            return false;
        }

        //Eliminar posibles separadores
        numeroCuenta = numeroCuenta.trim().replace(" ", "").replace("-", "").replace("/", "");

        //Comprobar que tenga 20 dígitos
        if (numeroCuenta.length() != 20) {
            return false;
        }

        //Comprobar que todos los caracteres sean números
        for (int i = 1; i < numeroCuenta.length(); i++) {
            char c = numeroCuenta.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        //Generamos el código del pago
        this.codigoPago = new Date().getTime();

        this.importe = 0;
        return true;
    }
}