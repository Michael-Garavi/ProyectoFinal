import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ReportesForm {
    private JPanel Principal;
    private JTextArea txtListar;
    private JButton btnPrioridad;
    private JButton btnTipo;
    private JButton btnCerrados;
    private JLabel lblTitulo;
    private JButton btnRegresar;

    private Usuario usuarioLogueado;
    private GestionTicket gestion;
    private GestionarReportes reportes;
    private ModuloHistorialSeguimiento historial;

    public ReportesForm(Usuario usuarioLogueado, GestionTicket gestion, GestionarReportes reportes, ModuloHistorialSeguimiento historial) {
        this.usuarioLogueado = usuarioLogueado;
        this.gestion = gestion;
        this.reportes = new GestionarReportes(gestion);
        this.historial = historial;

        txtListar.setEditable(false);
        lblTitulo.setText("Reportes; " + usuarioLogueado.getNombre());

        btnPrioridad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtListar.setText(reportes.reportePrioridad());
            }
        });
        btnTipo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtListar.setText(reportes.reporteTipo());
            }
        });
        btnCerrados.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtListar.setText(reportes.reporteCerrados());
            }
        });
        btnRegresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TecnicoForm.abrir(usuarioLogueado,gestion,historial,reportes);
            }
        });
    }
    public static void abrir(Usuario usuarioLogueado, GestionTicket gestion, GestionarReportes reportes, ModuloHistorialSeguimiento historial) {
        JFrame frame = new JFrame("Reportes - URBE RED");
        frame.setContentPane(new ReportesForm(usuarioLogueado, gestion, reportes, historial).Principal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
