package ec.edu.ups.compras.service.cliente;

import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.utils.ApiMessage;

import java.util.List;

public interface IClienteService {
    public ApiMessage registrarCliente(Cliente cliente);

    public Cliente buscarClientePorCedula(String cedula);

    public Cliente buscarClientePorCorreo(String correo);

    public Cliente buscarClientePorId(int id);

    public Iterable<Cliente> clientes();

    public Cliente login(String correo, String password);
}
