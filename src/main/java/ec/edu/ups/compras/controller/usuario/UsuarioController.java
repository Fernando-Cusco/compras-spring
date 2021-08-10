package ec.edu.ups.compras.controller.usuario;


import ec.edu.ups.compras.model.Usuario;
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


    @PostMapping
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
        return ResponseEntity.ok("Credenciales Incorrecta");
    }

    @GetMapping("/{correo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarUsuarioPorCorreo(@PathVariable("correo") String correo) {
        Usuario usuario = usuarioService.buscarUsuarioPorCorreo(correo);
        return Response.status(Response.Status.OK).entity(usuario).build();
    }

}
