package ec.edu.ups.compras.repository.cliente;

import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClienteRepository extends CrudRepository<Cliente, Integer> {

    Cliente findClienteByCedula(String cedula);
    Cliente findClienteById(int id);
    @Transactional
    @Modifying
    @Query("update Cliente u set u.nombres = :nombres, u.apellidos = :apellidos, u.cedula = :cedula, u.direccion = :direccion where u.id = :id")
    void updateCliente(@Param(value = "id") int id, @Param(value = "nombres") String nombres, @Param(value = "apellidos") String apellidos,  @Param(value = "cedula") String cedula,  @Param(value = "direccion") String direccion);


}
