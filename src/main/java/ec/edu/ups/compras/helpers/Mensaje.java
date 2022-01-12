package ec.edu.ups.compras.helpers;

import java.io.Serializable;

public class Mensaje implements Serializable {
    private int codigo;
    private String mensaje;
    private boolean estado;

    public Mensaje(int codigo, String mensaje, boolean estado) {
        this.codigo = codigo;
        this.mensaje = mensaje;
        this.estado = estado;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
