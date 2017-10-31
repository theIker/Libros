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
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author iker
 */
public class AdminController implements Initializable {
   private Stage stage;
    @FXML
    private TextField TextIsbn;
    @FXML
    private TextField TextTitulo;
    @FXML
    private TextField TextAutor;
    @FXML
    private TextField TextEditorial;
    @FXML
    private TextField TextDescripcion;
    @FXML
    private TextField TextPrecio;
    @FXML
    private TextField TextStock;
    @FXML
    private DatePicker dateFechaPUb;
    @FXML
    private Button btnInsertar;
    @FXML
    private ComboBox<?> comboGeneros;
    @FXML
    private Button btnaddGen;
    @FXML
    private TextArea textAreaGeneros;
    @FXML
    private Button btnlimpGeneros;
    @FXML
    private TextField TextIsbn1;
    @FXML
    private TextField TextTitulo1;
    @FXML
    private TextField TextAutor1;
    @FXML
    private TextField TextEditorial1;
    @FXML
    private TextField TextDescripcion1;
    @FXML
    private TextField TextPrecio1;
    @FXML
    private TextField TextStock1;
    @FXML
    private DatePicker dateFechaPub1;
    @FXML
    private ComboBox<?> comboGeneros1;
    @FXML
    private Button btnaddGen1;
    @FXML
    private TextArea textAreaGeneros1;
    @FXML
    private Button btnlimpGeneros1;
    @FXML
    private Button btnBUscar;
    @FXML
    private TextField textGenero;
    @FXML
    private Button btnInsertgen;
    @FXML
    private TableView<?> tablaGeneros;
    @FXML
    private Button btnBorrar;

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
