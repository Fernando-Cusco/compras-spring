package ec.edu.ups.compras.service.cliente;

import ec.edu.ups.compras.helpers.ClienteRegistro;
import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IClienteService {
    public ApiMessage registrarCliente(ClienteRegistro clienteRegistro);

    public Cliente buscarClientePorCedula(String cedula);

    public Cliente buscarClientePorId(int id);

    public Iterable<Cliente> clientes();

}
