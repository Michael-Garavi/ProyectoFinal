public class Nodo {
    Ticket ticket;
    Nodo isquierdo ,derecho;
    public Nodo(Ticket ticket) {
        this.ticket = ticket;
        isquierdo = derecho = null;
    }
}
