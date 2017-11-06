/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import libros.datos.beans.GeneroBean;
import libros.datos.mana.GenerosManager;
import libros.datos.mana.LibrosManager;

/**
 * FXML Controller class
 *
 * @author iker
 */
public class AdminController implements Initializable {
   private Stage stage;
   private GenerosManager generosManager;
   private LibrosManager lib;
   
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
    private TableView<GeneroBean> tablaGeneros;
    @FXML
    private Button btnBorrar;
    @FXML
    private TableColumn tableGenero;

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
        //Codificar comportamiento
        
        
        cargarTabla();
        stage.show();
    }
    
   @FXML
    public void añadirGen(ActionEvent event){
        
    }
    
    @FXML
    public void limpiarGen(ActionEvent event){
        
    }
    
    @FXML
    public void insertarLibro(ActionEvent event){
        
    }
    
    @FXML
    public void buscar(ActionEvent event) throws IOException{
        Stage reg = new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/BusquedaLibro.fxml"));
        
        Parent root =(Parent)loader.load();
        
       BusquedaLibroController controller= ((BusquedaLibroController) loader.getController());
        controller.setLibroManager(lib);
        controller.setStage(reg);
        controller.initStage(root);
    }
   
    @FXML
    public void añadirGen2(ActionEvent event){
        
    }
    
    @FXML
    public void limpiarGen2(ActionEvent event){
        
    }
    
    @FXML
    public void borrar(ActionEvent event){
        
    }
    
    @FXML
    public void modificar(ActionEvent event){
        
    }
    
    @FXML
    public void insertarGenero(ActionEvent event){
        
    }
    
    @FXML
    public void borrarGenero(ActionEvent event){
        
    }

    private void cargarTabla() {
       tableGenero.setCellValueFactory(new PropertyValueFactory<> ("Genero"));
        
      ObservableList<GeneroBean> list=FXCollections.observableArrayList(generosManager.getAllGeneros());
       
        
      tablaGeneros.setItems(list);
     
    }

    void setGenManager(GenerosManager gen) {
        this.generosManager=gen;
    }

    void setLibroManager(LibrosManager lib) {
        this.lib=lib;
    }
}