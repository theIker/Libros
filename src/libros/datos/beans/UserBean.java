/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.datos.beans;

import java.io.Serializable;
import javafx.beans.property.SimpleStringProperty;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Iker Iglesias
 */
@XmlRootElement(name = "usuario")
public class UserBean implements Serializable {

    private final SimpleStringProperty usuario;
    private final SimpleStringProperty nombre;
    private final SimpleStringProperty apellidos;
    private final SimpleStringProperty direccion;
    private final SimpleStringProperty telefono;
    private final SimpleStringProperty email;

    public UserBean() {
        this.usuario = new SimpleStringProperty();
        this.nombre = new SimpleStringProperty();
        this.apellidos = new SimpleStringProperty();
        this.direccion = new SimpleStringProperty();
        this.telefono = new SimpleStringProperty();
        this.email = new SimpleStringProperty();

    }

    public void setUsuario(String usuario) {
        this.usuario.set(usuario);
    }

    public String getUsuario() {
        return this.usuario.get();
    }

    public void setNombre(String nombre) {
        this.nombre.set(nombre);
    }

    public String getNombre() {
        return this.nombre.get();
    }

    public void setApellidos(String apellidos) {
        this.apellidos.set(apellidos);
    }

    public String getApellidos() {
        return this.apellidos.get();
    }

    public void setDireccion(String direccion) {
        this.direccion.set(direccion);
    }

    public String getDireccion() {
        return this.direccion.get();
    }

    public void setTelefono(String telefono) {
        this.telefono.set(telefono);
    }

    public String getTelefono() {
        return this.telefono.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public String getEmail() {
        return this.email.get();
    }

}
