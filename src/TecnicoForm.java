import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TecnicoForm {
    private JPanel panelPrincipal3;
    private JLabel lblTitulo;
    private JLabel lblBienvenida;
    private JButton btnVerTickets;
    private JButton btnResponderTicket;
    private JButton btnCerrarSesion;
    private JButton btnReportes;

    private Usuario usuarioLogueado;
    private GestionTicket gestion;
    private ModuloHistorialSeguimiento historial;
    private GestionarReportes reportes;

    public TecnicoForm(Usuario usuarioLogueado , GestionTicket gestion, ModuloHistorialSeguimiento historial, GestionarReportes reportes) {
        this.usuarioLogueado = usuarioLogueado;
        this.gestion = gestion;
        this.historial = historial;
        this.reportes = reportes;

        lblBienvenida.setText("Bienvenido: " + usuarioLogueado.getNombre());

        btnVerTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ClasificacionForm.abrir(gestion);
            }
        });

        btnResponderTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirVentanaTickets();
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
        btnReportes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ReportesForm.abrir(usuarioLogueado, gestion, reportes, historial);
            }
        });
    }

    private void cerrarSesion() {
        LoginForm.abrir();

        JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal3);
        ventanaActual.dispose();
    }
    private void abrirVentanaTickets() {
        VentanaTickets.abrir(usuarioLogueado,gestion,historial,reportes);
    }


    public static void abrir(Usuario usuarioLogueado, GestionTicket gestion, ModuloHistorialSeguimiento historial, GestionarReportes reportes) {
        JFrame frame = new JFrame("Panel Soporte Técnico - URBE RED");
        frame.setContentPane(new TecnicoForm(usuarioLogueado, gestion, historial, reportes).panelPrincipal3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}