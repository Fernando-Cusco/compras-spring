package ec.edu.ups.compras.repository;

import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.model.Compra;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompraRepository extends CrudRepository<Compra, Integer> {

    public Iterable<Compra> findCompraByCliente(Cliente cliente);
}
