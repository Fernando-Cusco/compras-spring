package ec.edu.ups.compras.service.compra;

import ec.edu.ups.compras.model.*;
import ec.edu.ups.compras.repository.compra.CompraRepository;
import ec.edu.ups.compras.repository.cliente.ClienteRepository;
import ec.edu.ups.compras.repository.producto.ProductoRepository;
import ec.edu.ups.compras.repository.usuario.UsuarioRepository;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

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
    public ApiMessage registrarCompra(String  cedula) {
        ApiMessage message = new ApiMessage();
        Cliente cliente = clienteRepository.findClienteByCedula(cedula);
        Compra carrito = compraRepository.findCompraByClienteAndEstadoCompra(cliente, EstadoCompra.CARRITO);
        if (carrito != null) {
            carrito.setEstadoCompra(EstadoCompra.FINALIZADA);
            compraRepository.save(carrito);
            message.setMessage("Compra registrada");
            message.setCode(200);
            message.setStatus(true);
        } else {
            message.setMessage("Eror al registrar la compra");
            message.setCode(100);
            message.setStatus(false);
        }

        return message;
    }

    @Override
    public List<Compra> comprasCliente(String cedula) {
        Cliente cliente = clienteRepository.findClienteByCedula(cedula);
        List<Compra> compras = compraRepository.findAllByClienteAndEstadoCompra(cliente, EstadoCompra.FINALIZADA);
        if (compras.isEmpty()) {
            return new ArrayList<>();
        }
        return compras;
    }

    @Override
    public Compra verCarritoCompras(String cedula) {
        Cliente cliente = clienteRepository.findClienteByCedula(cedula);
        return compraRepository.findCompraByClienteAndEstadoCompra(cliente, EstadoCompra.CARRITO);
    }

    @Override
    public ApiMessage eliminarProductoCarrito(Producto producto, String cedula) {
        ApiMessage message = new ApiMessage();
        Cliente cliente = clienteRepository.findClienteByCedula(cedula);
        Compra carrito = compraRepository.findCompraByClienteAndEstadoCompra(cliente, EstadoCompra.CARRITO);
        if (carrito != null) {
            List<DetalleCompra> detallesCompra = carrito.getDetallesCompra();
            AtomicBoolean encontrado = new AtomicBoolean(false);
            for (int i = 0; i < detallesCompra.size(); i++) {
                if (detallesCompra.get(i).getProducto().getId() == producto.getId()) {
                    encontrado.set(true);
                    detallesCompra.get(i).setSubtotal(detallesCompra.get(i).calcularSubtotal());
                    detallesCompra.remove(i);
                }
            }
            if (encontrado.get()) {
                carrito.setDetallesCompra(detallesCompra);
                carrito.setTotal(carrito.calcularTotal());
                compraRepository.save(carrito);
                message.setMessage("Producto eliminado");
                message.setCode(200);
                message.setStatus(true);
            } else {
                message.setMessage("No existe el producto en el carrito");
                message.setCode(400);
                message.setStatus(false);
            }
        } else {
            message.setMessage("No existe el carrito de compras");
            message.setCode(400);
            message.setStatus(false);
        }
        return message;
    }
    @Override
    public ApiMessage actualizarCarrito(Producto producto, String cedula, boolean add) {
        Cliente cliente = clienteRepository.findClienteByCedula(cedula);
        Compra carrito = compraRepository.findCompraByClienteAndEstadoCompra(cliente, EstadoCompra.CARRITO);
        ApiMessage apiMessage = new ApiMessage();
        AtomicBoolean existe = new AtomicBoolean(false);
        if (carrito != null && cliente != null) {
            List<DetalleCompra> detallesCompra = carrito.getDetallesCompra();
            for (int i = 0; i < detallesCompra.size(); i++) {
                if (detallesCompra.get(i).getProducto().getId() == producto.getId()) {
                    detallesCompra.get(i).setCantidad((add)?detallesCompra.get(i).getCantidad() + 1 : detallesCompra.get(i).getCantidad() - 1);
                    detallesCompra.get(i).setSubtotal(detallesCompra.get(i).calcularSubtotal());
                    carrito.setTotal(carrito.calcularTotal());
                    existe.set(true);
                    compraRepository.save(carrito);
                }
            }

            if (!existe.get()) {
                DetalleCompra detalleCompra = new DetalleCompra();
                detalleCompra.setProducto(producto);
                detalleCompra.setCantidad(1);
                detalleCompra.setSubtotal(detalleCompra.calcularSubtotal());
                detallesCompra.add(detalleCompra);
                carrito.setDetallesCompra(detallesCompra);
                carrito.setTotal(carrito.calcularTotal());

                compraRepository.save(carrito);
            }
            apiMessage.setCode(100);
            apiMessage.setMessage("Producto agregado al carrito");
            apiMessage.setStatus(true);
            return apiMessage;
        } else {
            // crear el carrito compras
            Producto p = productoRepository.findProductoById(producto.getId());
            DetalleCompra detalleCompra = new DetalleCompra();
            detalleCompra.setProducto(p);
            detalleCompra.setCantidad(1);
            detalleCompra.setSubtotal(detalleCompra.calcularSubtotal());
            List<DetalleCompra> detallesCompra = new ArrayList<>();
            detallesCompra.add(detalleCompra);
            Compra compra = new Compra();
            compra.setDetallesCompra(detallesCompra);
            compra.setTotal(compra.calcularTotal());
            compra.setCliente(cliente);
            compra.setEstadoCompra(EstadoCompra.CARRITO);
            compraRepository.save(compra);
            apiMessage.setCode(100);
            apiMessage.setMessage("Se creo el carrito de compras");
            apiMessage.setStatus(true);
            return apiMessage;
        }
    }
}
