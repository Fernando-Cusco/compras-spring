package ec.edu.ups.compras.service.compra;


import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.model.Compra;
import ec.edu.ups.compras.model.Producto;
import ec.edu.ups.compras.utils.ApiMessage;

import java.util.List;

public interface ICompraService {
    public ApiMessage registrarCompra(String cedula);

    public List<Compra> comprasCliente(String cedula);

    public Compra verCarritoCompras(String cedula);

    public ApiMessage actualizarCarrito(Producto producto, String cedula, boolean add);


    public ApiMessage eliminarProductoCarrito(Producto producto, String cedula);


}
