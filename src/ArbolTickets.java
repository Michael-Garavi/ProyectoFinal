public class ArbolTickets {
    private Nodo raiz;

    //----------------InsertarTicket
    public void insertar(Ticket ticket) {
        raiz = insertarRec(raiz, ticket);
    }

    private Nodo insertarRec(Nodo nodo, Ticket ticket) {
        if (nodo == null) {
            return new Nodo(ticket);
        }

        if (ticket.getCodigoTicket() < nodo.ticket.getCodigoTicket()) {
            nodo.isquierdo = insertarRec(nodo.isquierdo, ticket);
        } else if (ticket.getCodigoTicket() > nodo.ticket.getCodigoTicket()) {
            nodo.derecho = insertarRec(nodo.derecho, ticket);
        }

        return nodo;
    }

    //--------------Buscar Ticket
    public Ticket buscar(int codigo) {
        return busarRec(raiz, codigo);
    }

    private Ticket busarRec(Nodo nodo, int codigo) {
        if (nodo == null) {
            return null;
        }

        if (codigo == nodo.ticket.getCodigoTicket()) {
            return nodo.ticket;
        }

        if (codigo < nodo.ticket.getCodigoTicket()) {
            return busarRec(nodo.isquierdo, codigo);
        } else {
            return busarRec(nodo.derecho, codigo);
        }
    }

    //----------------Eliminar Ticket
    public void eliminar(int codigo) {
        raiz = eliminarRec(raiz, codigo);
    }

    private Nodo eliminarRec(Nodo nodo, int codigo) {
        if (nodo == null) {
            return null;
        }

        if (codigo < nodo.ticket.getCodigoTicket()) {
            nodo.isquierdo = eliminarRec(nodo.isquierdo, codigo);
        } else if (codigo > nodo.ticket.getCodigoTicket()) {
            nodo.derecho = eliminarRec(nodo.derecho, codigo);
        } else {
            // Caso 1: no tiene hijos
            if (nodo.isquierdo == null && nodo.derecho == null) {
                return null;
            }

            // Caso 2: tiene solo hijo derecho
            if (nodo.isquierdo == null) {
                return nodo.derecho;
            }

            // Caso 3: tiene solo hijo izquierdo
            if (nodo.derecho == null) {
                return nodo.isquierdo;
            }

            // Caso 4: tiene dos hijos
            Nodo sucesor = encontrarMenor(nodo.derecho);
            nodo.ticket = sucesor.ticket;
            nodo.derecho = eliminarRec(nodo.derecho, sucesor.ticket.getCodigoTicket());
        }

        return nodo;
    }

    private Nodo encontrarMenor(Nodo nodo) {
        while (nodo.isquierdo != null) {
            nodo = nodo.isquierdo;
        }
        return nodo;
    }

    //-----------------Mostrar InOrder en consola
    public void mostrarInOrder() {
        mostrarInOrderRec(raiz);
    }

    private void mostrarInOrderRec(Nodo nodo) {
        if (nodo != null) {
            mostrarInOrderRec(nodo.isquierdo);
            System.out.println(nodo.ticket);
            mostrarInOrderRec(nodo.derecho);
        }
    }

    //-----------------Mostrar tickets como texto
    public String obtenerTicketsInOrder() {
        StringBuilder sb = new StringBuilder();
        obtenerTicketsInOrderRec(raiz, sb);
        return sb.toString();
    }

    private void obtenerTicketsInOrderRec(Nodo nodo, StringBuilder sb) {
        if (nodo != null) {
            obtenerTicketsInOrderRec(nodo.isquierdo, sb);
            sb.append(nodo.ticket.toString()).append("\n");
            obtenerTicketsInOrderRec(nodo.derecho, sb);
        }
    }
}