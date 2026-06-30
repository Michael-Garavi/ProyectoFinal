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

    private GestionTicket gestion;
    private Usuario usuarioLogueado;
    private ModuloHistorialSeguimiento historial;


    public OperativoForm(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;

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
    }


    private void cerrarSesion() {
        LoginForm.abrir();

        JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal4);
        ventanaActual.dispose();
    }

    private void abrirVentanaTickets() {
        VentanaTickets.abrir(usuarioLogueado);
    }




    public static void abrir(Usuario usuarioLogueado) {
        JFrame frame = new JFrame("Panel Soporte Operativo - URBE RED");
        frame.setContentPane(new OperativoForm(usuarioLogueado).panelPrincipal4);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}