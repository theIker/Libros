/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import libros.datos.beans.ComprasBean;
import libros.datos.beans.GeneroBean;
import libros.datos.beans.LibroBean;
import libros.datos.mana.ComprasManager;
import libros.datos.mana.LibrosManager;

/**
 * FXML Controller class
 *
 * @author ubuntu
 */
public class BusquedaLibroController implements Initializable {
    
    /*
    private Stage stage;
    private LibrosManager librosManager;
    private ComprasManager comprasManager;
    private UsuController usu= new UsuController();
    private AdminController x= new AdminController();
    
    */
    
    
       private Stage stage;
       private LibrosManager librosManager;
       private ComprasManager comprasManager;
       private UsuController usu= new UsuController();
       private AdminController x= new AdminController();
       private ArrayList<LibroBean>compras= new ArrayList<LibroBean>();
       private final static Logger logger= Logger.getLogger("libros.gui.controller");
    @FXML
    private ComboBox<String> comboBusqueda;
    @FXML
    private TextField textFieldBusqueda;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnAdd;
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
    @FXML
    private TextField textFieldUnidades;
   
    
   
    
    
    
      

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
          
          
       comboBusqueda.getItems().addAll(
            "ISBN",
            "Titulo",
            "Autor"
            );
      textFieldBusqueda.setPromptText("Parametro de Busqueda");
       
        
    }    

    
    
    
    
    
    @FXML
   private void anadirCompra(ActionEvent event){
       
     
       if(!(textFieldUnidades.getText().equals(""))&&tablaBusqueda.getSelectionModel().getSelectedItem()!=null){
       btnComprar.setVisible(true);
       
        
        LibroBean libro=tablaBusqueda.getSelectionModel().getSelectedItem();
        libro.setStock(Integer.valueOf(textFieldUnidades.getText()));
       
        compras.add(libro);
        textFieldUnidades.setText("");
        
       }
       else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Inserta unidades");
                alert.showAndWait();
       }
     
     
   
       
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
        
        else{
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Selecciona criterio de busqueda");
                alert.showAndWait();
            
        }
        
        
        
        
    }
    
    @FXML
    private void cargarLibro(ActionEvent event) {
       
        LibroBean aux= tablaBusqueda.getSelectionModel().getSelectedItem();
        x.cargarLibro(aux);
        stage.close();
    }

    @FXML
    private void comprarLibro(ActionEvent event) throws IOException {
     if(compras.size()>0){
		Stage reg = new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/confirmarCompra.fxml"));
        
        Parent root =(Parent)loader.load();
        
        ConfirmarCompraController controller= ((ConfirmarCompraController) loader.getController());
        controller.setUsuController(usu);
        controller.setStage(reg,btnComprar,compras);
        controller.setComprasManager(comprasManager);
        controller.initStage(root);
     }
     else{
         btnComprar.setVisible(false);
          Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Selecciona libro");
                alert.showAndWait();
         
     }
    }
    
    public void setStage(Stage stage){
      
        this.stage=stage;
       
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
           btnAdd.setVisible(false);
           btnComprar.setVisible(false);
           textFieldUnidades.setVisible(false);
        
          
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
         ObservableList<LibroBean> list=FXCollections.observableArrayList(librosManager.getAllLibros());
         this.cargarTabla(list);
    }
    
    void setAdminController(AdminController x){
        this.x=x;
    }
    
    void resetCompras(){
        compras.clear();
    }

    void setUsuController(UsuController aThis) {
        this.usu=aThis;
    }

    void setComprasManager(ComprasManager comprasManager) {
        
       this.comprasManager=comprasManager;
    }
    
}


