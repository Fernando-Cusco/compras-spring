package ec.edu.ups.compras.service.cliente;

import ec.edu.ups.compras.helpers.ClienteRegistro;
import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.model.Usuario;
import ec.edu.ups.compras.repository.cliente.ClienteRepository;
import ec.edu.ups.compras.repository.usuario.UsuarioRepository;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class ClienteService implements IClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private ApiMessage apiMessage;


    @Override
    public ApiMessage registrarCliente(ClienteRegistro clienteRegistro) {
        Usuario usuario = usuarioRepository.findUsuarioByCorreo(clienteRegistro.getCorreo());
        Cliente cliente = clienteRepository.findClienteByCedula(clienteRegistro.getCedula());

        if (usuario != null) {
            if (cliente != null && Objects.equals(usuario.getCliente(), cliente)) {
                System.out.println("actualizando");
                clienteRepository.updateCliente(cliente.getId(), clienteRegistro.getNombres(), clienteRegistro.getApellidos(), clienteRegistro.getCedula(), clienteRegistro.getDireccion());
                apiMessage = new ApiMessage("Cliente actualizado correctamente", 200, true);
            }
            else if (cliente != null && !Objects.equals(usuario.getCliente(), cliente)) {
                apiMessage = new ApiMessage("La cédula registrada", 100, false);
                System.out.println("error");
            } else {
                System.out.println("creando");
                System.out.println("Creando Cliente");
                Cliente c = usuario.getCliente();
                c.setNombres(clienteRegistro.getNombres());
                c.setApellidos(clienteRegistro.getApellidos());
                c.setDireccion(clienteRegistro.getDireccion());
                c.setCedula(clienteRegistro.getCedula());
                c.setUsuario(usuario);
                usuario.setCliente(c);
                c.setUsuario(usuario);
//                clienteRepository.save(c);
                usuarioRepository.save(usuario);
                apiMessage = new ApiMessage("Cliente actualizado correctamente", 200, true);
            }
        } else {
            apiMessage = new ApiMessage("Usuario no registrado", 100, false);
            System.out.println("NO Existe usuario");
        }
        return apiMessage;
    }

    @Override
    public Cliente buscarClientePorCedula(String cedula) {
        return clienteRepository.findClienteByCedula(cedula);
    }


    @Override
    public Cliente buscarClientePorId(int id) {
        return clienteRepository.findClienteById(id);
    }

    @Override
    public Iterable<Cliente> clientes() {
        return clienteRepository.findAll();
    }
}
