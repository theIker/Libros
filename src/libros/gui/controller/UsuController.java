/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author iker
 */
public class UsuController implements Initializable {
    
    private Stage stage;

    @FXML
    private Label lblUsu;
    @FXML
    private Tab tabLibro;
    @FXML
    private Tab tabUsuario;
    @FXML
    private TextField textFieldNombre;
    @FXML
    private TextField textFieldApel1;
    @FXML
    private TextField textFieldApel2;
    @FXML
    private TextField textFieldDir;
    @FXML
    private TextField textFieldTfno;
    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldNumCuenta;
    @FXML
    private Button btnActualizar;
    @FXML
    private Button btnCambiarContra;
    @FXML
    private Button btnBorrarUsu;
    @FXML
    private Button btnSalir;
    @FXML
    private Tab tabCompra;
    @FXML
    private TableView<?> tableHisto;
    @FXML
    private Button btnActualizarHist;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

   public void setStage(Stage stage) {
        this.stage=stage;
    }

   public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnShowing(this::handleWindowShowing);
        stage.show();
    }
    
    public void handleWindowShowing(WindowEvent event){
           //se ejecuta antes de iniciar la ventana

    } 
    
}
