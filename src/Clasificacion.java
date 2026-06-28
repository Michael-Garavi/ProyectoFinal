public class Clasificacion {
    private String tipoSoporte;
    private String prioridad;

    public Clasificacion(String tipoSoporte, String prioridad) {
        this.tipoSoporte = tipoSoporte;
        this.prioridad = prioridad;
    }

    public String getTipoSoporte() {
        return tipoSoporte;
    }

    public String getPrioridad() {
        return prioridad;
    }

    @Override
    public String toString() {
        return "Tipo de soporte: " + tipoSoporte +
                "\nPrioridad asignada: " + prioridad;
    }
}