/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.manager;

import java.util.Collection;
import libros.datos.beans.LibroBean;
import libros.datos.exceptions.BusquedaLibroException;
import libros.datos.exceptions.LibroException;

/**
 *
 * @author Jon Xabier Gimenezs
 */
public interface LibrosManager {

    public Collection getAllLibros() throws BusquedaLibroException;

    public Collection getLibrosIsbn(String isbn) throws BusquedaLibroException;

    public Collection getLibrosTitulo(String titulo) throws BusquedaLibroException;

    public Collection getLibrosAutor(String autor) throws BusquedaLibroException;
    
    public void createLibro(LibroBean create) throws LibroException;
    
    public void deleteLibro(LibroBean delete) throws LibroException;
    
    public void updateLibro(LibroBean update) throws LibroException;

}
