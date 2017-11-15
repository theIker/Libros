/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.mana;

import java.util.ArrayList;
import java.util.Collection;
import libros.datos.beans.ComprasBean;

/**
 *
 * @author iker
 */
public class ComprasManagerDataGenerator implements ComprasManager{
    private ArrayList<ComprasBean> compras;

    public ComprasManagerDataGenerator(){
        compras= new ArrayList();
        for(int i=0;i<25;i++){
            compras.add(new ComprasBean("codCompra"+i,"isbn"+i,"fechaComp"+i,"titulo"+i,(float)i,i+2));
        }
    }

    @Override
    public Collection getAllCompras() {
        
        return compras;
    }
    
}
