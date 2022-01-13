package ec.edu.ups.compras.repository.favorito;

import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.model.Favorito;
import ec.edu.ups.compras.model.Producto;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IFavoritoRepository extends CrudRepository<Favorito, Integer> {

    public Favorito findByClienteAndProducto(Cliente cliente, Producto producto);

    public List<Favorito> findByClienteId(int id);

    public List<Favorito> findByProductoId(int id);

}
