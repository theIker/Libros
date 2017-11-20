/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.mana;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import libros.datos.beans.GeneroBean;

/**
 *
 * @author Jon Xabier Gimenez
 */
public class GenerosManagerTestDataGenerator implements GenerosManager {

    private ArrayList<GeneroBean> x;
    private final static Logger logger = Logger.getLogger("libros.datos.mana");

    /**
     * Metodo para generar generos
     */
    public GenerosManagerTestDataGenerator() {
        x = new ArrayList<GeneroBean>();
        for (int i = 0; i < 15; i++) {
            x.add(new GeneroBean(i, "Genero" + i));
        }
        logger.info("Generos de prueba generados");
    }

    /**
     * Devuelve todos los generos
     *
     * @return Collection
     */
    @Override
    public Collection getAllGeneros() {
        logger.info("Devolviendo todos los generos");
        return x;
    }

    /**
     * Metodo que devuelve los los generos
     *
     * @return
     */
    @Override
    public Collection getNombresGenero() {
        logger.info("Mapeando para sacar solo los nombres");
        Collection generosC = x.stream().map(gen -> gen.getGenero()).collect(Collectors.toList());
        return generosC;
    }

}
