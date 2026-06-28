import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class VentanaTickets {
    private JPanel VentanaTickets;
    private JTextField txtDescripcionTik;
    private JTextField txtEstadoTik;
    private JButton btnCreart;
    private JButton btnBuscart;
    private JButton btnMostrar;
    private JButton btnEliminar;
    private JSpinner spinnerCodigo;
    private JTextArea txtArea;
    private JButton btnEditar;

    private GestionTicket gestion = new GestionTicket();

    private ArbolTickets arbol = new ArbolTickets();




    public VentanaTickets (){
        arbol = new ArbolTickets();

        spinnerCodigo.setModel(new SpinnerNumberModel(1, 1, 1000, 1));
        JSpinner.NumberEditor editor = new JSpinner.NumberEditor(spinnerCodigo, "0000");
        spinnerCodigo.setEditor(editor);
        /// ------- CrearTicket
        btnCreart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    txtArea.setText("");

                    int codigo = (int) spinnerCodigo.getValue();
                    String descripcion = txtDescripcionTik.getText().trim();
                    String estado = txtEstadoTik.getText().trim();
                    if (descripcion.isEmpty() || estado.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "⚠️ Todos los campos deben estar llenos");
                        return;
                    }

                    if (!estado.equalsIgnoreCase("Pendiente") && !estado.equalsIgnoreCase("Resuelto")) {

                        JOptionPane.showMessageDialog(null, "⚠️ El estado debe ser 'Pendiente' o 'Resuelto'");
                        return;
                    }

                    if (gestion.buscarTiket(codigo) != null) {
                        JOptionPane.showMessageDialog(null,"⚠️ Ya existe un ticket con ese código");
                        return;
                    }

                    Ticket t = new Ticket(codigo, descripcion, estado);
                    gestion.CrearTicket(t);
                    arbol.insertar(t);

                    JOptionPane.showMessageDialog(null,"✅ Se ha creado un ticket correctamente " +codigo);

                }catch (Exception ex){
                    txtArea.setText(ex.getMessage());
                }
            }
        });
        /// ---------Buscar-----------
        btnBuscart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = (int) spinnerCodigo.getValue();
                Ticket t = gestion.buscarTiket(codigo);
                if (t != null) {

                    txtArea.setText(arbol.buscar(codigo).toString());
                } else {
                    txtArea.setText("Ticket no encontrado");
                }
            }
        });

        /// ----------------mostrar

        btnMostrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String tickets = arbol.obtenerTicketsInOrder();

                if (tickets.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No hay tickets");
                } else {
                    txtArea.setText(gestion.obtenerTicketsInOrder());
                }

            }
        });

        /// -------------Eliminar
        btnEliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int codigo = (int) spinnerCodigo.getValue();

                boolean eliminado = gestion.eliminarTicketPendiente(codigo);

                if (eliminado) {
                    txtArea.setText(gestion.obtenerTicketsInOrder());
                    JOptionPane.showMessageDialog(null, "Ticket eliminado correctamente");
                } else {
                    JOptionPane.showMessageDialog(null, "No se encontró un ticket pendiente con ese código");
                }

            }
        });


        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    txtArea.setText("");

                    int codigo = (int) spinnerCodigo.getValue();
                    String descripcion = txtDescripcionTik.getText().trim();
                    String estado = txtEstadoTik.getText().trim();

                    if (descripcion.isEmpty() || estado.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "⚠️ Todos los campos deben estar llenos");
                        return;
                    }
                    if (!estado.equalsIgnoreCase("Pendiente") && !estado.equalsIgnoreCase("Resuelto")) {

                        JOptionPane.showMessageDialog(null, "⚠️ El estado debe ser 'Pendiente' o 'Resuelto'");
                    }

                    boolean editado = gestion.editarTicket(codigo, descripcion, estado);

                    if (editado) {
                        JOptionPane.showMessageDialog(null,"✅ Ticket " +codigo + " actualizado correctamente" );
                    } else{
                        JOptionPane.showMessageDialog(null,"❌ Ticket no encontrado");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("VentanaTickets");
        frame.setContentPane(new VentanaTickets().VentanaTickets);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
