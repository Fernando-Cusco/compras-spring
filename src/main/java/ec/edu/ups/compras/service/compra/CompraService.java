package ec.edu.ups.compras.service.compra;

import ec.edu.ups.compras.model.*;
import ec.edu.ups.compras.repository.CompraRepository;
import ec.edu.ups.compras.repository.cliente.ClienteRepository;
import ec.edu.ups.compras.repository.producto.ProductoRepository;
import ec.edu.ups.compras.repository.usuario.UsuarioRepository;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class CompraService implements ICompraService{

    @Autowired
    private CompraRepository compraRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public ApiMessage registrarCompra(Compra compra) {
        ApiMessage message = new ApiMessage();
        Cliente cliente = clienteRepository.findClienteByCedula(compra.getCliente().getCedula());
        if (cliente != null) {
            Usuario usuario = usuarioRepository.findUsuarioByCorreo(cliente.getUsuario().getCorreo());
            if (usuario != null) {
                List<Producto> productos = new ArrayList<>();
                for(DetalleCompra detalleCompra: compra.getDetallesCompra()){
                    Producto producto = productoRepository.findProductoById(detalleCompra.getProducto().getId());
                    if (producto != null) {
                        detalleCompra.setProducto(producto);
                    } else {
                        message.setMessage("No existe el producto, primero debes registrarte");
                        message.setCode(100);
                        message.setStatus(false);
                        return message;
                    }

                }

                cliente.setCompras(Arrays.asList(compra));
                compra.setCliente(cliente);
                compraRepository.save(compra);
                message.setMessage("Compra guardada");
                message.setCode(200);
                message.setStatus(true);

            } else {
                message.setMessage("No existe el usuario, primero debes registrarte");
                message.setCode(100);
                message.setStatus(false);
            }
        } else {
            message.setMessage("No existe el cliente, primero debes registrarte");
            message.setCode(100);
            message.setStatus(false);
        }
        return message;
    }

    @Override
    public Iterable<Compra> comprasCliente(Cliente cliente) {
        return null;
    }
}
