import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClienteForm {
    private JPanel panelPrincipal2;
    private JLabel lblTitulo;
    private JLabel lblBienvenida;
    private JButton btnCrearTicket;
    private JButton btnVerHistorial;
    private JButton btnCerrarSesion;
    private JTextArea txtHistoClientAc;

    private Usuario usuarioLogueado;
    private GestionTicket gestion = new GestionTicket();


    public ClienteForm(Usuario usuarioLogueado) {
        this.usuarioLogueado = usuarioLogueado;

        lblBienvenida.setText("Bienvenido: " + usuarioLogueado.getNombre());

        btnCrearTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                abrirVentanaTickets();
            }
        });

        btnVerHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                verHistorialCliente();
            }
        });

        btnCerrarSesion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarSesion();
            }
        });
        btnVerHistorial.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    private void verHistorialCliente() {
        txtHistoClientAc.setText(gestion.obtenerTicketsInOrder());
        txtHistoClientAc.setEditable(false);
    }


    private void abrirVentanaTickets() {
        VentanaTickets.abrir(usuarioLogueado);

        JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal2);
        ventanaActual.dispose();
    }

    private void cerrarSesion() {
        JFrame frame = new JFrame("Login - URBE RED");
        frame.setContentPane(new LoginForm().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal2);
        ventanaActual.dispose();
    }

    public static void abrir(Usuario usuarioLogueado) {
        JFrame frame = new JFrame("Panel Cliente - URBE RED");
        frame.setContentPane(new ClienteForm(usuarioLogueado).panelPrincipal2);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}