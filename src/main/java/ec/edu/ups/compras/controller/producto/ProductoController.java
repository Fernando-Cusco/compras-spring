package ec.edu.ups.compras.controller.producto;

import ec.edu.ups.compras.model.Producto;
import ec.edu.ups.compras.service.producto.IProductoService;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@RequestMapping("/api/producto")
public class ProductoController {

    @Autowired
    private IProductoService productoService;

    private ApiMessage apiMessage;

    @PostMapping
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER_WRITE')")
    public ResponseEntity<Object> registrarProducto(@RequestBody Producto producto) {
        apiMessage = productoService.registrarProducto(producto);
        return ResponseEntity.ok(apiMessage);
    }

    @GetMapping("/lista")
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER_READ')")
    public ResponseEntity<Object> productos() {
        Iterable<Producto> productos = productoService.productos();
        return ResponseEntity.ok(productos);
    }

}
