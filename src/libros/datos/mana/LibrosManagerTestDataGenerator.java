/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.mana;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;
import libros.datos.beans.LibroBean;

/**
 *
 * @author ubuntu
 */
public class LibrosManagerTestDataGenerator implements LibrosManager {
    private ArrayList <LibroBean> x;
    
    public LibrosManagerTestDataGenerator(){
        x= new ArrayList<LibroBean>();
        for (int i = 1; i < 16; i++) {
  		if(i<10){
            x.add(new LibroBean("isbn"+i,"titulo"+i,"autor"+i,"editorial"+i,"Descripcion"+i,"2017-11-0"+i,(float)i,i,"Genero"+i));
  		}
  
  		else	x.add(new LibroBean("isbn"+i,"titulo"+i,"autor"+i,"editorial"+i,"Descripcion"+i,"2017-11-"+i,(float)i,i,"Genero"+i));
        }
    }
    
    @Override
    public Collection getAllLibros() {
        return x;
    }
    public Collection getLibrosIsbn(String isbn){
       Collection Bisbn= x.stream().filter(libro->libro.getIsbn().toLowerCase().contains(isbn)).collect(Collectors.toList());    
       return Bisbn;
        }

    @Override
    public Collection getLibrosTitulo(String titulo) {
        
        Collection Btitulo=x.stream().filter(libro->libro.getTitulo().toLowerCase().contains(titulo)).collect(Collectors.toList());    
        return Btitulo;
    }

    @Override
    public Collection getLibrosAutor(String autor) { 
        Collection Bautor= x.stream().filter(libro->libro.getTitulo().toLowerCase().contains(autor)).collect(Collectors.toList());    
        return Bautor;
    }
        
         
    }
    

