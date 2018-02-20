/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.manager;

import java.util.Collection;
import libros.datos.beans.ComprasBean;
import libros.datos.exceptions.CompraException;

/**
 *
 * @author Iker Iglesias
 */
public interface ComprasManager {

    public Collection<ComprasBean> getAllCompras(String usuario) throws CompraException;

    public void createCompras(ComprasBean compra) throws CompraException;

    public void updateCompra(ComprasBean compra) throws CompraException;

    public void deleteCompra(ComprasBean compra) throws CompraException;

}
