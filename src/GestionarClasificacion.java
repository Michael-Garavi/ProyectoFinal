public class GestionarClasificacion {

    public String asignarPrioridad(String tipoSoporte) {
        if (tipoSoporte.equals("TECNICO")) {
            return "ALTA";
        } else if (tipoSoporte.equals("OPERATIVO")) {
            return "MEDIA";
        } else if (tipoSoporte.equals("CONOCIMIENTO")) {
            return "BAJA";
        } else {
            return "";
        }
    }

    public String asignarAreaResponsable(String tipoSoporte) {
        if (tipoSoporte.equals("TECNICO")) {
            return "TECNICO";
        } else if (tipoSoporte.equals("OPERATIVO")) {
            return "OPERATIVO";
        } else if (tipoSoporte.equals("CONOCIMIENTO")) {
            return "OPERATIVO";
        } else {
            return "";
        }
    }

    public boolean validarTipoSoporte(String tipoSoporte) {
        return tipoSoporte.equals("TECNICO")
                || tipoSoporte.equals("OPERATIVO")
                || tipoSoporte.equals("CONOCIMIENTO");
    }

    public Clasificacion clasificarTicket(String tipoSoporte) throws Exception {
        if (tipoSoporte == null || tipoSoporte.equals("Seleccione")) {
            throw new Exception("Debe seleccionar un tipo de soporte.");
        }

        if (!validarTipoSoporte(tipoSoporte)) {
            throw new Exception("Tipo de soporte no válido.");
        }

        String prioridad = asignarPrioridad(tipoSoporte);

        return new Clasificacion(tipoSoporte, prioridad);
    }
}