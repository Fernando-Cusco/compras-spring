package ec.edu.ups.compras.controller.compra;

import ec.edu.ups.compras.model.Compra;
import ec.edu.ups.compras.model.Producto;
import ec.edu.ups.compras.service.compra.ICompraService;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/{cedula}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> guardarCompra(@PathVariable("cedula") String cedula) {
        message = compraService.registrarCompra(cedula);
        return  ResponseEntity.ok().body(message);
    }

    @GetMapping("/{cedula}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> misCompras(@PathVariable("cedula") String cedula) {
        return ResponseEntity.ok().body(compraService.comprasCliente(cedula));
    }


    @GetMapping("/carrito/{cedula}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> buscarCompra(@PathVariable("cedula") String cedula) {
        Compra compra = compraService.verCarritoCompras(cedula);
        if (compra != null) {
            return ResponseEntity.ok().body(compra.getDetallesCompra());
        }
        message.setMessage("Error al buscar la compra");
        message.setCode(100);
        message.setStatus(false);
        return ResponseEntity.badRequest().body(message);

    }

    @PutMapping("/carrito/{cedula}/{add}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> actualizarCompra(@RequestBody Producto producto, @PathVariable("cedula") String cedula, @PathVariable("add") boolean add) {
        message = compraService.actualizarCarrito(producto, cedula, add);
        System.out.println(message.toString());
        return  ResponseEntity.ok().body(message);
    }

    @PutMapping("/carrito/eliminar/{cedula}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> eliminarProductoCarrito(@RequestBody Producto producto, @PathVariable("cedula") String cedula) {
        message = compraService.eliminarProductoCarrito(producto, cedula);
        System.out.println(message.toString());
        return  ResponseEntity.ok().body(message);
    }

}
