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
                JOptionPane.showMessageDialog(null, "Funcionalidad pendiente: Ver Tickets Técnicos");
            }
        });

        btnResponderTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "Funcionalidad pendiente: Responder Ticket");
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

    public static void abrir(Usuario usuarioLogueado) {
        JFrame frame = new JFrame("Panel Soporte Técnico - URBE RED");
        frame.setContentPane(new TecnicoForm(usuarioLogueado).panelPrincipal3);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}