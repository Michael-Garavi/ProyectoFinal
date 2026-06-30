public class Ticket implements Comparable<Ticket> {

    private int CodigoTicket;
    private String cliente;       // NUEVO
    private String Descripcion;
    private String tipoSoporte;   // TECNICO, OPERATIVO, CONOCIMIENTO
    private String prioridad;     // ALTA, MEDIA, BAJA
    private String Estado;

    public Ticket(int codigoTicket, String cliente, String descripcion, String tipoSoporte, String prioridad, String estado) {
        this.CodigoTicket = codigoTicket;
        this.cliente = cliente;
        this.Descripcion = descripcion;
        this.tipoSoporte = tipoSoporte;
        this.prioridad = prioridad;
        this.Estado = estado;
    }

    public int getCodigoTicket() {
        return CodigoTicket;
    }

    public String getCliente() {
        return cliente;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getTipoSoporte() {
        return tipoSoporte;
    }

    public String getPrioridad() {
        return prioridad;
    }

    public String getEstado() {
        return Estado;
    }

    // Setter
    public void setEstado(String Estado) {
        this.Estado = Estado;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    @Override
    public int compareTo(Ticket otro) {
        return getPeso(this.prioridad) - getPeso(otro.prioridad);
    }

    private int getPeso(String p) {
        switch (p) {
            case "ALTA": return 1;
            case "MEDIA": return 2;
            default: return 3;
        }
    }

    @Override
    public String toString() {
        return "♦ Ticket= " +
                "Código: " + CodigoTicket + '-' +
                " Cliente: " + cliente + '-' +
                " Descripción: " + Descripcion + '-' +
                " Tipo soporte: " + tipoSoporte + '-' +
                " Prioridad: " + prioridad + '-' +
                " Estado: " + Estado + "\n";
    }
}
