/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.ResourceBundle;
import java.util.stream.Collectors;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import libros.datos.beans.GeneroBean;
import libros.datos.beans.LibroBean;
import libros.datos.mana.LibrosManager;

/**
 * FXML Controller class
 *
 * @author ubuntu
 */
public class BusquedaLibroController implements Initializable {
    private Stage stage;
    private LibrosManager librosManager;
    private AdminController x;
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
    @FXML
    private TableColumn isbn;
    @FXML
    private TableColumn titulo;
    @FXML
    private TableColumn autor; 
    @FXML
    private TableColumn editorial; 
    @FXML
    private TableColumn precio; 
      @FXML
    private TableColumn fechaP; 
    @FXML
    private TableColumn descripcion; 
    
    
    
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

     public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnShowing(this::handleWindowShowing);
        stage.showAndWait();
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
           comboBusqueda.setPromptText("Criterio");
           textFieldBusqueda.setPromptText("Parametro de Busqueda");
           stage.setTitle("Busqueda Libro");
           
          ObservableList<LibroBean> list=FXCollections.observableArrayList(librosManager.getAllLibros());
          this.cargarTabla(list);
    } 

    
    
    
    
    
    
    @FXML
    private void buscar(ActionEvent event) {
         ObservableList<LibroBean> lista = null;
      if(comboBusqueda.getSelectionModel().getSelectedIndex()!=-1){
          if(comboBusqueda.getSelectionModel().getSelectedIndex()==0){
            lista=FXCollections.observableArrayList(librosManager.getLibrosIsbn((String)textFieldBusqueda.getText()));
           
         }else if(comboBusqueda.getSelectionModel().getSelectedIndex()==1){
            lista=FXCollections.observableArrayList(librosManager.getLibrosTitulo((String)textFieldBusqueda.getText()));
        }else if(comboBusqueda.getSelectionModel().getSelectedIndex()==2){
            lista=FXCollections.observableArrayList(librosManager.getLibrosAutor((String)textFieldBusqueda.getText()));
       }
           cargarTabla(lista);
      }
       
       
      
    }
    
    @FXML
    private void cargarLibro(ActionEvent event) {
        if(tablaBusqueda.getSelectionModel().getSelectedIndex()==-1){
             Alert alert = new Alert(Alert.AlertType.ERROR, "Seleccione un libro");
             alert.showAndWait(); 
        }else{
         LibroBean libro= tablaBusqueda.getSelectionModel().getSelectedItem();
         x.cargarLibro(libro);
         textFieldBusqueda.setText("");
        comboBusqueda.setValue("");
         stage.close();
        }
       
        
    }
    @FXML
    private void comprarLibro(ActionEvent event) {
    }
    
    public void setStage(Stage stage){
        this.stage=stage;
    }
   
    private void cargarTabla(ObservableList<LibroBean> list) {
       if(list !=null){
           isbn.setCellValueFactory(new PropertyValueFactory<> ("isbn"));
      titulo.setCellValueFactory(new PropertyValueFactory<> ("titulo"));
      autor.setCellValueFactory(new PropertyValueFactory<> ("autor"));
      editorial.setCellValueFactory(new PropertyValueFactory<> ("editorial"));
      precio.setCellValueFactory(new PropertyValueFactory<> ("precio"));
      fechaP.setCellValueFactory(new PropertyValueFactory<> ("fechaPub"));
      descripcion.setCellValueFactory(new PropertyValueFactory<> ("descripcion"));
      
       
   
      tablaBusqueda.setItems(list); 
      tablaBusqueda.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
       }
    }

    void setLibroManager(LibrosManager lib) {
        this.librosManager=lib;
    }
    
    void setAdminController(AdminController x){
        this.x=x;
    }
    
}


