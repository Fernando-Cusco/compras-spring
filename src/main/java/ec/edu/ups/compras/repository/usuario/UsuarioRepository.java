package ec.edu.ups.compras.repository.usuario;

import ec.edu.ups.compras.model.Role;
import ec.edu.ups.compras.model.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Integer> {

    public Usuario findUsuarioByCorreo(String correo);

    @Query("select u.roles from Usuario u where u.correo =:correo")
    public Role usuarioRoles(@Param("correo") String correo);

}
