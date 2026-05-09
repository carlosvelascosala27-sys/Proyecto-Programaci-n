import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;


/**
 * Clase que representa un pedido de un cliente.
 * Un pedido contiene los productos elegidos y el importe total.
 * También se encarga de realizar el pago.
 */


public class Pedido implements Serializable {

    //Cliente que realiza el pedido
    private Cliente cliente;

    //Lista de productos que contiene el pedido
    private ArrayList<Producto> productos;

    //Importe total del pedido
    private double importeTotal;

    //Objeto que gestiona el pago
    private PasarelaDePago pago;

    //Estado en el que se encuentra el pedido
    private EstadoPedido estado;

    //Fecha y hora en la que se realiza el pago
    private Date fechaHora;

    /**
     * Constructor del pedido.
     * Recibe el cliente que realiza el pedido.
     */
    public Pedido(Cliente cliente) {
        this.cliente = cliente;
        this.productos = new ArrayList<>();
        this.importeTotal = 0;
    }

    /**
     * Devuelve el cliente que ha realizado el pedido.
     */
    public Cliente getCliente() {
        return cliente;
    }

    /**
     * Devuelve el estado actual del pedido.
     */
    public EstadoPedido getEstado() {
        return estado;
    }

    /**
     * Devuelve la fecha y hora del pedido.
     */
    public Date getFechaHora() {
        return fechaHora;
    }

    /**
     * Devuelve el importe total del pedido.
     */
    public double getImporteTotal() {
        return importeTotal;
    }

    /**
     * Devuelve la pasarela de pago asociada al pedido.
     */
    public PasarelaDePago getPago(){
        return pago;
    }

    /**
     * Añade un producto al pedido.
     */
    public boolean agregarProducto(Producto producto) {

        //Si el pedido ya está pagado no se pueden añadir más productos
        if (estado == EstadoPedido.PAGADO) {
            return false;
        }
        this.productos.add(producto);

        //Sumamos el precio al total
        this.importeTotal += producto.getPrecio();

        //Redondeamos a dos decimales
        this.importeTotal = Math.round(importeTotal * 100.0) / 100.0;
        return true;
    }

    /**
     * Elimina un producto del pedido según su posición.
     */
    public boolean eliminarProducto(int posicion) {

        //Si el pedido ya está pagado no se puede modificar
        if (estado == EstadoPedido.PAGADO) {
            return false;
        }

        //Comprobamos que la posición sea válida
        if (posicion < 0 || posicion >= this.productos.size()) {
            return false;
        }
        Producto p = this.productos.get(posicion);

        //Restamos el precio al total
        this.importeTotal -= p.getPrecio();

        this.importeTotal = Math.round(importeTotal * 100.0) / 100.0;
        this.productos.remove(posicion);
        return true;
    }

    /**
     * Método para pagar el pedido.
     * Dependiendo del tipo de pago se usa un método distinto.
     */
    public boolean pagar(int tipoPago, String dato) {

        //Si ya está pagado no se puede volver a pagar
        if (estado == EstadoPedido.PAGADO) {
            return false;
        }

        //Si no hay productos no tiene sentido pagar
        if (productos.isEmpty()) {
            return false;
        }

        //Creamos la pasarela de pago con el importe del pedido
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

        //Si el pago se realiza correctamente se actualiza el estado
        if(resultado){
            this.fechaHora = new Date();
            this.estado = EstadoPedido.PAGADO;
            return true;
        }
        return false;
    }

    /**
     * Devuelve el pedido en formato texto.
     * Agrupa los productos iguales y muestra cantidad y total.
     */
    @Override
    public String toString() {
        String contenido = "";
        contenido += String.format("%-5s %-12s %-12s %-8s\n",
                "CANT.", "PRODUCTO", "PRECIO UD.", "TOTAL");

        contenido += "===============================================\n";

        //Lista para controlar productos repetidos
        ArrayList<String> lista = new ArrayList<>();

        for (int i = 0; i < this.productos.size(); i++) {
            String nombre = this.productos.get(i).getNombre();

            if(!lista.contains(nombre)){
                int cantidad = 0;

                //Contamos cuántas veces aparece el producto
                for (int j = 0; j < this.productos.size(); j++) {

                    if(nombre.equals(this.productos.get(j).getNombre())){
                        cantidad++;
                    }
                }

                double precioUnidad = this.productos.get(i).getPrecio();
                double totalProductos = precioUnidad * cantidad;

                totalProductos = Math.round(totalProductos * 100.0) / 100.0;

                //Alinear los datos según como nos piden, reclcar que desconocía este forma de colocar los números,
                // gracias a videos y la ia me han ayudado a colocar mejor los números
                contenido += String.format("%-5d %-12s %-12.2f %-8.2f\n", cantidad, nombre, precioUnidad, totalProductos);

                lista.add(nombre);
            }
        }

        contenido += "==============================================\n";

        contenido += "TOTAL --------------------------------------------> " + String.format("%.2f", this.importeTotal) + " €\n";

        return contenido;
    }
}
