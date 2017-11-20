/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Iker Iglesias
 */
public class CambiarPassController implements Initializable {
    private Stage stage;
    private final static Logger logger= Logger.getLogger("libros.gui.controller");
    
    @FXML
    private PasswordField contraAnt;
    @FXML
    private PasswordField nuevaContra;
    @FXML
    private PasswordField nuevaContra2;
    @FXML
    private Button btnCambiarPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     /**
      * Recoge el stage
      * @param stage 
      */
         public void setStage(Stage stage) {
        this.stage=stage;
    }

         /**
          * Metodo que inicia el stage
          * @param root 
          */
   public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnShowing(this::handleWindowShowing);
        stage.show();
    }
    
   /**
    * Se ejecuta al iniciar la ventana
    * @param event 
    */
    public void handleWindowShowing(WindowEvent event){
           //se ejecuta antes de iniciar la ventana
            stage.setResizable(false);
            stage.setTitle("Cambiar contraseña");
    } 
    
    /**
     * Metodo que valida que el cambio de contraseña sea correcta
     * @param even
     * @throws IOException 
     */
    @FXML
    public void cambiarPass(ActionEvent even) throws IOException{
         if(!(contraAnt.getText().equals("")||nuevaContra.getText().equals("")||nuevaContra2.getText().equals(""))){
             if(contraAnt.getText().equals("u")){
                 
                 if(nuevaContra.getText().equals(nuevaContra2.getText())){
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Hecho");
                    alert.setContentText("Contraseña cambiada");
                    logger.info("Contraseña cambiada");
                    alert.showAndWait();
                    
                     Stage y=(Stage) btnCambiarPass.getScene().getWindow();
                     y.close();

                 }
                 else {
                      Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("La nueva contraseña no coincide");
                alert.showAndWait();
                     
                 }
                 
             }
             else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("La contraseña antigua no coincide");
                alert.showAndWait();
             }
             
         }
         
         else{
               Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Rellena los campos");
                alert.showAndWait();
         }
        
        
    }

   
    
}
