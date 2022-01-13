package ec.edu.ups.compras.service.producto;

import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.model.Producto;
import ec.edu.ups.compras.utils.ApiMessage;

public interface IProductoService {

    public ApiMessage registrarProducto(Producto producto);

    public Producto buscarProductoPorNombre(String nombre);

    public Producto buscarProductoPorId(int id);

    public Iterable<Producto> productos(String id);

    public Iterable<Producto> productosAngular();

}
