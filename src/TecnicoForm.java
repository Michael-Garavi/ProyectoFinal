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

    private Usuario usuarioLogueado;

    public TecnicoForm(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;

        lblBienvenida.setText("Bienvenido: " + usuarioLogueado.getNombre());

        btnVerTickets.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("Clasificación de Tickets");
                frame.setContentPane(new ClasificacionForm().getPanelPrincipal());
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.setSize(700, 500);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);

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

        JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal3);
        ventanaActual.dispose();
    }
    private void abrirVentanaTickets() {
        VentanaTickets.abrir(usuarioLogueado);
    }


    public static void abrir(Usuario usuarioLogueado) {
        JFrame frame = new JFrame("Panel Soporte Técnico - URBE RED");
        frame.setContentPane(new TecnicoForm(usuarioLogueado).panelPrincipal3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}