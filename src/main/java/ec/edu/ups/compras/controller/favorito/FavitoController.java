package ec.edu.ups.compras.controller.favorito;


import ec.edu.ups.compras.service.favorito.IFavoritoService;
import ec.edu.ups.compras.utils.ApiMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@RestController
@RequestMapping("/api/compra")
public class FavitoController {

    @Autowired
    private IFavoritoService favoritoService;

    private ApiMessage apiMessage;

    @PostMapping("/favorito/{cedula}/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<Object> favorito(@PathVariable("cedula") String cedula, @PathVariable("id") int id){
        apiMessage = favoritoService.createFavorito(cedula, id);
        return  ResponseEntity.ok().body(apiMessage);
    }

}
