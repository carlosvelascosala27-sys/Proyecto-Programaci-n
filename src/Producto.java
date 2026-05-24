import java.io.Serializable;
/**
 * Clase que representa un producto que se puede pedir.
 * Cada producto tiene un nombre y un precio.
 */
public class Producto implements Serializable {

    //Nombre del producto
    private String nombre;

    //Precio del producto
    private double precio;

    /**
     * Constructor de la clase Producto.
     * Recibe el nombre y el precio del producto.
     */
    public Producto(String nombre, double precio) {

        //El nombre siempre se guarda en mayúsculas
        this.nombre = nombre.toUpperCase();

        //Comprobamos que el precio no sea negativo
        if (precio <= 0) {
            this.precio = 0;
        } else {
            //Redondeamos el precio a dos decimales
            this.precio = Math.round(precio * 100.0) / 100.0;
        }
    }

    /**
     * Devuelve el nombre del producto.
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Permite cambiar el nombre del producto.
     * El nombre se guarda en mayúsculas.
     */
    public void setNombre(String nombre) {
        this.nombre = nombre.toUpperCase();
    }

    /**
     * Devuelve el precio del producto.
     */
    public double getPrecio() {
        return precio;
    }

    /**
     * Permite cambiar el precio del producto.
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    /**
     * Muestra el producto en formato texto.
     */
    @Override
    public String toString() {
        return nombre + " - " + precio + " €";
    }
}