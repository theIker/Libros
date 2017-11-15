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
import libros.datos.mana.LibrosManager;



/**
 * FXML Controller class
 *
 * @author iker
 */
public class UsuController implements Initializable {
    private ComprasManager comprasManager;
    private LibrosManager librosManager;
    private Collection<ComprasBean> historial;
    
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
   
    
    public void cambiarPass(ActionEvent event) throws IOException{
         Stage reg = new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/CambiarPass.fxml"));
        
        Parent root =(Parent)loader.load();
        
        CambiarPassController controller= ((CambiarPassController) loader.getController());
        controller.setStage(reg);
        controller.initStage(root);
       
        
        
    }
    
    
  
    
    
    
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
                alert2.showAndWait();
               // stage=(Stage) btnBorrarUsu.getScene().getWindow();
               // stage.close();
            FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/LoginFinal.fxml"));
        
            Parent root =(Parent)loader.load();

            LoginFinalController controller= ((LoginFinalController) loader.getController());
            controller.setComprasManager(comprasManager);
            controller.setStage(stage);
            controller.initStage(root);

         }
            
             

                
        
    }
    @FXML
    public void salirLogOut(ActionEvent event) throws IOException{
          FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/LoginFinal.fxml"));
        
        Parent root =(Parent)loader.load();
        
        LoginFinalController controller= ((LoginFinalController) loader.getController());
        controller.setComprasManager(comprasManager);
        controller.setStage(stage);
        controller.initStage(root);
        
    }

    
    
   public void setStage(Stage stage, String e) {
        this.stage=stage;
        lblUsu.setText(e);
    }

   public void initStage(Parent root) throws IOException {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        
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
        
         
        
    }

    public void setComprasManager(ComprasManager comprasManager) {
        this.comprasManager=comprasManager;
    }
    
    public void setLibrosManager(LibrosManager lib){
        this.librosManager=lib;
    }
    
    public void setHistorial(Collection<ComprasBean> historial){
        this.historial=historial;
        
        ObservableList<ComprasBean>comprasData;
        comprasData=FXCollections.observableArrayList(this.historial);
        tableHisto.setItems(comprasData);
    }
    
    

  
    
    
  
    
}
