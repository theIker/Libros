/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.manager;


import java.util.Collection;
import java.util.List;
import libros.REST.GenerosRESTClient;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import javax.ws.rs.core.GenericType;
import libros.datos.beans.GeneroBean;
import libros.datos.exceptions.GeneroException;

/**
 *
 * @author Jon Xabier Gimenez
 */
public class GenerosManagerImplementation implements GenerosManager {
    
    
    private GenerosRESTClient webClient;
    private static final Logger logger=Logger.getLogger("libros.datos.manager");
    
    
      /**
     * Crea un objeto GeneroManagerImplementation. Se construye un cliente de un 
     * servicio RESTful del que se obtienen los datos para la aplicación cliente.
     */
    public GenerosManagerImplementation(){
         webClient=new GenerosRESTClient();
    }
    
    @Override
    public Collection getAllGeneros() {
        logger.info("GenerosManager: Buscando todos los generos desde el servicio REST(XML).");
        List<GeneroBean> generos = webClient.findAll_XML(new GenericType<List<GeneroBean>>() {});
        return generos;    
    }

    @Override
    public Collection getNombresGenero(Collection <GeneroBean> generos) throws GeneroException {
        return generos.stream().map(gen -> gen.getGenero()).collect(Collectors.toList());
    }
}
