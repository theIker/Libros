/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.awt.Checkbox;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author iker
 */
public class RegistroController implements Initializable {
    private Stage stage;
    @FXML
    private TextField textUser;
    @FXML
    private PasswordField textPass;
    @FXML
    private PasswordField TextPass2;
    @FXML
    private Button btnSig;
    @FXML
    private TextField textNombre;
    @FXML
    private TextField textApellido1;
    @FXML
    private TextField textApellido2;
    @FXML
    private TextField textDireccion;
    @FXML
    private TextField textTelefono;
    @FXML
    private TextField textEmail;
    @FXML
    private TextField textCuenta;
    @FXML
    private Button btnRegistrar;
    @FXML
    private Checkbox aceptarTerminos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validarDatos(ActionEvent event) {
        if(!textUser.getText().equals("u")){
               if(textPass.getText().equals(TextPass2)){
                    textNombre.setVisible(true);
                    textApellido1.setVisible(true);
                    textApellido2.setVisible(true);
                    textDireccion.setVisible(true);
                    textTelefono.setVisible(true);
                    textEmail.setVisible(true);
                    textCuenta.setVisible(true);
                    btnRegistrar.setVisible(true);
                    aceptarTerminos.setVisible(true);
               }
    }
        
  

    @FXML
    private void registrarse(ActionEvent event) {   
    }
    
    
     public void setStage(Stage stage) {
        this.stage=stage;
    }

   public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnShowing(this::handleWindowShowing);
        stage.show();
    }
    
    public void handleWindowShowing(WindowEvent event){
           //se ejecuta antes de iniciar la ventana
           textNombre.setVisible(false);
           textApellido1.setVisible(false);
           textApellido2.setVisible(false);
           textDireccion.setVisible(false);
           textTelefono.setVisible(false);
           textEmail.setVisible(false);
           textCuenta.setVisible(false);
           btnRegistrar.setVisible(false);
           aceptarTerminos.setVisible(false);
    } 
    
}
