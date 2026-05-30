import java.io.Serializable;
/**
 * Enum que representa los diferentes estados de un pedido.
 * Un pedido va pasando por estos estados durante el proceso.
 *
 * @author Carlos Velasco Sala
 * @since 1.0
 */
public enum EstadoPedido implements  Serializable {

    //El pedido ya ha sido pagado
    PAGADO,

    //El pedido se está preparando
    PREPARANDO,

    //El pedido ya está listo para entregarse
    LISTO,

    //El pedido ya ha sido servido al cliente
    SERVIDO
}