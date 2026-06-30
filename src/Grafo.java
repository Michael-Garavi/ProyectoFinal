import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Grafo {

    private JPanel panelPrincipal;
    private JTextArea textArea1;
    private JButton mostrarButton;
    private JButton volverButton;
    private JLabel nom;

    private GestionTicket gestion;
    private Usuario usuarioLogueado;
    private ModuloHistorialSeguimiento historial;
    private GestionarReportes reportes;

    public Grafo(Usuario usuarioLogueado, GestionTicket gestion) {

        this.usuarioLogueado = usuarioLogueado;
        this.gestion = gestion;

        nom.setText("Vista de Grafo: " + usuarioLogueado.getNombre());

        mostrarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                GrafoTickets grafo = new GrafoTickets();

                grafo.construirGrafo(gestion.obtenerTodos());

                textArea1.setText(grafo.mostrarGrafo());
            }
        });
        volverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OperativoForm.abrir(usuarioLogueado, gestion, historial, reportes);
            }
        });
    }

    public static void abrir(
            Usuario usuarioLogueado,
            GestionTicket gestion) {

        JFrame frame = new JFrame("Grafo");

        frame.setContentPane(
                new Grafo(usuarioLogueado, gestion).panelPrincipal
        );

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}

