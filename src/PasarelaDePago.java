import java.util.Date;

public class PasarelaDePago {
    private double importe;
    private long codigoPago;


    public PasarelaDePago(double importe) {
        if (importe <= 0) {
            this.importe = 0;
        }else  {
            this.importe = Math.round(importe * 100.0) / 100.0;
        }
        this.codigoPago = 0;
    }

    public double getImporte() {
        return importe;
    }

    public long getCodigoPago() {
        return codigoPago;
    }

    public boolean pagoEfectivo(double cantidadEntrega) {
        if (this.importe == 0) {
            return false;
        }

        cantidadEntrega = Math.round(cantidadEntrega * 100.0) / 100.0;

        if (cantidadEntrega < this.importe) {
            return false;
        }

        double cambioImporte = cantidadEntrega - this.importe;
        cambioImporte = Math.round(cambioImporte * 100.0) / 100.0;

        this.codigoPago = new Date().getTime();
        this.importe = 0;

        return true;
    }

    public boolean pagoTarjeta(String numeroTarjeta) {
        if (this.importe == 0) {
            return false;
        }

        numeroTarjeta = numeroTarjeta.trim().replace(" ", "").replace("-", "");

        if (numeroTarjeta.length() != 16) {
            return false;
        }

        if (numeroTarjeta.charAt(0) < '0' || numeroTarjeta.charAt(0) > '9') {
            return false;
        }

        for (int i = 1; i < numeroTarjeta.length(); i++) {
            char c = numeroTarjeta.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        char tipoTarjeta = numeroTarjeta.charAt(0);

        if (tipoTarjeta == '4') {
            System.out.println("Visa");
        }else if (tipoTarjeta == '5') {
            System.out.println("MasterCard");
        }else if (tipoTarjeta == '3') {
            System.out.println("American Express");
        }

        this.codigoPago = new Date().getTime();
        this.importe = 0;

        return true;
    }

    public boolean pagoCuenta(String numeroCuenta) {
        if (this.importe == 0) {
            return false;
        }

        numeroCuenta = numeroCuenta.trim().replace(" ", "").replace("-", "").replace("/", "");

        if (numeroCuenta.length() != 20) {
            return false;
        }

        for (int i = 1; i < numeroCuenta.length(); i++) {
            char c = numeroCuenta.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        this.codigoPago = new Date().getTime();
        this.importe = 0;

        return true;
    }
}
