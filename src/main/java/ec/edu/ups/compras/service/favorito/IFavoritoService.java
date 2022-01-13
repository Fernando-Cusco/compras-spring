package ec.edu.ups.compras.service.favorito;

import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.model.Favorito;
import ec.edu.ups.compras.model.Producto;
import ec.edu.ups.compras.utils.ApiMessage;

import java.util.List;

public interface IFavoritoService {

    public ApiMessage createFavorito(String cedula, int idProducto);

    public ApiMessage eliminarFavorito(String cedula, int idProducto);

    public List<Favorito> listarFavoritosPorCliente(int id);

    public List<Favorito> listarFavoritosPorIdProducto(int id);
}
