package ec.edu.ups.compras.controller.cliente;


import ec.edu.ups.compras.helpers.ClienteRegistro;
import ec.edu.ups.compras.model.Cliente;
import ec.edu.ups.compras.service.cliente.IClienteService;
import ec.edu.ups.compras.utils.ApiMessage;
import ec.edu.ups.compras.utils.Credenciales;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarCliente(@RequestBody ClienteRegistro clienteRegistro) {
        try {
            apiMessage = service.registrarCliente(clienteRegistro);
            if (apiMessage.getCode() == 200) {
                return Response.status(Response.Status.CREATED).entity(apiMessage).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).entity(apiMessage).build();
        } catch (Exception e) {
            apiMessage = new ApiMessage("Error: "+e.getMessage(), 500, false);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiMessage).build();
        }
    }

    @GetMapping("/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarClientePorCedula(@PathVariable("cedula") String cedula) {
        Cliente cliente = service.buscarClientePorCedula(cedula);
        if (cliente != null) {
            return Response.status(Response.Status.OK).entity(cliente).build();
        }
        apiMessage = new ApiMessage("Error", 103, false);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(apiMessage).build();
    }


}
