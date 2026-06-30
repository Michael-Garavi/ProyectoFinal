import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GrafoTickets {

    private HashMap<String, ArrayList<String>> grafo;

    public GrafoTickets() {
        grafo = new HashMap<>();
    }

    public void agregarRelacion(String origen, String destino) {

        if (!grafo.containsKey(origen)) {
            grafo.put(origen, new ArrayList<>());
        }

        grafo.get(origen).add(destino);
    }

    // CORREGIDO: Cliente -> Ticket -> Soporte
    public void construirGrafo(ArrayList<Ticket> tickets) {

        grafo.clear();

        for (Ticket t : tickets) {

            String cliente = t.getCliente();
            String ticket = "Ticket " + t.getCodigoTicket();
            String soporte = t.getTipoSoporte();

            // Cliente -> Ticket
            agregarRelacion(cliente, ticket);

            // Ticket -> Soporte
            agregarRelacion(ticket, soporte);
        }
    }

    public String mostrarGrafo() {

        StringBuilder sb = new StringBuilder();

        sb.append("=========== GRAFO ==========\n\n");

        if (grafo.isEmpty()) {
            sb.append("No existen relaciones.");
            return sb.toString();
        }

        for (Map.Entry<String, ArrayList<String>> nodo : grafo.entrySet()) {

            sb.append(nodo.getKey()).append("\n");

            for (String destino : nodo.getValue()) {

                sb.append("   |\n");
                sb.append("   ---> ").append(destino).append("\n");
            }

            sb.append("\n");
        }

        return sb.toString();
    }
}