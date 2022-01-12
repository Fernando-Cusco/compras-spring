package ec.edu.ups.compras.controller.cliente;


import ec.edu.ups.compras.helpers.ClienteRegistro;
import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.service.cliente.IClienteService;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private IClienteService service;

    private ApiMessage apiMessage;

    @PutMapping
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> registrarCliente(@RequestBody ClienteRegistro clienteRegistro) {
        System.out.println(clienteRegistro.toString());
        try {
            apiMessage = service.registrarCliente(clienteRegistro);
            return ResponseEntity.ok().body(apiMessage);
        } catch (Exception e) {
            apiMessage = new ApiMessage("Error: "+e.toString(), 500, false);
            return ResponseEntity.internalServerError().body(apiMessage);
        }
    }

    @GetMapping("/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> buscarClientePorCedula(@PathVariable("cedula") String cedula) {
        Cliente cliente = service.buscarClientePorCedula(cedula);
        if (cliente != null) {
            return ResponseEntity.ok(cliente);
        }
        apiMessage = new ApiMessage("Error", 103, false);
        return ResponseEntity.badRequest().body(apiMessage);
    }


}
