package ec.edu.ups.compras.controller.compra;

import ec.edu.ups.compras.model.Compra;
import ec.edu.ups.compras.service.compra.ICompraService;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/api/compra")
public class CompraController {

    @Autowired
    private ICompraService compraService;

    private ApiMessage message;

    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response guardarCompra(@RequestBody Compra compra) {
        message = compraService.registrarCompra(compra);
        return Response.status(Response.Status.OK).entity(message).build();
    }

}
