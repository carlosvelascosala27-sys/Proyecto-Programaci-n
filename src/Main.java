import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Clase principal del programa.
 * Desde aquí se ejecuta toda la aplicación de gestión de pedidos.
 * Permite introducir el teléfono del cliente, hacer pedidos y pagarlos.
 */
public class Main {

    /**
     * Método principal del programa.
     * Controla todo el sistema: buscar cliente, realizar pedido, añadir productos y pagar.
     */
    public static void main(String[] args) {

        //Scanner para leer datos desde consola
        Scanner sc = new Scanner(System.in);

        //Objeto que gestiona clientes y productos
        GestionPedidos gestion = new GestionPedidos();

        //Productos disponibles en el sistema
        gestion.agregarProducto(new Producto("AGUA", 0.35));
        gestion.agregarProducto(new Producto("BOCADILLO", 3.55));
        gestion.agregarProducto(new Producto("CERVEZA", 0.75));
        gestion.agregarProducto(new Producto("COCACOLA", 0.59));
        gestion.agregarProducto(new Producto("HAMBURGUESA", 3.75));
        gestion.agregarProducto(new Producto("PIZZA", 7.55));

        //Cliente de pruebas
        Cliente c1 = new Cliente("Antonio", "Velasco Sala", "630414951", "Calle Francisco Ruíz");
        gestion.agregarCliente(c1);

        //Bucle principal del programa
        while (true) {

            //Pedir teléfono al usuario
            System.out.print("INTRODUZCA TELEFONO (0 SALIR): ");
            String telefono = sc.nextLine();

            //Si el usuario escribe 0 se termina el programa
            if (telefono.equals("0")) {
                System.out.println("GRACIAS POR USAR NUESTRO SOFTWARE!");
                break;
            }

            //Buscar cliente por teléfono
            Cliente cliente = gestion.buscarCliente(telefono);

            if (cliente == null) {
                System.out.println("ERROR: El cliente no existe!");
                continue;
            }

            //Nuevo pedido para el cliente
            Pedido pedido = cliente.realizarPedido();

            boolean pagar = false;

            //Bucle para seguir añadiendo productos hasta pagar
            while (!pagar) {

                //Bucle para seleccionar productos
                while (true) {

                    System.out.println("Su pedido:");
                    System.out.println(pedido);

                    System.out.println("Agregue los productos que desee a su pedido (0 para finalizar):");
                    gestion.mostrarProductos();

                    System.out.print("Elige un producto: ");
                    int opcion = Integer.parseInt(sc.nextLine());

                    if (opcion == 0) {
                        break;
                    }

                    //Obtener el producto elegido
                    Producto producto = gestion.obtenerProducto(opcion);

                    //Si el producto existe se añade al pedido
                    if (producto != null) {
                        pedido.agregarProducto(producto);
                    } else {
                        System.out.println("Producto incorrecto");
                    }
                }

                //Mostrar todo lo seleccionado del pedido
                System.out.println("Resumen de su pedido:");
                System.out.println(pedido);

                System.out.print("¿Quisiera usted continuar? (Si/No): ");
                String continuar = sc.nextLine();

                if (continuar.equalsIgnoreCase("Si")) {

                    //Mostrar importe total
                    System.out.println("IMPORTE " + pedido.getImporteTotal() + " €");

                    //Opciones para pagar el pedido
                    System.out.println("1.- EFECTIVO");
                    System.out.println("2.- TARJETA");
                    System.out.println("3.- CUENTA");

                    System.out.print("Seleccione un metodo de pago: ");
                    int metodo = Integer.parseInt(sc.nextLine());

                    //Dato necesario para el pago (tarjeta, cuenta o cantidad)
                    System.out.print("Introduce el dato de pago: ");
                    String dato = sc.nextLine();

                    boolean pagado = pedido.pagar(metodo, dato);

                    //Si el pago es correcto se guarda el pedido en el historial
                    if (pagado) {

                        cliente.agregarPedido(pedido);

                        System.out.println("OPERACION REALIZADA CON EXITO.");

                        //Mostrar información del pedido realizado
                        long codigo = pedido.getPago().getCodigoPago();
                        Date fecha = pedido.getFechaHora();
                        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                        System.out.println("PEDIDO: " + codigo + "   FECHA: " + formato.format(fecha) + "   ESTADO: " + pedido.getEstado());
                        pagar = true;

                    } else {
                        System.out.println("ERROR EN EL PAGO.");
                    }
                }
            }
        }
    }
}