public class Usuario {
    private int idUsuario;
    private String nombre;
    private String usuario;
    private String contraseña;
    private String rol;

    public Usuario(int idUsuario, String nombre, String usuario, String contraseña, String rol) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.usuario = usuario;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getRol() {
        return rol;
    }
}