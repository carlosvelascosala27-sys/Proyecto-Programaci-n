import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        GestionPedidos gestion = new GestionPedidos();

        gestion.agregarProducto(new Producto("AGUA", 0.35));
        gestion.agregarProducto(new Producto("BOCADILLO", 3.55));
        gestion.agregarProducto(new Producto("CERVEZA", 0.75));
        gestion.agregarProducto(new Producto("COCACOLA", 0.59));
        gestion.agregarProducto(new Producto("HAMBURGUESA", 3.75));
        gestion.agregarProducto(new Producto("PIZZA", 7.55));


        Cliente c1 = new Cliente("Antonio", "Velasco Sala", "630414951", "Calle Francisco Ruíz");
        gestion.agregarCliente(c1);

        while (true) {
            System.out.print("INTRODUZCA TELEFONO (0 SALIR): ");
            String telefono = sc.nextLine();

            if (telefono.equals("0")) {
                System.out.println("GRACIAS POR USAR NUESTRO SOFTWARE!");
                break;
            }

            Cliente cliente = gestion.buscarCliente(telefono);

            if (cliente == null) {
                System.out.println("ERROR: El cliente no existe!");
                continue;
            }

            Pedido pedido = cliente.realizarPedido();

            boolean pagar = false;

            while (!pagar) {

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

                    Producto producto = gestion.obtenerProducto(opcion);

                    if (producto != null) {
                        pedido.agregarProducto(producto);
                    } else {
                        System.out.println("Producto incorrecto");
                    }
                }

                System.out.println("Resumen de su pedido:");
                System.out.println(pedido);

                System.out.print("¿Continuar? (S/N): ");
                String continuar = sc.nextLine();

                if (continuar.equalsIgnoreCase("s")) {

                    System.out.println("IMPORTE " + pedido.getImporteTotal() + " €");
                    System.out.println("1.- EFECTIVO");
                    System.out.println("2.- TARJETA");
                    System.out.println("3.- CUENTA");

                    System.out.print("Seleccione un metodo de pago: ");
                    int metodo = Integer.parseInt(sc.nextLine());

                    System.out.print("Introduce el dato de pago: ");
                    String dato = sc.nextLine();

                    boolean pagado = pedido.pagar(metodo, dato);

                    if (pagado) {

                        cliente.agregarPedido(pedido);

                        System.out.println("OPERACION REALIZADA CON EXITO.");

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
