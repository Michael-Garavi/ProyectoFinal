import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class HistorialSeguimientoForm {

    private JPanel Principal;

    private JTextField CODIGOTextField;
    private JSpinner spinnerCodigo;
    private JTextField txtResultado1;

    private JButton btnVerEstado;
    private JButton btnVerRespuestas;
    private JButton btnAgregarRespuesta;
    private JButton btnTiempoResolucion;
    private JButton btnSeguimientoCompleto;
    private JButton btnLimpiar;

    private JTextArea txtRespuesta;
    private JTextArea txtResultado;
    private JButton btnRTHisto;
    private JButton btnCerrarTicket;
    private JTextField txtRespuesta1;

    private GestionTicket gestion;
    private ModuloHistorialSeguimiento historial;
    private Usuario usuarioLogueado;

    public HistorialSeguimientoForm(GestionTicket gestion,
                                    ModuloHistorialSeguimiento historial,
                                    Usuario usuarioLogueado) {

        this.gestion = gestion;
        if (historial == null) {
            this.historial = new ModuloHistorialSeguimiento();
        } else {
            this.historial = historial;
        }

        this.usuarioLogueado = usuarioLogueado;

        CODIGOTextField.setText("CODIGO");

        spinnerCodigo.setModel(new SpinnerNumberModel(1, 1, 1000, 1));

        btnVerEstado.addActionListener(e -> verEstados());

        btnVerRespuestas.addActionListener(e -> verRespuestas());

        btnAgregarRespuesta.addActionListener(e -> agregarRespuesta());

        btnTiempoResolucion.addActionListener(e -> calcularTiempo());

        btnSeguimientoCompleto.addActionListener(e -> verSeguimientoCompleto());

        btnLimpiar.addActionListener(e -> limpiar());

        btnRTHisto.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registrarTicket();
            }
        });

        btnCerrarTicket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cerrarTicket();
            }
        });
    }

    private void registrarTicket() {
        int codigo = (int) spinnerCodigo.getValue();

        try {
            if (gestion == null) {
                JOptionPane.showMessageDialog(null, "Error: gestion está en null");
                return;
            }

            if (historial == null) {
                JOptionPane.showMessageDialog(null, "Error: historial está en null");
                return;
            }

            Ticket ticket = gestion.buscarTiket(codigo);

            if (ticket == null) {
                JOptionPane.showMessageDialog(
                        null,
                        "No se encontró ningún ticket con el código: " + codigo +
                                "\nTickets registrados actualmente: " + gestion.cantidadTickets()
                );
                return;
            }

            historial.registrarTicket(ticket);

            JOptionPane.showMessageDialog(null, "Ticket registrado en historial");
            txtResultado.setText(historial.obtenerSeguimientoCompleto(codigo));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al registrar ticket: " + ex.getMessage());
        }
    }

    private boolean validarHistorial(int codigo) {
        if (historial == null) {
            JOptionPane.showMessageDialog(null, "El módulo de historial no está inicializado");
            return false;
        }

        if (gestion == null) {
            JOptionPane.showMessageDialog(null, "El módulo de gestión de tickets no está inicializado");
            return false;
        }

        if (!historial.existeHistorial(codigo)) {
            try {
                Ticket ticket = gestion.buscarTiket(codigo);

                if (ticket == null) {
                    JOptionPane.showMessageDialog(null, "Primero debe registrar el ticket");
                    return false;
                }

                historial.registrarTicket(ticket);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Primero debe registrar el ticket");
                return false;
            }
        }

        return true;
    }

    private void verEstados() {
        int codigo = (int) spinnerCodigo.getValue();

        if (!validarHistorial(codigo)) {
            return;
        }

        txtResultado.setText(historial.verHistorialEstados(codigo));
    }

    private void verRespuestas() {
        int codigo = (int) spinnerCodigo.getValue();

        if (!validarHistorial(codigo)) {
            return;
        }

        txtResultado.setText(historial.verRespuestasTicket(codigo));
    }

    private void agregarRespuesta() {
        int codigo = (int) spinnerCodigo.getValue();
        String mensaje = txtRespuesta.getText();
        String respuesta = txtRespuesta1.getText();
        String resultado = txtResultado1.getText();

        String mensajeCompleto = "Mensaje: " + mensaje + "\nRespuesta: " + respuesta + "\nResultado: " + resultado;

        if (!validarHistorial(codigo)) {
            return;
        }

        if (mensaje.trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese una respuesta");
            return;
        }

        String autor = "Usuario";

        if (usuarioLogueado != null) {
            autor = usuarioLogueado.getNombre();
        }

        try {
            historial.agregarRespuesta(codigo, mensajeCompleto, autor);

            Ticket ticket = gestion.buscarTiket(codigo);
            ticket.setEstado("ATENDIDO");

            JOptionPane.showMessageDialog(null, "Respuesta agregada correctamente");

            txtRespuesta.setText("");
            txtResultado.setText(historial.obtenerSeguimientoCompleto(codigo));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void cerrarTicket() {
        int codigo = (int) spinnerCodigo.getValue();

        if (!validarHistorial(codigo)) {
            return;
        }

        try {
            historial.cerrarTicket(codigo);

            Ticket ticket = gestion.buscarTiket(codigo);
            ticket.setEstado("CERRADO");

            JOptionPane.showMessageDialog(null, "Ticket cerrado");

            txtResultado.setText(historial.obtenerSeguimientoCompleto(codigo));

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

    private void calcularTiempo() {
        int codigo = (int) spinnerCodigo.getValue();

        if (!validarHistorial(codigo)) {
            return;
        }

        long minutos = historial.calcularTiempoResolucionMinutos(codigo);

        if (minutos == 0) {
            txtResultado.setText("El ticket todavía no ha sido cerrado");
        } else {
            txtResultado.setText("Tiempo de resolución: " + minutos + " minutos");
        }
    }

    private void verSeguimientoCompleto() {
        int codigo = (int) spinnerCodigo.getValue();

        if (!validarHistorial(codigo)) {
            return;
        }

        txtResultado.setText(historial.obtenerSeguimientoCompleto(codigo));
    }

    private void limpiar() {
        spinnerCodigo.setValue(1);
        txtRespuesta.setText("");
        txtResultado.setText("");
    }

    public static void abrir(GestionTicket gestion,
                             ModuloHistorialSeguimiento historial,
                             Usuario usuarioLogueado) {

        JFrame frame = new JFrame("Historial y Seguimiento");

        frame.setContentPane(new HistorialSeguimientoForm(
                gestion,
                historial,
                usuarioLogueado
        ).Principal);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(1980, 1800);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}