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

    public JPanel getPanelPrincipal() {
        return panelPrincipal;
    }

    public ClasificacionForm() {

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
        try {
            String tipo = cmbTipoSoporte.getSelectedItem().toString();

            Clasificacion clasificacion = gestor.clasificarTicket(tipo);
            String area = gestor.asignarAreaResponsable(tipo);

            txtResumen.setText("");
            txtResumen.append("RESUMEN DE CLASIFICACIÓN\n");
            txtResumen.append("Tipo de soporte: " + clasificacion.getTipoSoporte() + "\n");
            txtResumen.append("Prioridad asignada: " + clasificacion.getPrioridad() + "\n");
            txtResumen.append("Área responsable: " + area + "\n");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null,
                    ex.getMessage(),
                    "Validación",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

    private void limpiar() {
        cmbTipoSoporte.setSelectedIndex(0);
        txtResumen.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Clasificación y Priorización");
        frame.setContentPane(new ClasificacionForm().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}