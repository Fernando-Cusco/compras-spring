package ec.edu.ups.compras.service.cliente;

import ec.edu.ups.compras.helpers.ClienteRegistro;
import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.utils.ApiMessage;

import java.util.List;

public interface IClienteService {
    public ApiMessage registrarCliente(ClienteRegistro clienteRegistro);

    public Cliente buscarClientePorCedula(String cedula);

    public Cliente buscarClientePorId(int id);

    public Iterable<Cliente> clientes();

}
