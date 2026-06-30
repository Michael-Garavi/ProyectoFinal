import java.util.ArrayList;

public class ModuloHistorialSeguimiento {

    private ArrayList<HistorialTicket> historiales;

    public ModuloHistorialSeguimiento() {
        historiales = new ArrayList<>();
    }

    public void registrarTicket(Ticket ticket) throws Exception {
        if (ticket == null) {
            throw new Exception("El ticket no existe");
        }

        if (buscarHistorial(ticket.getCodigoTicket()) == null) {
            HistorialTicket historial = new HistorialTicket(ticket);
            historiales.add(historial);
        }
    }


    public HistorialTicket buscarHistorial(int codigoTicket) {
        for (HistorialTicket h : historiales) {
            if (h.getCodigoTicket() == codigoTicket) {
                return h;
            }
        }

        return null;
    }

    public boolean existeHistorial(int codigoTicket) {
        return buscarHistorial(codigoTicket) != null;
    }

    public String verHistorialEstados(int codigoTicket) {
        HistorialTicket historial = buscarHistorial(codigoTicket);

        if (historial == null) {
            return "Primero debe registrar el ticket en el historial";
        }

        return historial.verHistorialEstados();
    }

    public String verRespuestasTicket(int codigoTicket) {
        HistorialTicket historial = buscarHistorial(codigoTicket);

        if (historial == null) {
            return "Primero debe registrar el ticket en el historial";
        }

        return historial.verRespuestasTicket();
    }

    public void agregarRespuesta(int codigoTicket, String mensaje, String autor) throws Exception {
        HistorialTicket historial = buscarHistorial(codigoTicket);

        if (historial == null) {
            throw new Exception("Primero debe registrar el ticket en el historial");
        }

        historial.agregarRespuesta(mensaje, autor);
    }

    public void cerrarTicket(int codigoTicket) throws Exception {
        HistorialTicket historial = buscarHistorial(codigoTicket);

        if (historial == null) {
            throw new Exception("Primero debe registrar el ticket en el historial");
        }

        historial.cerrarTicket();
    }

    public long calcularTiempoResolucionMinutos(int codigoTicket) {
        HistorialTicket historial = buscarHistorial(codigoTicket);

        if (historial == null) {
            return 0;
        }

        return historial.calcularTiempoResolucionMinutos();
    }

    public String obtenerSeguimientoCompleto(int codigoTicket) {
        HistorialTicket historial = buscarHistorial(codigoTicket);

        if (historial == null) {
            return "Primero debe registrar el ticket en el historial";
        }

        return historial.obtenerSeguimientoCompleto();
    }
}