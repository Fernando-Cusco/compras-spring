package ec.edu.ups.compras.repository.compra;

import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.model.Compra;
import ec.edu.ups.compras.model.DetalleCompra;
import ec.edu.ups.compras.model.EstadoCompra;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Integer> {

    public Iterable<Compra> findCompraByCliente(Cliente cliente);

    // buscar una campra por el id del cliente y si esta en carrito
    public Compra findCompraByClienteAndEstadoCompra(Cliente cliente, EstadoCompra estadoCompra);

    // buscar todas las compras por cedula del cliente
    public List<Compra> findAllByClienteAndEstadoCompra(Cliente cliente, EstadoCompra estadoCompra);

    // actualizar el carrito de compras
    @Transactional
    @Modifying
    @Query("update Compra c set c.detallesCompra = :detallesCompra, c.total = :total where c.id = :id")
    public void updateCompra(@Param("id") int id, @Param("detallesCompra") List<DetalleCompra> detallesCompra, @Param("total") double total);
}


//    @Query("update Cliente u set u.nombres = :nombres, u.apellidos = :apellidos, u.cedula = :cedula, u.direccion = :direccion where u.id = :id")
//    void updateCliente(@Param(value = "id") int id, @Param(value = "nombres") String nombres, @Param(value = "apellidos") String apellidos, @Param(value = "cedula") String cedula, @Param(value = "direccion") String direccion);