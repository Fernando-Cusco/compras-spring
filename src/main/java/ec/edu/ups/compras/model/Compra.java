package ec.edu.ups.compras.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "compras")
public class Compra implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private Date fecha;

    private String observaciones;

    @ManyToOne(cascade = CascadeType.ALL)
    public Cliente cliente;

    @Enumerated(EnumType.STRING)
    private EstadoCompra estadoCompra;

    @OneToMany(cascade = CascadeType.ALL)
    private List<DetalleCompra> detallesCompra;

    private double total;

    public double calcularTotal() {
        double total = 0.0;
        for(int i =0; i < detallesCompra.size(); i++) {
            total += detallesCompra.get(i).calcularSubtotal();
        }
        return total;
    }

    @PrePersist
    public void data() {
        fecha = new Date();
        setTotal(calcularTotal());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public EstadoCompra getEstadoCompra() {
        return estadoCompra;
    }

    public void setEstadoCompra(EstadoCompra estadoCompra) {
        this.estadoCompra = estadoCompra;
    }

    public List<DetalleCompra> getDetallesCompra() {
        return detallesCompra;
    }

    public void setDetallesCompra(List<DetalleCompra> detallesCompra) {
        this.detallesCompra = detallesCompra;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    private static final long serialVersionUID = 1L;
}
