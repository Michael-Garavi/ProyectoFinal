public class Ticket {
    private int CodigoTicket;
    private String Descripcion;
    private String Estado;

    public Ticket(int codigoTicket, String descripcion, String estado) {
        this.CodigoTicket = codigoTicket;
        this.Descripcion = descripcion;
        this.Estado = estado;
    }

    public int getCodigoTicket() {
        return CodigoTicket;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getEstado() {
        return Estado;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public void setEstado(String estado) {
        Estado = estado;
    }

    @Override
    public String toString() {
        return "Ticket: " +
                "CodigoTicket: " + CodigoTicket + '\'' +
                ", Descripcion: " + Descripcion + '\'' +
                ", Estado: " + Estado + '\'' + "\n";
    }
}
