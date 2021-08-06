package ec.edu.ups.compras.repository;

import ec.edu.ups.compras.model.Cliente;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    Cliente findClienteByCedula(String cedula);
    Cliente findClienteByCorreo(String correo);
    Cliente findClienteById(int id);


}
