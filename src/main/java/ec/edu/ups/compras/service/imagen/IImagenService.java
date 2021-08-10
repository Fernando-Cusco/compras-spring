package ec.edu.ups.compras.service.imagen;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;

public interface IImagenService {

    public void init();

    public String guardar(MultipartFile file);

    public Resource cargar(String filename);

}
