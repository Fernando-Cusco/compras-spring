package ec.edu.ups.compras.controller.imagen;

import ec.edu.ups.compras.model.Imagen;
import ec.edu.ups.compras.service.imagen.IImagenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.core.HttpHeaders;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.PUT,RequestMethod.POST})
@RequestMapping("/api/imagen")
public class ImagenController {

    @Autowired
    private IImagenService imagenService;

    @PostMapping("/upload")
    @PreAuthorize("hasRole('ROLE_ADMIN') OR hasRole('ROLE_USER_WRITE')")
    public ResponseEntity<Object> uploadImage(@RequestParam("file")MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        try {
            Imagen imagen = new Imagen();
            String nombre = imagenService.guardar(file);
            imagen.setPath(nombre);
            return ResponseEntity.ok(imagen);
        } catch (Exception e) {
            throw new RuntimeException("Error");
        }
    }

    @GetMapping("/get/{filename:.+}")
    @ResponseBody
    public ResponseEntity<Object> obtenerImagen(@PathVariable String filename) {
        Resource resource = imagenService.cargar(filename);
        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }

//    @DeleteMapping("/{nombre}")
//    public ResponseEntity<Object> eliminarNombre(@PathVariable("nombre") String nombre)

}
