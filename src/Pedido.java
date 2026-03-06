import java.util.ArrayList;
import java.util.Date;

public class Pedido {
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

        contenido += "CANT.\tPRODUCTO\tPRECIO UD.\tTOTAL\n";
        contenido += "==============================================\n";


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

                contenido += cantidad + "\t" + nombre + "\t" + precioUnidad + "\t" + totalProductos + "\n";
                lista.add(nombre);
            }
        }

        contenido += "==============================================\n";
        contenido += "TOTAL: " + this.importeTotal + " €\n";

        return contenido;

    }
}
