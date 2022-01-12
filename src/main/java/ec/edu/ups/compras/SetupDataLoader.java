package ec.edu.ups.compras;

import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.model.Role;
import ec.edu.ups.compras.model.Usuario;
import ec.edu.ups.compras.repository.role.RoleRepository;
import ec.edu.ups.compras.repository.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.Arrays;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;


    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (!alreadySetup)
            return;
        createRoleIfNotFound("ROLE_ADMIN", "read-write-delete");
        createRoleIfNotFound("ROLE_USER_READ", "read");
        createRoleIfNotFound("ROLE_USER_WRITE", "read-write");
        createRoleIfNotFound("ROLE_USER_DETELE", "read-write-detele");

        Role adminRole = roleRepository.findByName("ROLE_ADMIN");

        Usuario usuario = new Usuario();
        usuario.setCorreo("admin@admin.com");
        usuario.setPassword(passwordEncoder.encode("admin"));
        usuario.setEstado(true);
        usuario.setRoles(Arrays.asList(adminRole));
        Cliente cliente = new Cliente();
        cliente.setUsuario(usuario);
        cliente.setApellidos("admin");
        cliente.setNombres("admin");
        cliente.setCedula("0000000000");
        cliente.setDireccion("Ec");
        usuario.setCliente(cliente);
        usuarioRepository.save(usuario);
        alreadySetup = true;
    }


    @Transactional
    Role createRoleIfNotFound(String name, String descripcion) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name, descripcion);
            roleRepository.save(role);
        }
        return role;
    }
}
