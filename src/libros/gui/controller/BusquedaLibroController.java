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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    
    
    
    
    @FXML
   private void anadirCompra(){
       
     try{
          if(!(textFieldUnidades.getText().equals(""))&&tablaBusqueda.getSelectionModel().getSelectedItem()!=null){
       
       
        
        LibroBean libro=tablaBusqueda.getSelectionModel().getSelectedItem();
        libro.setStock(Integer.valueOf(textFieldUnidades.getText()));
       
        compras.add(libro);
        textFieldUnidades.setText("");
        btnComprar.setVisible(true);
        
       }
       else{
           Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Inserta unidades");
                alert.showAndWait();
       } 
     }catch(NumberFormatException e){
          Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Campo numerico");
                alert.showAndWait();
     }
         
   }
   
    @FXML
    public void anadirCompra2(KeyEvent event) throws IOException{
        if(event.getCode() == KeyCode.SPACE) {
         anadirCompra();
     }
    }
   
   /**
    * Se encarga de buscar el libro dependiendo el filtro que le haya asignado el usuario.
    * Se ejecuta cuando se clica sobre el boton btnBuscar.
    */
    
    @FXML
    private void buscar() {
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
    /**
     * 
     * @param event
     * @throws IOException 
     * Hace una llamada al metodo buscar cuando se pulsa el espacio y el elemento btnBuscar tiene el foco
     */
    
    @FXML
    public void buscar2(KeyEvent event) throws IOException{
        if(event.getCode() == KeyCode.SPACE) {
         buscar();
     }
    }
    
    @FXML
    private void cargarLibro() {
       
        LibroBean aux= tablaBusqueda.getSelectionModel().getSelectedItem();
        x.cargarLibro(aux);
        stage.close();
    }
    
    @FXML
    public void cargarLibro2(KeyEvent event) throws IOException{
        if(event.getCode() == KeyCode.SPACE) {
         buscar();
     }
    }

    @FXML
    private void comprarLibro() throws IOException {
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
    
     @FXML
    public void comprarLibro2(KeyEvent event) throws IOException{
        if(event.getCode() == KeyCode.SPACE) {
         comprarLibro();
     }
    }
    
    /**
     * 
     * @param stage 
     * Coge la stage utilizada por la ventana anterior
     */
    public void setStage(Stage stage){
      
        this.stage=stage;
    }
    
    /**
     * 
     * @param root
     * Se ejecuta cuando esta es iniciada como una ventana nueva
     */
    public void initStage(Parent root) {
        
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnShowing(this::handleWindowShowing);
        stage.showAndWait();
        
    }
    
 
    /**
     * 
     * @param event 
     * metodo que se llama al iniciar la ventana(InitStage)
     */
    public void handleWindowShowing(WindowEvent event){
           //se ejecuta antes de iniciar la ventana
           btnCargar.setVisible(true);
           btnAdd.setVisible(false);
           btnComprar.setVisible(false);
           textFieldUnidades.setVisible(false);
           stage.setTitle("Busqueda Libro");
        
          
    } 
    
    /**
     * 
     * @param list 
     * Recibe un ObservableList y se ocupa de mostrarlo en la tableView (tablaBusqueda)
     */

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
               tablaBusqueda.setColumnResizePolicy((param) -> true );
       }
    }


    
}


