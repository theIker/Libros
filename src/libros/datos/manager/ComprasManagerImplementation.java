/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.manager;

import java.util.Collection;
import java.util.List;
import java.util.logging.Logger;
import libros.REST.ComprasRestClient;
import libros.datos.beans.ComprasBean;
import javax.ws.rs.core.GenericType;

/**
 * Clase que implementa la interfaz de la lógica de negocio devolviendo datos
 * de obtenidos del servicio REST para la entidad User.
 * @author Iker Iglesias
 */
public class ComprasManagerImplementation implements ComprasManager{
    
     private ComprasRestClient webClient;
     private final static Logger logger = Logger.getLogger("libros.datos.mana");
     
     
       /**
     * Crea un objeto UsersManagerImplementation. Se construye un cliente de un 
     * servicio RESTful del que se obtienen los datos para la aplicación cliente.
     */
    public ComprasManagerImplementation(){
        webClient=new ComprasRestClient();
    }

    @Override
    public Collection<ComprasBean> getAllCompras(String usuario) {
        logger.info("Recibiendo compras del usuario");
        List<ComprasBean> compras=webClient.findAll_XML(new GenericType<List<ComprasBean>>() {}, usuario);
        
        return compras; 
    }

    @Override
    public void createCompras(ComprasBean compra) {
        logger.info("Creando compra");
         webClient.create_XML(compra);
    }

    @Override
    public void updateCompra(ComprasBean compra) {
        logger.info("Updateando compra");
        webClient.update_XML(compra);
    }

    @Override
    public void deleteCompra(ComprasBean compra) {
        logger.info("Borrando compra");
        webClient.remove(compra.getCodigo());
    }

   
    
    
}
