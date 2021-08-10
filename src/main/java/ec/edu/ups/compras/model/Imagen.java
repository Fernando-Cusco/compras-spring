package ec.edu.ups.compras.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "imagenes")
public class Imagen implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String path;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
