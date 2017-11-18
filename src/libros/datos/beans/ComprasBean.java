/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.beans;

import java.io.Serializable;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Iker Iglesias
 */
public class ComprasBean implements Serializable {

  

    
    
    private final SimpleStringProperty codCompra;
    private final SimpleStringProperty isbn;
    private final SimpleStringProperty fechaComp;
    private final SimpleStringProperty titulo;
    private final SimpleFloatProperty precioTot;
    private final SimpleIntegerProperty unidades;
    
    public ComprasBean(String codCompra,String isbn,String fechaComp,String titulo,
            Float precioTot,Integer unidades){
          this.codCompra=new SimpleStringProperty(codCompra);
          this.isbn=new SimpleStringProperty(isbn);
          this.fechaComp=new SimpleStringProperty(fechaComp);
          this.titulo=new SimpleStringProperty(titulo);
          this.precioTot=new SimpleFloatProperty(precioTot);
          this.unidades=new SimpleIntegerProperty(unidades);
          
        
    }
    

    public void setCodCompra(String codCompra){
        this.codCompra.set(codCompra);
    }
     public String getCodCompra() {
        return this.codCompra.get();
    }
     
     public void setIsbn(String isbn){
        this.isbn.set(isbn);
    }

    public String getIsbn() {
        return this.isbn.get();
    }
    
    public void setFechaComp(String fechaComp){
        this.fechaComp.set(fechaComp);
    }

    public String getFechaComp() {
        return this.fechaComp.get();
    }
    
    public void setTitulo(String titulo){
        this.titulo.set(titulo);
    }

    public String getTitulo() {
        return this.titulo.get();
    }
    
    public void setPrecioTot(Float precioTot){
        this.precioTot.set(precioTot);
    }

    public Float getPrecioTot() {
        return this.precioTot.get();
    }
    
    public void setUnidades(Integer unidades){
        this.unidades.set(unidades);
    }

    public Integer getUnidades() {
        return this.unidades.get();
    }
  
    
}
