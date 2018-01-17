/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.beans;

import java.beans.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;

/**
 *
 * @author Jon Xabier Gimenez
 */


@XmlRootElement(name="book")
public class LibroBean implements Serializable {

    private final SimpleStringProperty isbn;
    private final SimpleStringProperty titulo;
    private final SimpleStringProperty autor;
    private final SimpleStringProperty editorial;
    private final SimpleStringProperty descripcion;
    private final SimpleStringProperty fechaPub;
    private final SimpleFloatProperty precio;
    private final SimpleIntegerProperty stock;
    private final SimpleObjectProperty<GeneroBean> genero;

   public LibroBean(String isbn, String titulo, String autor,
            String editorial, String descripcion, String fechaPub, Float precio, Integer stock, GeneroBean genero) {

        this.isbn = new SimpleStringProperty(isbn);
        this.titulo = new SimpleStringProperty(titulo);
        this.autor = new SimpleStringProperty(autor);
        this.editorial = new SimpleStringProperty(editorial);
        this.descripcion = new SimpleStringProperty(descripcion);
        this.fechaPub = new SimpleStringProperty(fechaPub);
        this.precio = new SimpleFloatProperty(precio);
        this.stock = new SimpleIntegerProperty(stock);
        this.genero = new SimpleObjectProperty(genero);

    }
  
    
    
   
     public LibroBean(){
        this.isbn = new SimpleStringProperty();
        this.titulo = new SimpleStringProperty();
        this.autor = new SimpleStringProperty();
        this.editorial = new SimpleStringProperty();
        this.descripcion = new SimpleStringProperty();
        this.fechaPub = new SimpleStringProperty();
        this.precio = new SimpleFloatProperty();
        this.stock = new SimpleIntegerProperty();
        this.genero = new SimpleObjectProperty();
    }

    public String getIsbn() {
        return this.isbn.get();
    }

    public void setIsbn(String isbn) {
        this.isbn.set(isbn);
    }

    public String getTitulo() {
        return this.titulo.get();
    }

    public void setTitulo(String titulo) {
        this.titulo.set(titulo);
    }

    public String getAutor() {
        return this.autor.get();
    }

    public void setAutor(String autor) {
        this.autor.set(autor);
    }

    public String getEditorial() {
        return this.editorial.get();
    }

    public void setEditorial(String editorial) {
        this.editorial.set(editorial);
    }

    public String getDescripcion() {
        return this.descripcion.get();
    }

    public void setDescripcion(String descripcion) {
        this.descripcion.set(descripcion);
    }
    @XmlElement(name="fechaPublicacion")
    public String getFechaPub() {
        return this.fechaPub.get();
    }

    public void setFechaPub(String fechaPub) {
        this.fechaPub.set(fechaPub);
    }

    public Float getPrecio() {
        return this.precio.get();
    }

    public void setPrecio(Float precio) {
        this.precio.set(precio);
    }

    public Integer getStock() {
        return this.stock.get();
    }

    public void setStock(Integer stock) {
        this.stock.set(stock);
    }
    @XmlElement(name="genero")
    public GeneroBean getGenero(){
        return this.genero.get();
    }
    public void setGenero(GeneroBean genero){
        this.genero.set(genero);
    }
   
}
