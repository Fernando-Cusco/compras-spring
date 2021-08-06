package ec.edu.ups.compras.service.cliente;

import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.repository.ClienteRepository;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    private ApiMessage apiMessage;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ApiMessage registrarCliente(Cliente cliente) {
        Cliente cc = clienteRepository.findClienteByCedula(cliente.getCedula());
        Cliente cm = clienteRepository.findClienteByCorreo(cliente.getCorreo());
        if (cc != null) {
            apiMessage = new ApiMessage("La cédula ya existe", 100, false);
            return apiMessage;
        } if (cm != null) {
            apiMessage = new ApiMessage("El correo ya existe", 101, false);
            return apiMessage;
        }
        String passwordEncrypt = passwordEncoder.encode(cliente.getPassword());
        cliente.setPassword(passwordEncrypt);
        clienteRepository.save(cliente);
        apiMessage = new ApiMessage("Cliente registrado correctamente", 200, true);
        return apiMessage;
    }

    @Override
    public Cliente buscarClientePorCedula(String cedula) {
        return clienteRepository.findClienteByCedula(cedula);
    }

    @Override
    public Cliente buscarClientePorCorreo(String correo) {
        return clienteRepository.findClienteByCorreo(correo);
    }

    @Override
    public Cliente buscarClientePorId(int id) {
        return clienteRepository.findClienteById(id);
    }

    @Override
    public Iterable<Cliente> clientes() {
        return clienteRepository.findAll();
    }

    @Override
    public Cliente login(String correo, String password) {
        Cliente cliente = buscarClientePorCorreo(correo);
        if (cliente != null) {
            boolean ok = passwordEncoder.matches(password, cliente.getPassword());
            return ok? cliente: null;
        }
        return null;
    }
}
