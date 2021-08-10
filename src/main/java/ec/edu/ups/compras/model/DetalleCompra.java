package ec.edu.ups.compras.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "detalles")
public class DetalleCompra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private int cantidad;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "producto_id")
    private Producto producto;

    private double subtotal;

    @PrePersist
    public void data() {
        setSubtotal(calcularSubtotal());
    }

    public double calcularSubtotal() {
        return producto.getPrecioFinal() * cantidad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }
}

