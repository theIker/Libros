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
import libros.datos.beans.LibroBean;

/**
 *
 * @author Jon Xabier Gimenez
 */
public class LibrosManagerTestDataGenerator implements LibrosManager {

    private ArrayList<LibroBean> x;
    private final static Logger logger = Logger.getLogger("libros.datos.mana");

    /**
     * Genera libros
     */
    public LibrosManagerTestDataGenerator() {
        x = new ArrayList<LibroBean>();
        for (int i = 1; i < 16; i++) {
            if (i < 10) {
                x.add(new LibroBean("isbn" + i, "titulo" + i, "autor" + i, "editorial" + i, "Descripcion" + i, "2017/11/0" + i, (float) i, i, "Genero" + i));
            } else {
                x.add(new LibroBean("isbn" + i, "titulo" + i, "autor" + i, "editorial" + i, "Descripcion" + i, "2017/11/" + i, (float) i, i, "Genero" + i));
            }
        }
        logger.info("Libros de prueba generados");
    }

    /**
     * Metodo que devulve todos los libros
     *
     * @return Collection
     */
    @Override
    public Collection getAllLibros() {
        logger.info("Devolviendo todos los libros");
        return x;
    }

    /**
     * Filtra libros por isbn
     *
     * @param isbn
     * @return Collection
     */
    public Collection getLibrosIsbn(String isbn) {
        logger.info("Devolviendo libros filtrados por isbn");
        Collection Bisbn = x.stream().filter(libro -> libro.getIsbn().toLowerCase().contains(isbn)).collect(Collectors.toList());
        return Bisbn;
    }

    /**
     * Filra los libros por titulo
     *
     * @param titulo
     * @return Collection
     */
    @Override
    public Collection getLibrosTitulo(String titulo) {
        logger.info("Devolviendo libros filtrados por titulo");
        Collection Btitulo = x.stream().filter(libro -> libro.getTitulo().toLowerCase().contains(titulo)).collect(Collectors.toList());
        return Btitulo;
    }

    /**
     * Filtra los libros por autor
     *
     * @param autor
     * @return Collection
     */
    @Override
    public Collection getLibrosAutor(String autor) {
        logger.info("Devolviendo libros filtrados por autor");
        Collection Bautor = x.stream().filter(libro -> libro.getAutor().toLowerCase().contains(autor)).collect(Collectors.toList());
        return Bautor;
    }

}
