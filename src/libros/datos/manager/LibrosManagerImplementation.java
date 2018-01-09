/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.manager;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import javax.ws.rs.core.GenericType;
import libros.REST.LibrosRESTClient;
import libros.datos.beans.LibroBean;

/**
 *
 * @author 2dam
 */
public class LibrosManagerImplementation  implements LibrosManager{
    
    private LibrosRESTClient webClient;
    private static final Logger logger=Logger.getLogger("libros.datos.manager");
    
    
     /**
     * Crea un objeto LibrosManagerImplementation. Se construye un cliente de un 
     * servicio RESTful del que se obtienen los datos para la aplicación cliente.
     */
    public LibrosManagerImplementation(){
         webClient=new LibrosRESTClient();
    }

    @Override
    public Collection getAllLibros() {
        logger.info("LibrosManager: Buscando todos los libros desde el servicio REST(XML).");
        List<LibroBean> libros = webClient.findAll_XML(new GenericType<List<LibroBean>>() {});
        return libros;    
    }

    @Override
    public Collection getLibrosIsbn(String isbn) {
        logger.info("LibrosManager: Buscando todos los libros a partir del ISBN desde el servicio REST(XML).");
        List<LibroBean> libros = webClient.findIsbn_XML(new GenericType<List<LibroBean>>() {},isbn);
        return libros;      
    }

    @Override
    public Collection getLibrosTitulo(String titulo) {
        logger.info("LibrosManager: Buscando todos los libros a partir del titulo desde el servicio REST(XML).");
        List<LibroBean> libros = webClient.findTitulo_XML(new GenericType<List<LibroBean>>() {},titulo);
        return libros;  
    }

    @Override
    public Collection getLibrosAutor(String autor) {
        logger.info("LibrosManager: Buscando todos los libros a partir del ISBN desde el servicio REST(XML).");
        List<LibroBean> libros = webClient.findAutor_XML(new GenericType<List<LibroBean>>() {},autor);
        return libros;  
    }

    @Override
    public void createLibro(LibroBean create) {
        logger.info("UsersManager: Creando Libro.");
        webClient.create_XML(create);
    }

    @Override
    public void deleteLibro(LibroBean delete) {
        logger.info("LibrosManager: Borrando Libro.");
        webClient.remove(delete.getIsbn());
    }

    @Override
    public void updateLibro(LibroBean update) {
        logger.info("LibrosManager: Actualizando libro.");
        webClient.update_XML(update);
    }
}
