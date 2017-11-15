/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import libros.datos.mana.ComprasManager;
import libros.datos.mana.GenerosManager;
import libros.datos.mana.LibrosManager;

/**
 * FXML Controller class
 *
 * @author iker
 */
public class LoginFinalController implements Initializable {
    private Stage stage;
    private GenerosManager gen;
    private LibrosManager lib;
    private ComprasManager comprasManager;
    
    @FXML
    private TextField textFieldNombreU;
    @FXML
    private PasswordField textFieldContra;
    @FXML
    private Button btnLogin;
    @FXML
    private Hyperlink HyperLinkRegistro;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     public void setComprasManager(ComprasManager comprasManager) {
        this.comprasManager=comprasManager;
    }
    
     public void setGenManager(GenerosManager generoManager) {
        this.gen=generoManager;
    }

    public void setLibroManager(LibrosManager libroManager) {
        this.lib=libroManager;
    }
    

    @FXML
    private void entrar(ActionEvent event) throws IOException {
        
        if(textFieldNombreU.getText().equals("u") && textFieldContra.getText().equals("u")){
              
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/Usu.fxml"));
        
        Parent root =(Parent)loader.load();
        
        UsuController controller= ((UsuController) loader.getController());
        controller.setComprasManager(comprasManager);
        controller.setLibrosManager(lib);
        controller.setStage(stage,textFieldNombreU.getText());
        controller.initStage(root);
       
        }
        
        else if(textFieldNombreU.getText().equals("a")& textFieldContra.getText().equals("a")){
              
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/admin.fxml"));
        
        Parent root =(Parent)loader.load();
        
        AdminController controller= ((AdminController) loader.getController());
        controller.setStage(stage);
        controller.setGenManager(gen);
        controller.setLibroManager(lib);
        
        controller.initStage(root);
        }
        
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Usuario/contraseña incorrecta");
                alert.showAndWait();
        }
    }

    @FXML
    private void registrar(ActionEvent event) throws IOException {
        Stage reg = new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/registro.fxml"));
        
        Parent root =(Parent)loader.load();
        
       RegistroController controller= ((RegistroController) loader.getController());
        controller.setStage(reg);
        controller.initStage(root);
    }

    public void setStage(Stage stage) {
          this.stage=stage;
    }
    
    public void initStage(Parent root){
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnShowing(this::handleWindowShowing);
        stage.show();
        
    }
    
    public void handleWindowShowing(WindowEvent event){
        //se ejecuta antes de iniciar la ventana
         
        
    }

   
    
    
}
