/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.beans;

import java.beans.*;
import java.io.Serializable;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Jon Xabier Gimenez
 */

@XmlRootElement(name="genero")
public class GeneroBean {

    private final SimpleIntegerProperty codGenero;
    private final SimpleStringProperty genero;

    public GeneroBean(Integer codGenero, String genero) {
        this.codGenero = new SimpleIntegerProperty(codGenero);
        this.genero = new SimpleStringProperty(genero);
    }

    public Integer getCodGenero() {
        return this.codGenero.get();
    }

    public void setCodGenero(Integer codGenero) {

        this.codGenero.set(codGenero);
    }

    public String getGenero() {
        return this.genero.get();
    }

    public void setGenero(String Genero) {

        this.genero.set(Genero);
    }

}
