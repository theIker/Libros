/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.mana;

import java.util.Collection;

/**
 *
 * @author ubuntu
 */
public interface LibrosManager {
    
     public Collection getAllLibros();
     
     public Collection getLibrosIsbn(String isbn);
    
}
