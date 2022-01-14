package ec.edu.ups.compras.controller.favorito;


import ec.edu.ups.compras.model.Favorito;
import ec.edu.ups.compras.service.favorito.IFavoritoService;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@RestController
@RequestMapping("/api/favorito")
public class FavitoController {

    @Autowired
    private IFavoritoService favoritoService;

    private ApiMessage apiMessage;

    @PostMapping("/{cedula}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> favorito(@PathVariable("cedula") String cedula, @PathVariable("id") int id){
        apiMessage = favoritoService.createFavorito(cedula, id);
        return  ResponseEntity.ok().body(apiMessage);
    }

    @DeleteMapping("/{cedula}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> eliminarFavorito(@PathVariable("cedula") String cedula, @PathVariable("id") int id){
        try {
            apiMessage = favoritoService.eliminarFavorito(cedula, id);
        } catch (Exception e) {
            apiMessage = new ApiMessage("error "+e.getMessage(), 100, false);
        }
        return  ResponseEntity.ok().body(apiMessage);
    }

    @GetMapping("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> listarFavoritosPorCliente(@PathVariable("id") int id){
        List<Integer> favoritos = favoritoService.listarProductosFavoritosPorCliente(id);
        return  ResponseEntity.ok().body(favoritos);
    }

}
