import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OperativoForm {
    private JPanel panelPrincipal4;
    private JLabel lblTitulo;
    private JLabel lblBienvenida;
    private JButton btnVerTickets;
    private JButton btnResponderTicket;
    private JButton btnCerrarSesion;
    private JButton brnGrafo;

    private GestionTicket gestion;
    private Usuario usuarioLogueado;
    private ModuloHistorialSeguimiento historial;
    private GestionarReportes reportes;


    public OperativoForm(Usuario usuarioLogueado, GestionTicket gestion, ModuloHistorialSeguimiento historial, GestionarReportes reportes) {
        this.usuarioLogueado = usuarioLogueado;
        this.gestion = gestion;
        this.historial = historial;
        this.reportes = reportes;



        lblBienvenida.setText("Bienvenido: " + usuarioLogueado.getNombre());

        btnVerTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                HistorialSeguimientoForm.abrir(gestion, historial, usuarioLogueado);
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
        brnGrafo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Grafo.abrir(usuarioLogueado, gestion);
            }
        });
    }


    private void cerrarSesion() {
        LoginForm.abrir();

        JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal4);
        ventanaActual.dispose();
    }

    private void abrirVentanaTickets() {
        VentanaTickets.abrir(usuarioLogueado,gestion,historial,reportes);
    }




    public static void abrir(Usuario usuarioLogueado,
                             GestionTicket gestion,
                             ModuloHistorialSeguimiento historial, GestionarReportes reportes) {

        JFrame frame = new JFrame("Panel Soporte Operativo - URBE RED");

        frame.setContentPane(
                new OperativoForm(
                        usuarioLogueado,
                        gestion,
                        historial,
                        reportes
                ).panelPrincipal4
        );

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}