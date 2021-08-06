package ec.edu.ups.compras.repository;

import ec.edu.ups.compras.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends CrudRepository<Producto, Integer> {
    Producto findProductoById(int id);
    Producto findProductoByNombre(String nombre);
}
