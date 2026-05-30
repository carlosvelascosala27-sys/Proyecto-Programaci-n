import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

/**
 * Clase que representa un cliente del sistema.
 * Guarda los datos del cliente y su historial de pedidos.
 *
 * @author Carlos Velasco Sala
 * @since 1.0
 */
public class Cliente implements Serializable {

    private String nombre;
    private String apellidos;
    private Date fechaAlta;
    private String telefono;
    private String direccion;
    private ArrayList<Pedido> historial;

    /**
     * Constructor de la clase Cliente.
     * Recibe los datos básicos del cliente.
     *
     * @param nombre nombre del cliente
     * @param apellidos apellidos del cliente
     * @param telefono teléfono del cliente
     * @param direccion dirección del cliente
     */
    public Cliente(String nombre,  String apellidos, String telefono, String direccion) {

        this.nombre = nombre.toLowerCase();
        this.apellidos = apellidos.toUpperCase();
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaAlta = new Date();
        this.historial = new ArrayList<>();
    }

    /**
     * Devuelve el teléfono del cliente.
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * Devuelve el historial de pedidos del cliente.
     */
    public ArrayList<Pedido> getHistorial(){
        return historial;
    }

    /**
     * Añade un pedido al historial del cliente.
     * Solo se añade si el pedido es válido y está pagado.
     *
     * @param pedido pedido que se quiere añadir
     */
    public boolean agregarPedido(Pedido pedido) {

        //Si el pedido es null no se añade
        if(pedido == null) {
            return false;
        }

        //El pedido debe estar pagado
        if(pedido.getEstado() != EstadoPedido.PAGADO){
            return false;
        }

        //Comprobamos que el pedido sea de ese cliente
        if (pedido.getCliente() != this) {
            return false;
        }

        //Se comprueba que no exista otro pedido con la misma fecha
        for (int i = 0; i < historial.size(); i++) {
            Pedido p = historial.get(i);

            if (p.getFechaHora().equals(pedido.getFechaHora())) {
                return false;
            }
        }

        //Si todo es correcto se añade al historial
        historial.add(pedido);
        return true;
    }

    /**
     * Crea un nuevo pedido para este cliente.
     */
    public Pedido realizarPedido() {
        Pedido pedido = new Pedido(this);
        return pedido;
    }
}