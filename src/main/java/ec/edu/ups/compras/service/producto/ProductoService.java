package ec.edu.ups.compras.service.producto;

import ec.edu.ups.compras.model.Producto;
import ec.edu.ups.compras.repository.producto.ProductoRepository;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class ProductoService implements IProductoService {

    @Inject
    private ProductoRepository repository;

    private ApiMessage apiMessage;

    @Override
    public ApiMessage registrarProducto(Producto producto) {
        repository.save(producto);
        apiMessage = new ApiMessage("Producto agregado correctamente", 100, true);
        return apiMessage;
    }

    @Override
    public Producto buscarProductoPorNombre(String nombre) {
        return repository.findProductoByNombre(nombre);
    }

    @Override
    public Producto buscarProductoPorId(int id) {
        return repository.findProductoById(id);
    }

    @Override
    public Iterable<Producto> productos() {
        return repository.findAll();
    }
}
