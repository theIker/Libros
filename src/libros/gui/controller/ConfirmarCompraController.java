/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import libros.datos.beans.ComprasBean;
import libros.datos.beans.LibroBean;
import libros.datos.mana.ComprasManager;

/**
 * FXML Controller class
 *
 * @author iker
 */
public class ConfirmarCompraController implements Initializable {
    private UsuController usu= new UsuController();
    private Stage stage;
    @FXML
    private Button btnConfirmarCompra;
    @FXML
    private TableView<LibroBean> tablaCompra;
    @FXML
     private TableColumn colTitulo;
    @FXML
     private TableColumn colAutor;
    @FXML
     private TableColumn colEditorial;
    @FXML
     private TableColumn colPrecio;
    @FXML
     private TableColumn colUnidades;
    @FXML
    private Button btnQuitarCompra;
    
    
    
    private Button compra;
    private ArrayList<LibroBean>compras= new ArrayList<LibroBean>();
    private ArrayList<ComprasBean>histo=new ArrayList<ComprasBean>();
    private ComprasManager compMana;
    
   


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
          
    }    

    
    @FXML
   private void quitarCompra(ActionEvent event){
        if(tablaCompra.getSelectionModel().getSelectedItem()!=null){
            
            compras.remove(tablaCompra.getSelectionModel().getSelectedItem());
            cargarTablaComras();
            
        }
        else{
            
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Selecciona compra");
                alert.showAndWait();
            
        }
   }
   
   
    @FXML
    private void confirmarCompra(ActionEvent event) {
       
        
        BusquedaLibroController controller= new BusquedaLibroController();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
          
          histo=(ArrayList<ComprasBean>) compMana.getAllCompras();
         
        
       for(int i=0;i<compras.size();i++){
            
            ComprasBean comp = new ComprasBean(String.valueOf(i) , compras.get(i).getIsbn(), dateFormat.format(date),compras.get(i).getTitulo(), compras.get(i).getStock()*compras.get(i).getPrecio(), compras.get(i).getStock());
       
      
            histo.add(comp);
       }
      
       
       usu.setHistorial(histo);
       
        
        
        controller.resetCompras();
        compras.clear();
                 Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Hecho");
                    alert.setContentText("Compra realizada");
                    alert.showAndWait();
                    
                     Stage y=(Stage) btnConfirmarCompra.getScene().getWindow();
                     y.close();
                     compra.setVisible(false);
    }
    
 

   public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnShowing(this::handleWindowShowing);
        stage.show();
    }
    
    public void handleWindowShowing(WindowEvent event){
           
       
      cargarTablaComras();

    } 

   

    void setStage(Stage reg, Button btnComprar, ArrayList<LibroBean> compras) {
         this.stage=reg;
        this.compra=btnComprar;
        this.compras=compras;
    }

    private void cargarTablaComras() {
          colTitulo.setCellValueFactory(new PropertyValueFactory<> ("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<> ("autor"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<> ("editorial"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<> ("precio"));
        colUnidades.setCellValueFactory(new PropertyValueFactory<>("stock"));
        
         ObservableList<LibroBean> list=FXCollections.observableArrayList(compras);
            
            tablaCompra.setItems(list); 
            tablaCompra.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
    }

    void setUsuController(UsuController usu) {
      this.usu=usu;
    }

    void setComprasManager(ComprasManager comprasManager) {
        this.compMana=comprasManager;
    }

   
    
}
