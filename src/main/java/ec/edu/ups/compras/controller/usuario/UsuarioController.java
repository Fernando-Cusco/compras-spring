package ec.edu.ups.compras.controller.usuario;


import ec.edu.ups.compras.helpers.Mensaje;
import ec.edu.ups.compras.model.Usuario;
import ec.edu.ups.compras.service.cliente.IClienteService;
import ec.edu.ups.compras.service.usuario.IUsuarioService;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@RequestMapping("/api/usuario")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    private ApiMessage apiMessage;

    private Usuario usuario;

    @PostMapping("/registro")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> registrarUsuario(@RequestBody Usuario usuario) {

        apiMessage = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(apiMessage);
    }

    @PostMapping("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> iniciarSesion(@RequestBody Usuario u) {
        usuario = usuarioService.login(u.getCorreo(), u.getPassword());
        if (usuario != null) {
            return ResponseEntity.ok(usuario);
        }
        Usuario usuario = new Usuario();
        usuario.setId(0);
        usuario.setCorreo("");
        usuario.setPassword("");
        usuario.setTelefono(0);
        usuario.setEstado(false);
        usuario.setToken("");
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/{correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> buscarUsuarioPorCorreo(@PathVariable("correo") String correo) {
        Usuario usuario = usuarioService.buscarUsuarioPorCorreo(correo);
        usuario.setPassword("");
        return ResponseEntity.ok(usuario);
    }

    @GetMapping("/perfil/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> buscarUsuarioPorId(@PathVariable("id") int id) {
        Usuario usuario = usuarioService.buscarUsuarioPorId(id);
        usuario.setPassword("");
        usuario.getCliente();
        return ResponseEntity.ok(usuario);
    }

    @PutMapping("/actualizar")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> actualizar(@RequestBody Usuario usuario) {
        Usuario u = usuarioService.actualizar(usuario);
        u.setPassword("");
        return ResponseEntity.ok(u);
    }

}
