package ec.edu.ups.compras.utils;

public class Credenciales {
    private String correo;
    private String password;

    public Credenciales(String correo, String password) {
        this.correo = correo;
        this.password = password;
    }
    public Credenciales() {
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
