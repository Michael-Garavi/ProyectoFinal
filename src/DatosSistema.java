public class DatosSistema {

    public static GestionTicket gestionarTickets = new GestionTicket();

    public static ModuloHistorialSeguimiento moduloHistorial =
            new ModuloHistorialSeguimiento();

    public static GrafoTickets grafoTickets = new GrafoTickets();

    public static GestionarReportes gestionarReportes =
            new GestionarReportes(gestionarTickets);
}