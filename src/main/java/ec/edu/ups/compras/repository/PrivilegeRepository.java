package ec.edu.ups.compras.repository;

import ec.edu.ups.compras.model.Privilege;
import ec.edu.ups.compras.model.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrivilegeRepository extends CrudRepository<Privilege, Integer> {

    public Privilege findByName(String name);
}
