/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.io.IOException;
import java.net.URL;

import java.util.Collection;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import libros.datos.beans.ComprasBean;
import libros.datos.mana.ComprasManager;
import libros.datos.mana.GenerosManager;
import libros.datos.mana.LibrosManager;



/**
 * FXML Controller class
 *
 * @author Iker Iglesias
 */
public class UsuController implements Initializable {
    private ComprasManager comprasManager;
    private LibrosManager librosManager;
    private GenerosManager generosManager;
    private Collection<ComprasBean> historial;
    private final static Logger logger= Logger.getLogger("libros.gui.controller");
    
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
    private TableView<ComprasBean> tableHisto;
    @FXML
    private TableColumn tbISBN;
    @FXML
    private TableColumn tbTitulo;
    @FXML
    private TableColumn tbFecha;
    @FXML
    private TableColumn tbUnidades;
    @FXML
     private TableColumn tbTotal;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
   
    /**
     * Se inica el stage y carga la ventana de Busqueda libro en una pestaña de usuario
     * @param root
     * @throws IOException 
     */
   public void initStage(Parent root) throws IOException {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Usuario");
         stage.setResizable(false);
         FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/BusquedaLibro.fxml"));
          Parent pane = null; 
          pane = loader.load();
          BusquedaLibroController controller= ((BusquedaLibroController) loader.getController());
         controller.setUsuController(this);
          controller.setLibroManager(librosManager);
          controller.setComprasManager(comprasManager);
         
            tabLibro.setContent(pane);
            stage.show();
          
          cargarTabla();

       
    }
   /**
    * Recoge el stage
    * @param stage
    * @param e 
    */
   public void setStage(Stage stage, String e) {
        this.stage=stage;
        lblUsu.setText(e);
    }
   
   /**
    * Se envian las compras a esta clase
    * @param comprasManager 
    */
     public void setComprasManager(ComprasManager comprasManager) {
        this.comprasManager=comprasManager;
    }
    /**
     * Se envian los libros a esta clase
     * @param lib 
     */
    public void setLibrosManager(LibrosManager lib){
        this.librosManager=lib;
    }
    
    /**
     * Se envian los generos a esta clase
     * @param gen 
     */
    
      void setGenerosManager(GenerosManager gen) {
        
        this.generosManager=gen;
    }
    
      /**
       * se cargan datos en el historial de compras
       * @param historial 
       */
    public void setHistorial(Collection<ComprasBean> historial){
        this.historial=historial;
        
        ObservableList<ComprasBean>comprasData;
        comprasData=FXCollections.observableArrayList(this.historial);
        tableHisto.setItems(comprasData);
    }
    
    /**
     * Se inicia la ventana para cambiar contraseñs
     * @param event
     * @throws IOException 
     */
    public void cambiarPass(ActionEvent event) throws IOException{
         Stage reg = new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/CambiarPass.fxml"));
        
        Parent root =(Parent)loader.load();
        logger.info("Abriendo ventana para cambiar contraseña(CambiarPass)");
        CambiarPassController controller= ((CambiarPassController) loader.getController());
        controller.setStage(reg);
        controller.initStage(root);
    }
    
    /**
     * Metodo para actualizar los datos del usuario
     * @param event 
     */
    @FXML
    public void actualizarUsu(ActionEvent event){
        
        if(!(textFieldNombre.getText().equals("")||textFieldApel1.getText().equals("")||
                textFieldApel2.getText().equals("")||textFieldEmail.getText().equals("")||
                textFieldDir.getText().equals("")||textFieldNumCuenta.getText().equals("")||
                textFieldTfno.getText().equals(""))){
                
            if(textFieldEmail.getText().matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$")){
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Hecho");
                alert.setContentText("Usuario modificado");
                logger.info("Usuario modificado");
                alert.showAndWait();
                
            }
            else{
                  Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Email no valido");
                alert.showAndWait();
            }
            
        }
        
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Rellena todos los campos");
                alert.showAndWait();
        }
        
    }
    
    
    /**
     * Se borra el usuario con el que estemos conectados
     * @param event
     * @throws IOException 
     */
    @FXML
    public void deleteUsuario(ActionEvent event) throws IOException{
         Alert alert = new Alert(AlertType.CONFIRMATION);
         alert.setTitle("Dialogo de confirmación");
         alert.setHeaderText(lblUsu.getText());
         alert.setContentText("¿Estas seguro de esto?");

         Optional<ButtonType> result = alert.showAndWait();
         if (result.get() == ButtonType.OK){
             
                Alert alert2 = new Alert(Alert.AlertType.WARNING);
                alert2.setTitle("Borrado");
                alert2.setContentText("Usuario borrado");
                logger.info("Usuario Borrado");
                alert2.showAndWait();
               // stage=(Stage) btnBorrarUsu.getScene().getWindow();
               // stage.close();
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/LoginFinal.fxml"));
        
            Parent root =(Parent)loader.load();

            LoginFinalController controller= ((LoginFinalController) loader.getController());
            controller.setComprasManager(comprasManager);
            controller.setLibroManager(librosManager);
            controller.setGenManager(generosManager);
            controller.setStage(stage);
            controller.initStage(root);

         }
            
             

                
        
    }
    /**
     * Desconectarse de la ventana usuario
     * @param event
     * @throws IOException 
     */
    @FXML
    public void salirLogOut(ActionEvent event) throws IOException{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/LoginFinal.fxml"));
        
        Parent root =(Parent)loader.load();
        logger.info("Saliendo y abriendo la ventana Login");
        LoginFinalController controller= ((LoginFinalController) loader.getController());
        controller.setComprasManager(comprasManager);
        controller.setLibroManager(librosManager);
        controller.setGenManager(generosManager);
        controller.setStage(stage);
        controller.initStage(root);
        
    }

    
    
 
/**
 * Metodo para cargar la tabla historial compras
 */
    private void cargarTabla() {
        tbTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        tbISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        tbFecha.setCellValueFactory(new PropertyValueFactory<>("fechaComp"));
        tbTotal.setCellValueFactory(new PropertyValueFactory<>("precioTot"));
        tbUnidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));
        ObservableList<ComprasBean>comprasData;
        comprasData=FXCollections.observableArrayList(comprasManager.getAllCompras());
        
        tableHisto.setItems(comprasData);
        tableHisto.refresh();
        tableHisto.setColumnResizePolicy((param) -> true );
        
    
    }

  
 
}
