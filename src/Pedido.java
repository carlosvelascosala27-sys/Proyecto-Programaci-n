import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Pedido implements Serializable {
    private Cliente cliente;
    private ArrayList<Producto> productos;
    private double importeTotal;
    private PasarelaDePago pago;
    private EstadoPedido estado;
    private Date fechaHora;

    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.importeTotal = 0;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public Date getFechaHora() {
        return fechaHora;
    }

    public double getImporteTotal() {
        return importeTotal;
    }

    public PasarelaDePago getPago(){
        return pago;
    }

    public boolean agregarProducto(Producto producto) {
        if (estado == EstadoPedido.PAGADO) {
            return false;
        }
        this.productos.add(producto);
        this.importeTotal += producto.getPrecio();
        this.importeTotal = Math.round(importeTotal * 100.0) / 100.0;

        return true;
    }

    public boolean eliminarProducto(int posicion) {
        if (estado == EstadoPedido.PAGADO) {
            return false;
        }

        if (posicion < 0 || posicion >= this.productos.size()) {
            return false;
        }

        Producto p = this.productos.get(posicion);

        this.importeTotal -= p.getPrecio();

        this.importeTotal = Math.round(importeTotal * 100.0) / 100.0;
        this.productos.remove(posicion);

        return true;
    }

    public boolean pagar(int tipoPago, String dato) {
        if (estado == EstadoPedido.PAGADO) {
            return false;
        }

        if (productos.isEmpty()) {
            return false;
        }

        this.pago = new PasarelaDePago(this.importeTotal);

        boolean resultado = false;

        switch (tipoPago) {
            case 1:
                double cantidad = Double.parseDouble(dato);
                resultado = pago.pagoEfectivo(cantidad);
                break;

            case 2:
                resultado = pago.pagoTarjeta(dato);
                break;

            case 3:
                resultado = pago.pagoCuenta(dato);
                break;

            default:
                return false;
        }

        if(resultado){
            this.fechaHora = new Date();
            this.estado = EstadoPedido.PAGADO;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String contenido = "";

        //Alinear los datos según como nos piden, reclcar que desconocía este forma de colocar los números,
        // gracias a videos y la ia me han ayudado a colocar mejor los números
        contenido += String.format("%-5s %-12s %-12s %-8s\n", "CANT.", "PRODUCTO", "PRECIO UD.", "TOTAL");
        contenido += "===============================================\n";


        ArrayList<String> lista = new ArrayList<>();
        for (int i = 0; i < this.productos.size(); i++) {
            String nombre = this.productos.get(i).getNombre();
            if(!lista.contains(nombre)){
                int cantidad = 0;
                for (int j = 0; j < this.productos.size(); j++) {
                    if(nombre.equals(this.productos.get(j).getNombre())){
                        cantidad++;
                    }
                }
                double precioUnidad = this.productos.get(i).getPrecio();

                double totalProductos = precioUnidad * cantidad;

                totalProductos = Math.round(totalProductos * 100.0) / 100.0;

                contenido += String.format("%-5d %-12s %-12.2f %-8.2f\n", cantidad, nombre, precioUnidad, totalProductos);

                lista.add(nombre);
            }
        }

        contenido += "==============================================\n";
        contenido += "TOTAL --------------------------------------------> "
                + String.format("%.2f", this.importeTotal) + " €\n";

        return contenido;

    }
}
