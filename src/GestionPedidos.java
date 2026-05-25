import java.sql.SQLOutput;
import java.util.ArrayList;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

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

    /**
     * Guarda los datos de los clientes y de los productos en un fichero
     */
    public void guardar(String fichero){
        FileOutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fos = new FileOutputStream(fichero);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(clientes);
            oos.writeObject(productos);
            oos.close();
            fos.close();
            System.out.println("Se ha guardado el archivo");
        }catch (IOException e) {
            System.out.println("Error al guardar el archivo");
        }
    }

    /**
     * Recupera los datos de los clientes y de los productos del fichero
     */
    public void recuperar(String fichero){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(fichero);
            ois = new ObjectInputStream(fis);
            clientes = (ArrayList<Cliente>) ois.readObject();
            productos = (ArrayList<Producto>) ois.readObject();
            ois.close();
            fis.close();
            System.out.println("Se ha recuperado el archivo");

        }catch (IOException e){
            System.out.println("Error al recuperar el archivo");
        } catch (ClassNotFoundException e) {
            System.out.println("Error al recuperar el archivo");
        }
    }

    /**
     * Busca un producto a partir del nombre introducido
     * Si no llega a existir le devuelve null al usuario
     */
    public Producto buscarProducto(String nombre){
        for (int i = 0; i < productos.size(); i++){
            Producto p = productos.get(i);
            if(p.getNombre().equals(nombre)){
                return p;
            }
        }
        return null;
    }


    /**
     * Saca los datos de una archivo para leerlo y agrega datos pero sin duplicar datos
     */
    public void recuperarAgregar(String fichero){
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try{
            fis = new FileInputStream(fichero);
            ois = new ObjectInputStream(fis);
            ArrayList<Cliente> clientesTemporales = (ArrayList<Cliente>) ois.readObject(); //Se crean listas temporales para guardar los datos para luego guardarlo en el fichero
            ArrayList<Producto> productosTemporales = (ArrayList<Producto>) ois.readObject();
            ois.close();
            fis.close();
            for (int i = 0; i < clientesTemporales.size(); i++) {
                Cliente c = clientesTemporales.get(i);
                if (buscarCliente(c.getTelefono()) == null){
                    clientes.add(c);
                }
            }
            for (int i = 0; i < productosTemporales.size(); i++) {
                Producto p = productosTemporales.get(i);
                if (buscarProducto(p.getNombre()) == null) {
                    productos.add(p);
                }
            }
            System.out.println("Se han guardado los datos de manera existosa en el fichero: " + fichero);

        } catch (IOException e) {
            System.out.println("Error al recuperar el archivo");;
        }catch (ClassNotFoundException e){
            System.out.println("Errpr al recuperar el archivo");
        }

    }


}