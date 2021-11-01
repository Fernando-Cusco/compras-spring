package ec.edu.ups.compras.service.usuario;

import ec.edu.ups.compras.auth.TokenProvider;
import ec.edu.ups.compras.model.Role;
import ec.edu.ups.compras.model.Usuario;
import ec.edu.ups.compras.repository.role.RoleRepository;
import ec.edu.ups.compras.repository.usuario.UsuarioRepository;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UsuarioService implements IUsuarioService{

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public ApiMessage registrarUsuario(Usuario usuario) {
        ApiMessage apiMessage = new ApiMessage();
        Usuario u = buscarUsuarioPorCorreo(usuario.getCorreo());
        if (u != null) {
            apiMessage.setMessage("Correo ya esta registrado");
            apiMessage.setCode(200);
            apiMessage.setStatus(false);
        } else {
            Role user = roleRepository.findByName("ROLE_USER_READ");
            usuario.setRoles(Arrays.asList(user));
            usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));

            usuarioRepository.save(usuario);
            apiMessage.setMessage("Usuario registrado correctamente");
            apiMessage.setCode(100);
            apiMessage.setStatus(true);
        }
        return apiMessage;
    }

    @Override
    public Usuario buscarUsuarioPorCorreo(String correo) {
        return usuarioRepository.findUsuarioByCorreo(correo);
    }

    @Override
    public Usuario login(String correo, String password) {
        Usuario usuario = buscarUsuarioPorCorreo(correo);
        Role role = usuarioRoles(correo);
        if (usuario != null) {
            boolean ok = passwordEncoder.matches(password, usuario.getPassword());
            String token = TokenProvider.generarToken(usuario, role);
            usuario.setPassword(null);
            usuario.setToken(token);
            return ok? usuario: null;
        }
        return null;
    }

    @Override
    public Role usuarioRoles(String correo) {
        return usuarioRepository.usuarioRoles(correo);
    }

    @Override
    public Usuario buscarUsuarioPorId(int id) {
        return usuarioRepository.findById(id).orElse(null);
    }


}
