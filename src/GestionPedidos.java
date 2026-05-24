import java.util.ArrayList;

/**
 * Clase que se encarga de gestionar los clientes y los productos.
 * Desde aquí se pueden buscar clientes y mostrar los productos disponibles.
 */
public class GestionPedidos {

    //Lista de clientes del sistema
    private ArrayList<Cliente> clientes;

    //Lista de productos disponibles
    private ArrayList<Producto> productos;

    /**
     * Constructor de la clase.
     * Inicializa las listas de clientes y productos.
     */
    public GestionPedidos() {
        clientes = new ArrayList<>();
        productos = new ArrayList<>();
    }

    /**
     * Añade un cliente al sistema.
     */
    public void agregarCliente(Cliente cliente) {
        clientes.add(cliente);
    }

    /**
     * Añade un producto al sistema.
     */
    public void agregarProducto(Producto producto) {
        productos.add(producto);
    }

    /**
     * Busca un cliente a partir de su teléfono.
     * Si no existe devuelve null.
     */
    public Cliente buscarCliente(String telefono) {
        for (int i = 0; i < clientes.size(); i++) {
            Cliente c = clientes.get(i);
            if (c.getTelefono().equals(telefono)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Muestra por pantalla todos los productos disponibles.
     */
    public void mostrarProductos() {

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);
            System.out.println((i + 1) + ".- " + p.getNombre() + "   " + p.getPrecio() + "€");
        }
    }

    /**
     * Devuelve un producto según la posición elegida.
     * Si la posición no es válida devuelve null.
     */
    public Producto obtenerProducto(int posicion) {
        if (posicion < 1 || posicion > productos.size()) {
            return null;
        }
        return productos.get(posicion - 1);
    }
}
