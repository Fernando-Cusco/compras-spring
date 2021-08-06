package ec.edu.ups.compras.controller;

import ec.edu.ups.compras.model.Producto;
import ec.edu.ups.compras.service.producto.IProductoService;
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
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    private ApiMessage apiMessage;

    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarProducto(@RequestBody Producto producto) {
        apiMessage = productoService.registrarProducto(producto);
        return Response.status(Response.Status.OK).entity(apiMessage).build();
    }
}
