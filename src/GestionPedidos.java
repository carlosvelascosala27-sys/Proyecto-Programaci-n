import java.util.ArrayList;

public class GestionPedidos {
    private ArrayList<Cliente> clientes;
    private ArrayList<Producto> productos;


    public GestionPedidos() {
        clientes = new ArrayList<>();
        productos = new ArrayList<>();
    }

    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    public Cliente buscarCliente(String telefono) {

        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);

            if (c.getTelefono().equals(telefono)) {
                return c;
            }
        }

        return null;
    }
}