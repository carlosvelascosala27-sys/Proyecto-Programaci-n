/**
 * Enum que representa los diferentes estados de un pedido.
 * Un pedido va pasando por estos estados durante el proceso.
 */
public enum EstadoPedido {

    //El pedido ya ha sido pagado
    PAGADO,

    //El pedido se está preparando
    PREPARANDO,

    //El pedido ya está listo para entregarse
    LISTO,

    //El pedido ya ha sido servido al cliente
    SERVIDO
}