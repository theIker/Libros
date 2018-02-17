/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.manager;

import java.util.Collection;
import libros.datos.beans.ComprasBean;

/**
 *
 * @author Iker Iglesias
 */
public interface ComprasManager {

    public Collection<ComprasBean> getAllCompras(String usuario);
    public void createCompras(ComprasBean compra);
    public void updateCompra(ComprasBean compra);
    public void deleteCompra(ComprasBean compra);
    
    
    

}
