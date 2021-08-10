package ec.edu.ups.compras.repository.cliente;

import ec.edu.ups.compras.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    Cliente findClienteByCedula(String cedula);
    Cliente findClienteById(int id);


}