import java.util.ArrayList;

public class GestionarReportes {

    private GestionTicket gestionarTickets;

    public GestionarReportes(GestionTicket gestionarTickets) {
        this.gestionarTickets = gestionarTickets;
    }

    // ===============================
    // REPORTE POR PRIORIDAD
    // ===============================
    public String reportePrioridad() {

        int alta = 0;
        int media = 0;
        int baja = 0;

        ArrayList<Ticket> lista = gestionarTickets.obtenerTodos();

        for (Ticket t : lista) {

            switch (t.getPrioridad().toUpperCase()) {

                case "ALTA":
                    alta++;
                    break;

                case "MEDIA":
                    media++;
                    break;

                case "BAJA":
                    baja++;
                    break;
            }
        }

        return "=========== REPORTE POR PRIORIDAD ===========\n\n"
                + "ALTA : " + alta + "\n"
                + "MEDIA: " + media + "\n"
                + "BAJA : " + baja;
    }

    // ===============================
    // REPORTE POR TIPO DE SOPORTE
    // ===============================
    public String reporteTipo() {

        int tecnico = 0;
        int operativo = 0;
        int conocimiento = 0;

        ArrayList<Ticket> lista = gestionarTickets.obtenerTodos();

        for (Ticket t : lista) {

            switch (t.getTipoSoporte().toUpperCase()) {

                case "TECNICO":
                    tecnico++;
                    break;

                case "OPERATIVO":
                    operativo++;
                    break;

                case "CONOCIMIENTO":
                    conocimiento++;
                    break;
            }

        }

        return "=========== REPORTE POR TIPO ===========\n\n"
                + "TECNICO      : " + tecnico + "\n"
                + "OPERATIVO    : " + operativo + "\n"
                + "CONOCIMIENTO : " + conocimiento;

    }

    // ===============================
    // REPORTE DE TICKETS CERRADOS
    // ===============================
    public String reporteCerrados() {

        ArrayList<Ticket> lista = gestionarTickets.obtenerTodos();

        String texto = "=========== TICKETS CERRADOS ===========\n\n";

        boolean encontrado = false;

        for (Ticket t : lista) {

            if (t.getEstado().equalsIgnoreCase("ATENDIDO")
                    || t.getEstado().equalsIgnoreCase("CERRADO")) {

                encontrado = true;

                texto += "Código: " + t.getCodigoTicket()
                        + "\nTipo: " + t.getTipoSoporte()
                        + "\nPrioridad: " + t.getPrioridad()
                        + "\nEstado: " + t.getEstado()
                        + "\n---------------------------------\n";

            }

        }

        if (!encontrado) {

            texto += "No existen tickets cerrados.";

        }

        return texto;

    }

}