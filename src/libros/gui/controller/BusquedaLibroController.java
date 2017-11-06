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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import libros.datos.beans.LibroBean;

/**
 * FXML Controller class
 *
 * @author ubuntu
 */
public class BusquedaLibroController implements Initializable {
    private Stage stage;
    @FXML
    private ComboBox<String> comboBusqueda;
    @FXML
    private TextField textFieldBusqueda;
    @FXML
    private Button btnBuscar;
    @FXML
    private TableView<LibroBean> tablaBusqueda;
    @FXML
    private Button btnCargar;
    @FXML
    private Button btnComprar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    
    
    
    
    
    
    @FXML
    private void buscar(ActionEvent event) {
    }
    
    @FXML
    private void cargarLibro(ActionEvent event) {
    }

    @FXML
    private void comprarLibro(ActionEvent event) {
    }
    
    public void setStage(Stage stage){
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
           btnCargar.setVisible(true);
           btnComprar.setVisible(false);
           comboBusqueda.getItems().addAll(
            "ISBN",
            "Titulo",
            "Autor"
);
          // this.cargarTabla();
    } 
    
}


