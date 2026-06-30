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
    private JComboBox<String> comboBox1;
    private JComboBox<String> comboBox2 ;
    private JButton btnVolver;

    private GestionTicket gestion = new GestionTicket();

    private ArbolTickets arbol = new ArbolTickets();

    private Usuario usuarioLogueado;



    public VentanaTickets (Usuario usuarioLogueado){
        this.usuarioLogueado = usuarioLogueado;
        comboBox1.setModel(new DefaultComboBoxModel<>(new String[]{
                "ALTA",
                "MEDIA",
                "BAJA"
        }));

        comboBox2.setModel(new DefaultComboBoxModel<>(new String[]{
                "TECNICO",
                "SOPORTE",
                "OPERATIVO",
                "CONOCIMIENTO"
        }));



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
                    String cliente = usuarioLogueado.getNombre();
                    String descripcion = txtDescripcionTik.getText();
                    String tipoSoporte = comboBox2.getSelectedItem().toString();
                    String prioridad = comboBox1.getSelectedItem().toString();
                    String estado = "Pendiente";


                    if (descripcion.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "⚠️ Todos los campos deben estar llenos");
                        return;
                    }


                    //txtEstadoTik.invalidate();

                    if (gestion.buscarTiket(codigo) != null) {
                        JOptionPane.showMessageDialog(null, "⚠️ Ya existe un ticket con ese código");
                        return;
                    }



                    cliente = cliente.trim();

                    String respuesta = "";
                    String tecnico = "Sin asignar";

                    Ticket t = new Ticket(codigo, cliente, descripcion, tipoSoporte, prioridad, estado);

                    gestion.CrearTicket(t);
                    arbol.insertar(t);

                    JOptionPane.showMessageDialog(null, "✅ Se ha creado un ticket correctamente " + codigo);

                } catch (Exception ex) {
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
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                volverSegunRol();
            }
        });
    }

    private void volverSegunRol() {
        String rol = usuarioLogueado.getRol();

        if (rol.equalsIgnoreCase("Cliente")) {
            ClienteForm.abrir(usuarioLogueado);

        } else if (rol.equalsIgnoreCase("Tecnico") || rol.equalsIgnoreCase("Técnico")) {
            TecnicoForm.abrir(usuarioLogueado);

        } else if (rol.equalsIgnoreCase("Operador") || rol.equalsIgnoreCase("Operativo")) {
            OperativoForm.abrir(usuarioLogueado);

        } else {
            JOptionPane.showMessageDialog(null, "Rol de usuario no reconocido");
            return;
        }

        JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(btnVolver);
        ventanaActual.dispose();
    }

    public static void abrir(Usuario usuarioLogueado) {
        JFrame frame = new JFrame("VentanaTickets");
        frame.setContentPane(new VentanaTickets(usuarioLogueado).VentanaTickets);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setSize(1200,700);
        frame.setLocationRelativeTo(null);


        frame.setVisible(true);
    }


}
