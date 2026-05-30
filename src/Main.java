import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

/**
 * Clase principal del programa.
 * Desde aquí se ejecuta toda la aplicación de gestión de pedidos.
 * Permite introducir el teléfono del cliente, hacer pedidos y pagarlos.
 *
 * @author Carlos Velasco Sala
 * @since 1.0
 */
public class Main {

    /**
     * Método principal del programa.
     * Controla todo el sistema: buscar cliente, realizar pedido, añadir productos y pagar.
     *
     * @param args argumentos de la línea de comandos
     */
    public static void main(String[] args) {

        //Scanner para leer datos desde consola
        Scanner sc = new Scanner(System.in);

        //Objeto que gestiona clientes y productos
        GestionPedidos gestion = new GestionPedidos();

        System.out.println("¿Quieres cargar los archivos? (Responde: Si/No)");
        String cargar = sc.nextLine();

        if(cargar.equalsIgnoreCase("Si")) {
            System.out.println("Pon el nombre del fichero: ");
            String nombreFichero = sc.nextLine();
            gestion.recuperar(nombreFichero);
        }else {
            gestion.agregarProducto(new Producto("AGUA", 0.35));
            gestion.agregarProducto(new Producto("BOCADILLO", 3.55));
            gestion.agregarProducto(new Producto("CERVEZA", 0.75));
            gestion.agregarProducto(new Producto("COCACOLA", 0.59));
            gestion.agregarProducto(new Producto("HAMBURGUESA", 3.75));
            gestion.agregarProducto(new Producto("PIZZA", 7.55));
            Cliente c1 = new Cliente("Antonio", "Velasco Sala", "630414951", "Calle Francisco Ruíz");
            gestion.agregarCliente(c1);

        }


        //Bucle principal del programa
        while (true) {

            //Menú principal
            System.out.println("\n0- SALIR");
            System.out.println("1- INTRODUCIR TELEFONO");
            System.out.println("2- CARGAR MAS DATOS");
            System.out.println("3- VER DATOS");
            System.out.print("Seleccione una opcion: ");
            int menu = Integer.parseInt(sc.nextLine());

            if (menu == 0) {
                System.out.println("¿Quisieras guardar los datos de la compra? (Si/No)");
                String guardar = sc.nextLine();
                if(guardar.equalsIgnoreCase("Si")){
                    System.out.println("Pon el nombre que quieras al fichero:");
                    String nombreFichero = sc.nextLine();
                    gestion.guardar(nombreFichero);
                }
                System.out.println("GRACIAS POR USAR NUESTRO SOFTWARE!");
                break;
            }

            if (menu == 2) {
                System.out.print("Introduce el nombre del fichero del que desea cargar los datos: ");
                String nombreFichero = sc.nextLine();
                gestion.recuperarAgregar(nombreFichero);
                continue;
            }

            if (menu == 3){
                gestion.mostrarDatos();
                continue;
            }

            //Pedir teléfono al usuario
            System.out.print("INTRODUZCA TELEFONO (0 SALIR): ");
            String telefono = sc.nextLine();

            //Si el usuario escribe 0 se termina el programa
            if (telefono.equals("0")) {
                System.out.println("¿Quisieras guardar los datos de la compra? (Si/No)");
                String guardar = sc.nextLine();
                if(guardar.equalsIgnoreCase("Si")){
                    System.out.println("Pon el nombre que quieras al fichero:" );
                    String nombreFichero = sc.nextLine();
                    gestion.guardar(nombreFichero);
                }
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

                System.out.print("¿Desea usted proceder con el pago? (Moroso) (Si/No): ");
                String continuar = sc.nextLine();


                //Poder escribir si o no, sin importar que sea mayúscula o minúscula
                if (continuar.equalsIgnoreCase("Si")) {

                    boolean pagado = false;

                    //Bucle de pasarela de pagos
                    while (!pagado) {
                        System.out.println("IMPORTE " + pedido.getImporteTotal() + " €");
                        System.out.println("1.- EFECTIVO");
                        System.out.println("2.- TARJETA");
                        System.out.println("3.- CUENTA");

                        System.out.print("Seleccione un metodo de pago: ");
                        int metodo = Integer.parseInt(sc.nextLine());

                        System.out.print("Introduce el dato de pago: ");
                        String dato = sc.nextLine();

                        pagado = pedido.pagar(metodo, dato);

                        if (pagado) {
                            cliente.agregarPedido(pedido);
                            System.out.println("OPERACION REALIZADA CON EXITO.");

                            long codigo = pedido.getPago().getCodigoPago();
                            Date fecha = pedido.getFechaHora();
                            SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy HH:mm");

                            System.out.println("PEDIDO: " + codigo + "   FECHA: " + formato.format(fecha) + "   ESTADO: " + pedido.getEstado());

                        } else {
                            System.out.println("ERROR EN EL PAGO. Intente otro metodo.");
                        }
                    }
                    pagar = true;
                }else{
                    break;
                }
            }
        }
    }
}