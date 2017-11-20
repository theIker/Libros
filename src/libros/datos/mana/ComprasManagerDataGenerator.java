/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.mana;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import libros.datos.beans.ComprasBean;

/**
 *
 * @author Iker Iglesias
 */
public class ComprasManagerDataGenerator implements ComprasManager {

    private ArrayList<ComprasBean> compras;
    private final static Logger logger = Logger.getLogger("libros.datos.mana");

    /**
     * Metodo para generar compras
     */
    public ComprasManagerDataGenerator() {
        compras = new ArrayList();
        for (int i = 0; i < 25; i++) {
            compras.add(new ComprasBean("codCompra" + i, "isbn" + i, "fechaComp" + i, "titulo" + i, (float) i, i + 2));

        }
        logger.info("Generadas compras de prueba");
    }

    /**
     * Metodo para devolver las compras
     *
     * @return las compras
     */
    @Override
    public Collection getAllCompras() {
        logger.info("Devolviendo todas las compras");
        return compras;
    }

}
