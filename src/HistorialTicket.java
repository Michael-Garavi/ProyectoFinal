import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Stack;

public class HistorialTicket {

    private int codigoTicket;
    private Stack<String> historialEstados;
    private ArrayList<Respuesta> respuestas;

    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaPrimeraRespuesta;
    private LocalDateTime fechaCierre;

    public HistorialTicket(Ticket ticket) {
        this.codigoTicket = ticket.getCodigoTicket();
        this.historialEstados = new Stack<>();
        this.respuestas = new ArrayList<>();

        this.fechaCreacion = LocalDateTime.now();
        this.fechaPrimeraRespuesta = null;
        this.fechaCierre = null;

        registrarEstado(ticket.getEstado());
    }

    public int getCodigoTicket() {
        return codigoTicket;
    }

    public void registrarEstado(String estado) {
        historialEstados.push(estado);
    }

    public String verHistorialEstados() {
        String texto = "HISTORIAL DE ESTADOS\n";
        texto += "Ticket: " + codigoTicket + "\n\n";

        if (historialEstados.isEmpty()) {
            texto += "No existen estados registrados.\n";
        } else {
            for (int i = 0; i < historialEstados.size(); i++) {
                texto += (i + 1) + ". " + historialEstados.get(i) + "\n";
            }
        }

        return texto;
    }

    public void agregarRespuesta(String mensaje, String autor) {
        int id = respuestas.size() + 1;

        Respuesta respuesta = new Respuesta(id, mensaje, autor);
        respuestas.add(respuesta);

        registrarFechaPrimeraRespuesta(respuesta.getFecha());
        registrarEstado("ATENDIDO");
    }

    public String verRespuestasTicket() {
        String texto = "RESPUESTAS DEL TICKET\n";
        texto += "Ticket: " + codigoTicket + "\n\n";

        if (respuestas.isEmpty()) {
            texto += "No existen respuestas registradas\n";
        } else {
            for (Respuesta r : respuestas) {
                texto += r.toString() + "\n";
            }
        }

        return texto;
    }

    public void registrarFechaPrimeraRespuesta(LocalDateTime fecha) {
        if (fechaPrimeraRespuesta == null) {
            fechaPrimeraRespuesta = fecha;
        }
    }

    public void cerrarTicket() {
        fechaCierre = LocalDateTime.now();
        registrarEstado("CERRADO");
    }

    public long calcularTiempoResolucionMinutos() {
        if (fechaCreacion == null || fechaCierre == null) {
            return 0;
        }

        return Duration.between(fechaCreacion, fechaCierre).toMinutes();
    }

    public String obtenerSeguimientoCompleto() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        String texto = "SEGUIMIENTO COMPLETO\n";
        texto += "Ticket: " + codigoTicket + "\n\n";

        texto += "Fecha de creacion: " + fechaCreacion.format(formato) + "\n";

        if (fechaPrimeraRespuesta != null) {
            texto += "Fecha de primera respuesta: " + fechaPrimeraRespuesta.format(formato) + "\n";
        } else {
            texto += "Fecha de primera respuesta: Sin registrar\n";
        }

        if (fechaCierre != null) {
            texto += "Fecha de cierre: " + fechaCierre.format(formato) + "\n";
            texto += "Tiempo de resolucion: " + calcularTiempoResolucionMinutos() + " minutos\n";
        } else {
            texto += "Fecha de cierre: Ticket aún no cerrado\n";
            texto += "Tiempo de resolucion: No disponible\n";
        }

        texto += "\n";
        texto += verHistorialEstados();
        texto += "\n";
        texto += verRespuestasTicket();

        return texto;
    }
}