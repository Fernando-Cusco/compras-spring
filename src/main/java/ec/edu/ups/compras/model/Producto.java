package ec.edu.ups.compras.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "productos")
public class Producto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotBlank(message = "Nombre es obligatorio")
    private String nombre;

    private String descripcion;

    @Column(name = "precio_inicial")
    @JsonProperty("precio_inicial")
    @NotNull(message = "Precio inicial es obligatorio")
    private double precioInicial;

    @Column(name = "descuento_porcentaje")
    @JsonProperty("descuento_porcentaje")
    private int descuentoPorcentaje;

    @Column(name = "valor_descuento")
    @JsonProperty("valor_descuento")
    private double valorDescuento;

    @NotNull(message = "Precio final es obligatorio")
    @Column(name = "precio_final")
    @JsonProperty("precio_final")
    private double precioFinal;

    private boolean estado;

    @NotNull(message = "Stock es obligatorio")
    private int stock;

    private boolean promocion;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Caracteristica> caracteristicas;

    @Column(name = "create_at")
    @JsonIgnore
    private Date createdAt;

    @Column(name = "updated_at")
    @JsonIgnore
    private Date updatedAt;

    @PrePersist
    public void init() {
        this.createdAt = new Date();
        this.updatedAt = new Date();
    }

    public void agregarCaracteristica(Caracteristica caracteristica) {
        if (caracteristicas == null) {
            caracteristicas = new ArrayList<>();
        }
        caracteristicas.add(caracteristica);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecioInicial() {
        return precioInicial;
    }

    public void setPrecioInicial(double precioInicial) {
        this.precioInicial = precioInicial;
    }

    public int getDescuentoPorcentaje() {
        return descuentoPorcentaje;
    }

    public void setDescuentoPorcentaje(int descuentoPorcentaje) {
        this.descuentoPorcentaje = descuentoPorcentaje;
    }

    public double getValorDescuento() {
        return valorDescuento;
    }

    public void setValorDescuento(double valorDescuento) {
        this.valorDescuento = valorDescuento;
    }

    public double getPrecioFinal() {
        return precioFinal;
    }

    public void setPrecioFinal(double precioFinal) {
        this.precioFinal = precioFinal;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public boolean isPromocion() {
        return promocion;
    }

    public void setPromocion(boolean promocion) {
        this.promocion = promocion;
    }

    public List<Caracteristica> getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(List<Caracteristica> caracteristicas) {
        this.caracteristicas = caracteristicas;
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
}
