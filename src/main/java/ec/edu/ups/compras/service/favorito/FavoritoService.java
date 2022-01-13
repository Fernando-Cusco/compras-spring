package ec.edu.ups.compras.service.favorito;

import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.model.Favorito;
import ec.edu.ups.compras.model.Producto;
import ec.edu.ups.compras.repository.cliente.ClienteRepository;
import ec.edu.ups.compras.repository.favorito.IFavoritoRepository;
import ec.edu.ups.compras.service.producto.IProductoService;
import ec.edu.ups.compras.service.usuario.IUsuarioService;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FavoritoService implements IFavoritoService {

    @Autowired
    private IFavoritoRepository favoritoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private IProductoService productoService;

    @Override
    public ApiMessage createFavorito(String cedula, int idProducto) {
        Cliente cliente = clienteRepository.findClienteByCedula(cedula);
        Producto producto = productoService.buscarProductoPorId(idProducto);
        Favorito f = favoritoRepository.findByClienteAndProducto(cliente, producto);
        ApiMessage apiMessage = new ApiMessage();
        if (f == null) {
            System.out.println("Favorito no existe");
            Favorito favorito = new Favorito();
            favorito.setCliente(cliente);
            favorito.setProducto(producto);
            favoritoRepository.save(favorito);
            apiMessage.setMessage("Favorito creado");
            apiMessage.setCode(200);
            apiMessage.setStatus(true);
        } else {
            System.out.println("Favorito ya existe");
            apiMessage.setMessage("Favorito ya existe");
            apiMessage.setCode(100);
            apiMessage.setStatus(false);
        }
        return apiMessage;
    }

    @Override
    public ApiMessage eliminarFavorito(String cedula, int idProducto) {
        Cliente cliente = clienteRepository.findClienteByCedula(cedula);
        Producto producto = productoService.buscarProductoPorId(idProducto);
        favoritoRepository.deleteFavoritoByClienteAndProducto(cliente, producto);
        ApiMessage apiMessage = new ApiMessage();
        apiMessage.setCode(200);
        apiMessage.setMessage("Favorito eliminado");
        apiMessage.setStatus(true);
        return apiMessage;
    }

    @Override
    public List<Favorito> listarFavoritosPorCliente(int id) {
        return favoritoRepository.findByClienteId(id);
    }

    @Override
    public List<Favorito> listarFavoritosPorIdProducto(int id) {
        return null;
    }
}
