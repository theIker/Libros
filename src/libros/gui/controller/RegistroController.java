/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.control.CheckBox;



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
    private CheckBox aceptarTerminos;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void validarDatos(ActionEvent event) {
        if(textUser.getText().trim().equals("")||textPass.getText().trim().equals("")||TextPass2.getText().trim().equals("")){
            Alert alert = new Alert(AlertType.ERROR, "Rellene todos los campos");
            alert.showAndWait();
        }else{
            if(!textUser.getText().equals("u")){
                if(textPass.getText().trim().equals(TextPass2.getText().trim())){
                      textNombre.setDisable(false);
                      textApellido1.setDisable(false);
                      textApellido2.setDisable(false);
                      textDireccion.setDisable(false);
                      textTelefono.setDisable(false);
                      textEmail.setDisable(false);
                      textCuenta.setDisable(false);
                      btnRegistrar.setDisable(false);
                      aceptarTerminos.setDisable(false);
                      
                      textUser.setDisable(true);
                      textPass.setDisable(true);
                      TextPass2.setDisable(true);
                }else{
                      Alert alert = new Alert(AlertType.ERROR, "Las contraseñas deben coincidir");
                      alert.showAndWait(); 
                } 
            }else{
               Alert alert = new Alert(AlertType.ERROR, "Nombre de usuario en uso");
               alert.showAndWait(); 
            }
        }      
    }
  

    @FXML
    public void registrarse(ActionEvent event) {   
        if(textNombre.getText().trim().equals("")||textApellido1.getText().trim().equals("")||textApellido2.getText().trim().equals("")||
                textEmail.getText().trim().equals("")|| textDireccion.getText().trim().equals("")||textTelefono.getText().trim().equals("")
                ||textCuenta.getText().trim().equals("")){
             Alert alert = new Alert(AlertType.ERROR, "Rellene todos los campos");
             alert.showAndWait();
        }else{
            if (textEmail.getText().matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$")){
                if(aceptarTerminos.isSelected()){
                    Alert alert = new Alert(AlertType.INFORMATION, "Registro completado podra acceder con el usuario u:u");
                    alert.showAndWait();
                    stage.close();
                }else{
                     Alert alert = new Alert(AlertType.ERROR, "Acepte los terminos primero");
                     alert.showAndWait();
                }
            }else{
                 Alert alert = new Alert(AlertType.ERROR, "Email Incorrecto");
                 alert.showAndWait();
            }
        }
        
        
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
           textNombre.setDisable(true);
           textApellido1.setDisable(true);
           textApellido2.setDisable(true);
           textDireccion.setDisable(true);
           textTelefono.setDisable(true);
           textEmail.setDisable(true);
           textCuenta.setDisable(true);
           btnRegistrar.setDisable(true);
           aceptarTerminos.setDisable(true);
    } 
    
}
