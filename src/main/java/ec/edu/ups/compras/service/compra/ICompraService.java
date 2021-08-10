package ec.edu.ups.compras.service.compra;


import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.model.Compra;
import ec.edu.ups.compras.utils.ApiMessage;

public interface ICompraService {
    public ApiMessage registrarCompra(Compra compra);

    public Iterable<Compra> comprasCliente(Cliente cliente);

}
