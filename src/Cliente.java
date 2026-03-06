import java.util.ArrayList;
import java.util.Date;

public class Cliente {
    private String nombre;
    private String apellidos;
    private Date fechaAlta;
    private String telefono;
    private String direccion;
    private ArrayList<Pedido> historial;

    public Cliente(String nombre,  String apellidos, String telefono, String direccion) {
        this.nombre = nombre.toLowerCase();
        this.apellidos = apellidos.toUpperCase();
        this.telefono = telefono;
        this.direccion = direccion;
        this.fechaAlta = new Date();
        this.historial = new ArrayList<>();
    }

    public String getTelefono() {
        return telefono;
    }

    public boolean agregarPedido(Pedido pedido) {
        if(pedido == null) {
            return false;
        }

        if(pedido.getEstado() != EstadoPedido.PAGADO){
            return false;
        }

        if (pedido.getCliente() != this) {
            return false;
        }

        for (int i = 0; i < historial.size(); i++) {
            Pedido p = historial.get(i);

            if (p.getFechaHora().equals(pedido.getFechaHora())) {
                return false;
            }
        }

        historial.add(pedido);
        return true;
    }


    public Pedido realizarPedido() {
        Pedido pedido = new Pedido(this);
        return pedido;
    }
}
