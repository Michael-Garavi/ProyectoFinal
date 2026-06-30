import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Respuesta {

    private int idRespuesta;
    private String mensaje;
    private String autor;
    private LocalDateTime fecha;

    public Respuesta(int idRespuesta, String mensaje, String autor) {
        this.idRespuesta = idRespuesta;
        this.mensaje = mensaje;
        this.autor = autor;
        this.fecha = LocalDateTime.now();
    }

    public int getIdRespuesta() {
        return idRespuesta;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getAutor() {
        return autor;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return "Respuesta #" + idRespuesta + "\n"
                + "Autor: " + autor + "\n"
                + "Fecha: " + fecha.format(formato) + "\n"
                + "Mensaje: " + mensaje + "\n";
    }
}