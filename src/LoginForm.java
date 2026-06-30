import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginForm {
     JPanel panelPrincipal;
    private JLabel lblTitulo;
    private JLabel lblUsuario;
    private JTextField txtUsuario;
    private JLabel lblContraseña;
    private JPasswordField txtContraseña;
    private JButton btnIngresar;

    GestionarUsuarios gestorUsuarios = new GestionarUsuarios();
    GestionTicket gestion = new GestionTicket();
    ModuloHistorialSeguimiento historial = new ModuloHistorialSeguimiento();
    GestionarReportes reportes = new GestionarReportes(gestion);

    public LoginForm() {
        btnIngresar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        txtUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });

        txtContraseña.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                iniciarSesion();
            }
        });
    }

    private void iniciarSesion() {
        String usuario = txtUsuario.getText().trim();
        String contraseña = new String(txtContraseña.getPassword()).trim();

        if (usuario.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese el usuario.");
            txtUsuario.requestFocus();
            return;
        }

        if (contraseña.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Ingrese la contraseña.");
            txtContraseña.requestFocus();
            return;
        }

        Usuario usuarioLogueado = gestorUsuarios.iniciarSesion(usuario, contraseña);

        if (usuarioLogueado == null) {
            JOptionPane.showMessageDialog(null,
                    "Usuario o contraseña incorrectos.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtContraseña.setText("");
            return;
        }

        abrirVentanaPorRol(usuarioLogueado);
    }

    private void abrirVentanaPorRol(Usuario usuarioLogueado ) {
        String rol = usuarioLogueado.getRol();

        if (rol.equals("CLIENTE")) {
            ClienteForm.abrir(usuarioLogueado,gestion,historial,reportes);

            JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal);
            ventanaActual.dispose();
        } else if (rol.equals("TECNICO")) {
            TecnicoForm.abrir(usuarioLogueado,gestion,historial,reportes);

            JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal);
            ventanaActual.dispose();
        } else if (rol.equals("OPERATIVO")) {
            OperativoForm.abrir( usuarioLogueado,gestion,historial,reportes);

            JFrame ventanaActual = (JFrame) SwingUtilities.getWindowAncestor(panelPrincipal);
            ventanaActual.dispose();
                } else {
            JOptionPane.showMessageDialog(null, "Rol no reconocido.");
        }
    }
    public static void abrir() {
        JFrame frame = new JFrame("Login - URBE RED");
        frame.setContentPane(new LoginForm().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Login - URBE RED");
        frame.setContentPane(new LoginForm().panelPrincipal);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 500);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}