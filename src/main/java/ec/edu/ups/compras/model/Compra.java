package ec.edu.ups.compras.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "compras")
public class Compra implements Serializable {

    @Id
    private int id;


    private static final long serialVersionUID = 1L;
}
