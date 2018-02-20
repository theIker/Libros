/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.beans;

import java.io.Serializable;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iker Iglesias
 */
@XmlRootElement(name = "purchase")
public class ComprasBean implements Serializable {

    private final SimpleStringProperty codigo;
    private final SimpleObjectProperty<UserBean> usuario;
    private final SimpleStringProperty fechaCompra;
    private final SimpleFloatProperty precioTotal;
    private final SimpleIntegerProperty unidades;
    private final SimpleObjectProperty<LibroBean> book;

    public ComprasBean(String codigo, UserBean usuario, String fechaCompra,
            Float precioTotal, LibroBean book, Integer unidades) {
        this.codigo = new SimpleStringProperty(codigo);
        this.usuario = new SimpleObjectProperty<>(usuario);
        this.fechaCompra = new SimpleStringProperty(fechaCompra);
        this.precioTotal = new SimpleFloatProperty(precioTotal);
        this.book = new SimpleObjectProperty<>(book);
        this.unidades = new SimpleIntegerProperty(unidades);
    }

    public ComprasBean() {
        this.codigo = new SimpleStringProperty();
        this.usuario = new SimpleObjectProperty<>();
        this.fechaCompra = new SimpleStringProperty();
        this.precioTotal = new SimpleFloatProperty();
        this.book = new SimpleObjectProperty<>();
        this.unidades = new SimpleIntegerProperty();
    }

    public String getTitulo() {
        return book.getValue().getTitulo();
    }

    public String getIsbn() {
        return book.getValue().getIsbn();
    }

    public Integer getUnidades() {
        return this.unidades.get();
    }

    public void setUnidades(Integer unidades) {
        this.unidades.set(unidades);
    }

    public UserBean getUsuario() {
        return this.usuario.get();
    }

    public void setUsuario(UserBean usuario) {
        this.usuario.set(usuario);
    }

    public LibroBean getBook() {
        return this.book.get();
    }

    public void setBook(LibroBean book) {
        this.book.set(book);

    }

    public void setCodigo(String codigo) {
        this.codigo.set(codigo);
    }

    public String getCodigo() {
        return this.codigo.get();
    }

    public void setFechaCompra(String fechaCompra) {
        this.fechaCompra.set(fechaCompra);
    }

    public String getFechaCompra() {
        return this.fechaCompra.get();
    }

    public void setPrecioTotal(Float precioTotal) {
        this.precioTotal.set(precioTotal);
    }

    public Float getPrecioTotal() {
        return this.precioTotal.get();
    }

}
