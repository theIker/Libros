/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.mana;

import java.util.ArrayList;
import java.util.Collection;
import libros.datos.beans.GeneroBean;

/**
 *
 * @author ubuntu
 */
public class GenerosManagerTestDataGenerator implements GenerosManager{

    private ArrayList <GeneroBean> x;
    
    public GenerosManagerTestDataGenerator(){
        x= new ArrayList <GeneroBean>();
        for (int i = 0; i < 15; i++) {
            x.add(new GeneroBean(i,"Genero"+i ));
        }
    }
    @Override
    public Collection getAllGeneros() {
        return x;
    }

    
}
