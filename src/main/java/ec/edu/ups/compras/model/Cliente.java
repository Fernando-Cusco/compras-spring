package ec.edu.ups.compras.model;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "clientes")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

//    @NotBlank(message = "Nombres es obligatorio")
    private String nombres;

//    @NotBlank(message = "Apellido es obligatorio")
    private String apellidos;

//    @Column(unique = true, length = 10)
//    @Size(min = 10, max = 10)
//    @NotBlank(message = "Cédula es obligatoria")
    private String cedula;

    @Column(name = "create_at")
    @JsonIgnore
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonIgnore
    private Date updatedAt;

    private String direccion;

    @OneToOne(mappedBy = "cliente")
    @JsonIgnore
    private Usuario usuario;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Compra> compras;

    @OneToMany(cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Favorito> favoritos;

    @PrePersist
    public void init() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }


    public List<Favorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<Favorito> favoritos) {
        this.favoritos = favoritos;
    }

    public List<Compra> getCompras() {
        return compras;
    }

    public void setCompras(List<Compra> compras) {
        this.compras = compras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    private static final long serialVersionUID = 1L;

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
