import java.util.ArrayList;

public class GestionTicket {
    private ArrayList<Ticket> Listatickets= new ArrayList<>();
    private ArbolTickets arbol = new ArbolTickets();


    //---------Crear Tickets------
    public void CrearTicket(Ticket ticket){
        Listatickets.add(ticket);
    }
    //---------Buscar ticket------
    public Ticket buscarTiket(int codigo) {
        for (Ticket t : Listatickets) {
            if (t.getCodigoTicket() == codigo) {
                return t;
            }
        }
        return null;
    }
    //-------Mostrar Detalle de ticket
    public void mostrarTickets(int codigo){
        Ticket t =  buscarTiket(codigo);
        if (t != null) {
            System.out.println(t);
        } else {
            System.out.println("Ticket no encontrado");
        }
    }
    //-----Eliminar ticketPendiente------------
    public boolean eliminarTicketPendiente(int codigo) {
        Ticket ticket = arbol.buscar(codigo);

        if (ticket == null
                || ticket.getEstado() == null
                || !ticket.getEstado().trim().equalsIgnoreCase("Pendiente")) {
            return false;
        }

        boolean eliminado = Listatickets.removeIf(t ->
                t.getCodigoTicket() == codigo &&
                        t.getEstado() != null &&
                        t.getEstado().trim().equalsIgnoreCase("Pendiente")
        );

        if (eliminado) {
            arbol.eliminar(codigo);
        }

        return eliminado;
    }

    public String obtenerTicketsInOrder() {
        return arbol.obtenerTicketsInOrder();
    }

    public Ticket buscarTicket(int codigo) {
        return arbol.buscar(codigo);
    }


    public boolean editarTicket(int codigo, String nuevaDescripcion, String nuevoEstado) {
        for (Ticket t : Listatickets) {
            if (t.getCodigoTicket() == codigo) {
                t.setDescripcion(nuevaDescripcion);
                t.setEstado(nuevoEstado);
                return true;
            }
        }
        return false;
    }

}
