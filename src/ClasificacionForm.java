import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClasificacionForm {
    private JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblTipoSoporte;
    private JComboBox cmbTipoSoporte;
    private JButton btnClasificar;
    private JTextArea txtResumen;
    private JButton btnLimpiar;

    GestionarClasificacion gestor = new GestionarClasificacion();
    private GestionTicket gestion;


    public ClasificacionForm(GestionTicket gestion) {
        this.gestion = gestion;

        txtResumen.setEditable(false);

        btnClasificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clasificar();
            }
        });

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                limpiar();
            }
        });
    }

    private void clasificar() {

        String tipoSeleccionado =
                cmbTipoSoporte.getSelectedItem().toString();

        StringBuilder resultado = new StringBuilder();

        resultado.append("TICKETS CLASIFICADOS\n\n");

        for (Ticket t : gestion.obtenerTodos()) {

            if (t.getTipoSoporte().equalsIgnoreCase(tipoSeleccionado)) {

                resultado.append("Código: ")
                        .append(t.getCodigoTicket())
                        .append("\n");

                resultado.append("Cliente: ")
                        .append(t.getCliente())
                        .append("\n");

                resultado.append("Tipo: ")
                        .append(t.getTipoSoporte())
                        .append("\n");

                resultado.append("---------------------\n");
            }
        }

        txtResumen.setText(resultado.toString());
    }

    private void limpiar() {
        cmbTipoSoporte.setSelectedIndex(0);
        txtResumen.setText("");
    }

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }



    public static void abrir(GestionTicket gestion) {

        JFrame frame = new JFrame("Clasificación y Priorización");

        frame.setContentPane(
                new ClasificacionForm(gestion).panelPrincipal
        );

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}