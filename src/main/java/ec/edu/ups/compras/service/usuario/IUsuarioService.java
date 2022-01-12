package ec.edu.ups.compras.service.usuario;

import ec.edu.ups.compras.model.Role;
import ec.edu.ups.compras.model.Usuario;
import ec.edu.ups.compras.utils.ApiMessage;

import java.util.List;

public interface IUsuarioService {

    public ApiMessage registrarUsuario(Usuario usuario);

    public Usuario buscarUsuarioPorCorreo(String correo);

    public Usuario login(String correo, String password);

    public Role usuarioRoles(String correo);

    public Usuario buscarUsuarioPorId(int id);

    public Usuario actualizar(Usuario usuario);

}
