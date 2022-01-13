package ec.edu.ups.compras.service.producto;

import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.model.Favorito;
import ec.edu.ups.compras.model.Producto;
import ec.edu.ups.compras.model.Usuario;
import ec.edu.ups.compras.repository.producto.ProductoRepository;
import ec.edu.ups.compras.service.cliente.IClienteService;
import ec.edu.ups.compras.service.favorito.IFavoritoService;
import ec.edu.ups.compras.service.usuario.IUsuarioService;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductoService implements IProductoService {

    @Autowired
    private ProductoRepository repository;

    @Autowired
    private IFavoritoService favoritoService;

    @Autowired
    private IClienteService clienteService;

    @Autowired
    private IUsuarioService usuarioService;

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
    public Iterable<Producto> productos(String id) {
        List<Producto> productos = new ArrayList<>();
        for (Producto producto : repository.findAll()) {
            productos.add(producto);
        }
        Usuario usuario = usuarioService.buscarUsuarioPorId(Integer.parseInt(id));
        List<Favorito> favoritos = favoritoService.listarFavoritosPorCliente(usuario.getCliente().getId());
        if (favoritos.size() > 0) {
            for (int i = 0; i < favoritos.size(); i++) {
                for (int j = 0; j < productos.size(); j++) {
                    if (favoritos.get(i).getProducto().getId() == productos.get(i).getId()) {
                        productos.get(i).setEsFavorito(true);
                        System.out.println("Favorito "+ productos.get(i).getNombre());
                    } else {
                        System.out.println("No es favorito");
                        productos.get(i).setEsFavorito(true);
                    }
                }
            }
            return productos;
        } else {
            return repository.findAll();
        }
    }

    @Override
    public Iterable<Producto> productosAngular() {
        return repository.findAll();
    }
}
