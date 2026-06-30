import java.util.ArrayList;

public class GestionarUsuarios {
    private ArrayList<Usuario> usuarios;

    public GestionarUsuarios() {
        usuarios = new ArrayList<>();
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        usuarios.add(new Usuario(1, "Matias Galarza", "cliente1", "123", "CLIENTE"));
        usuarios.add(new Usuario(2, "Juan Administrador", "cliente2", "123", "CLIENTE"));
        usuarios.add(new Usuario(3, "Carlos Técnico", "tecnico1", "123", "TECNICO"));
        usuarios.add(new Usuario(4, "Ana Operativa", "operativo1", "123", "OPERATIVO"));
        usuarios.add(new Usuario(5, "Luis Conocimiento", "operativo2", "123", "OPERATIVO"));
    }

    public Usuario iniciarSesion(String usuarioIngresado, String contraseñaIngresado) {
        for (Usuario u : usuarios) {
            if (u.getUsuario().equals(usuarioIngresado)
                    && u.getContraseña().equals(contraseñaIngresado)) {
                return u;
            }
        }
        return null;
    }

    public String obtenerRol(Usuario usuario) {
        if (usuario != null) {
            return usuario.getRol();
        }
        return "";
    }
}