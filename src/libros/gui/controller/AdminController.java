/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package libros.gui.controller;


import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;



import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import libros.datos.beans.GeneroBean;
import libros.datos.beans.LibroBean;
import libros.datos.mana.GenerosManager;
import libros.datos.mana.LibrosManager;

/**
 * FXML Controller class
 *
 * @author Jon Xabier Gimenez
 */


public class AdminController implements Initializable {
   private Stage stage;
   private GenerosManager generosManager;
   private LibrosManager lib;
   private LibroBean libro;
   private final static Logger logger= Logger.getLogger("libros.gui.controller");
  
   //InsertarLibro
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
    private ComboBox comboGeneros;

    //ModificarBorrarDisco
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
    private ComboBox comboGeneros1;
    @FXML
    private Button btnBUscar;
    @FXML
    private Button btnModi;
    @FXML
    private Button btnBorrar;
   
    //InsertarBorrarGenero
     @FXML
    private TextField textGenero;
    @FXML
    private Button btnInsertgen;
    @FXML
    private TableView<GeneroBean> tablaGeneros;
     @FXML
    private Button btnBorrar2;
   
   
    @FXML
    private TableColumn tableGenero;
    
    /**
     * Se envia libroBean  esta clase
     * @param libro 
     */
    public void setLibro(LibroBean libro){
        this.libro=libro;
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    
    /**
     * Se inicia el stage
     * @param root 
     */
     public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);  
         stage.setResizable(false);
        //Codificar comportamiento
        btnBorrar.setDisable(true);
        btnModi.setDisable(true);
        TextIsbn.requestFocus();
        
        stage.setTitle("Administrador");
        
        cargarTabla();
        stage.show();
        logger.info("Despues de mostrar ventana Administrador");
    }
    /**
     * Recoge el stage
     * @param stage 
     */
        public void setStage(Stage stage) {
        this.stage=stage;
    }
   
        /**
         * Se envian los generos a esta clase
         * @param gen 
         */
    void setGenManager(GenerosManager gen) {
        this.generosManager=gen;
    }
     
    /**
     * se envian los Libros a esta clase
     * @param lib 
     */
    void setLibroManager(LibrosManager lib) {
        this.lib=lib;
    }
    
  
    /**
     * Metodo en el cual se inserta un libro
     */
    @FXML
    public void insertarLibro(){
        if(TextIsbn.getText().equals("")||TextAutor.getText().equals("")||TextDescripcion.getText().equals("")
                || TextEditorial.getText().equals("")||TextPrecio.getText().equals("")||TextStock.getText().equals("")
                ||TextTitulo.getText().equals("")||comboGeneros.getSelectionModel().getSelectedIndex()==-1){
                
            Alert alert = new Alert(Alert.AlertType.ERROR, "Rellena todos los campos");
                   alert.showAndWait(); 
            
        }else{
            
            try{
                LibroBean aux= new LibroBean(TextIsbn.getText(),TextTitulo.getText(),TextAutor.getText(),
                TextEditorial.getText(),TextDescripcion.getText(),dateFechaPUb.getValue().toString(),Float.parseFloat(TextPrecio.getText()),
                Integer.parseInt(TextStock.getText()),((String)comboGeneros.getSelectionModel().getSelectedItem()));      
                lib.getAllLibros().add(aux);
                logger.info("Libro  insertado");
                limpiarInsertar();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Libro Insertado");
                alert.showAndWait(); 
            }catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Precio y stock deben ser numéricos");
               alert.showAndWait(); 
            }catch(NullPointerException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Revisa la fecha");
               alert.showAndWait();        
             }  
        }
}
    
    /**
     * Metodo insertar() pero por teclado
     * @param event 
     */
    @FXML
    public void insertarLibro2(KeyEvent event){
    if(event.getCode() == KeyCode.SPACE) {
          insertarLibro();
     }
}
    
    /**
     * Metodo en el cual se abre la busqueda de libro y se carga en la ventana de admin
     * @throws IOException 
     */
    @FXML
    public void buscar() throws IOException{
        Stage reg = new Stage();
        FXMLLoader loader =new FXMLLoader(getClass().getResource("/libros/gui/ui/BusquedaLibro.fxml"));
        
        Parent root =(Parent)loader.load();
        logger.info("Abriendo Ventana Busqueda Libro");
       BusquedaLibroController controller= ((BusquedaLibroController) loader.getController());
        controller.setLibroManager(lib);
        controller.setStage(reg);
        controller.setAdminController(this);
        controller.initStage(root);
        logger.info("Despues de Buscar(Ventana)");
     
    }
    /**
     * Metodo buscar() pero por teclado
     * @param event
     * @throws IOException 
     */
      @FXML
    public void buscar2(KeyEvent event) throws IOException{
    if(event.getCode() == KeyCode.SPACE) {
          buscar();
     }
}
    /**
     * 
     * modifica el libro cargado
     */
    @FXML
    public void modificar(){
        if(TextIsbn1.getText().equals("")||TextAutor1.getText().equals("")||TextDescripcion1.getText().equals("")
                || TextEditorial1.getText().equals("")||TextPrecio1.getText().equals("")||TextStock1.getText().equals("")
                ||TextTitulo1.getText().equals("")||comboGeneros1.getSelectionModel().getSelectedIndex()==-1){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Rellena todos los campos");
            alert.showAndWait(); 
        }else{  
            try{
                LibroBean modificado=new LibroBean(TextIsbn1.getText(),TextTitulo1.getText(),TextAutor1.getText(),
                TextEditorial1.getText(),TextDescripcion1.getText(),dateFechaPub1.getValue().toString(),Float.parseFloat(TextPrecio1.getText()),
                Integer.parseInt(TextStock1.getText()),((String)comboGeneros1.getSelectionModel().getSelectedItem()));
                lib.getAllLibros().remove(libro);
                lib.getAllLibros().add(modificado);
                logger.info("Disco  modificado");
                limpiarModificar();
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Libro Modificado");
                alert.showAndWait(); 
            }catch(NumberFormatException e){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Precio y stock deben ser numéricos");
               alert.showAndWait(); 
            }catch(NullPointerException ex){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Revisa la fecha");
               alert.showAndWait();         
             }
        }     
    }
    /**
     * Metodo modificar() pero por teclado
     * @param event 
     */
    @FXML
    public void modificar2(KeyEvent event){
    if(event.getCode() == KeyCode.SPACE) {
          modificar();
     }    
    }
    
    /**
     * Metodo en el cual se borra un libro
     */
    @FXML
    public void borrar(){
        Alert alert = new Alert(AlertType.WARNING, 
                        "Seguro que quiere borrar el libro?", 
                        ButtonType.YES, ButtonType.NO);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.YES){
              lib.getAllLibros().remove(libro);
              logger.info("Disco borrado");
              limpiarModificar();
        } else{
            
        }
    }
    
    /**
     * Metodo borrar() pero por teclado
     * @param event 
     */
    @FXML
    public void borrar2(KeyEvent event){
    if(event.getCode() == KeyCode.SPACE){
          borrar();
     }
 }
    
    /**
     * Metodo para insertar generos
     */
    @FXML
    public void insertarGenero(){
        if(!generosManager.getNombresGenero().contains((String) textGenero.getText())){
            generosManager.getAllGeneros().add(new GeneroBean(generosManager.getAllGeneros().size()+1,(String) textGenero.getText()));
            cargarTabla();
            logger.info("Genero insertado");
             Alert alert = new Alert(Alert.AlertType.INFORMATION, "Genero Introducido");
             alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Genero existente");
             alert.showAndWait();
        }   
    }
    
    /**
     * Metodo insertarGenero() pero por teclado
     * @param event 
     */
     @FXML
    public void insertarGenero2(KeyEvent event){
    if(event.getCode() == KeyCode.SPACE){
          insertarGenero();
     }
 }
    
    /**
     * Metodo para borrar generos
     */
    @FXML
    public void borrarGenero(){
       if(tablaGeneros.getSelectionModel().getSelectedIndex()!= -1){
         generosManager.getAllGeneros().remove(tablaGeneros.getSelectionModel().getSelectedItem());
         cargarTabla();
         logger.info("Genero borrado");
         Alert alert = new Alert(Alert.AlertType.INFORMATION, "Genero borrado");
         alert.showAndWait();
         
       }else{
           Alert alert = new Alert(Alert.AlertType.INFORMATION, "Selecciona un genero");
           alert.showAndWait();
       }  
    } 
    /**
     * Metodo borrarGenero() pero por teclado
     * @param event 
     */
     @FXML
    public void borrarGenero2(KeyEvent event){
    if(event.getCode() == KeyCode.SPACE){
          borrarGenero();
     }
 }

    /**
     * Carga la tabla generos y los combos que contienen generos
     */
    private void cargarTabla() {
       ObservableList<String> list=FXCollections.observableArrayList(generosManager.getNombresGenero());
       ObservableList<GeneroBean> lista=FXCollections.observableArrayList(generosManager.getAllGeneros());
    
      tableGenero.setCellValueFactory(new PropertyValueFactory<> ("Genero"));
      
      
      comboGeneros.setItems(list);
      comboGeneros1.setItems(list);
      tablaGeneros.setItems(lista);
      
      tablaGeneros.setColumnResizePolicy((param) -> true );
      
      
      
     
     
    }

    /**
     * Limpia la pestaña insertar una vez se ha insertado un libro
     */

    private void limpiarInsertar() {
        TextIsbn.setText("");
        TextAutor.setText("");
        TextDescripcion.setText("");
        TextStock.setText("");
        TextPrecio.setText("");
        TextTitulo.setText("");
        dateFechaPUb.setValue(null);
        TextEditorial.setText("");    
        comboGeneros.getSelectionModel().clearSelection();
    }
    
    /**
     * Limpia en la pestaña de modificar una vez se haya modificado
     */
    
    private void limpiarModificar(){
        TextIsbn1.setText("");
        TextAutor1.setText("");
        TextDescripcion1.setText("");
        TextStock1.setText("");
        TextPrecio1.setText("");
        TextTitulo1.setText("");
        dateFechaPub1.setValue(null);
        TextEditorial1.setText("");    
        comboGeneros1.getSelectionModel().clearSelection();
        
        TextAutor1.setDisable(true);
        TextTitulo1.setDisable(true);
        TextEditorial1.setDisable(true);
        TextPrecio1.setDisable(true);
        TextStock1.setDisable(true);
        TextDescripcion1.setDisable(true);
        comboGeneros1.setDisable(true);
        dateFechaPub1.setDisable(true);
        
        btnBorrar.setDisable(true);  
        btnModi.setDisable(true);
    }

    /** 
     * 
     * Carga un libro que se ha seleccionado en otra ventana (BusquedaLibro)
     */
    void cargarLibro(LibroBean libro) {
            this.libro=libro;
            
           TextAutor1.setDisable(false);
           TextTitulo1.setDisable(false);
           TextEditorial1.setDisable(false);
           TextPrecio1.setDisable(false);
           TextStock1.setDisable(false);
           TextDescripcion1.setDisable(false);
           comboGeneros1.setDisable(false);
           dateFechaPub1.setDisable(false);
           
           btnBorrar.setDisable(false);  
           btnModi.setDisable(false);
           
           
           TextIsbn1.setText(libro.getIsbn());
           TextAutor1.setText(libro.getAutor());
           TextEditorial1.setText(libro.getEditorial());
           TextPrecio1.setText(libro.getPrecio().toString());
           TextStock1.setText(libro.getStock().toString());
           TextDescripcion1.setText(libro.getDescripcion());
           TextTitulo1.setText(libro.getTitulo());
           String s=libro.getFechaPub();  
           String fecha;
           fecha=s.substring(8,10)+"/"+s.substring(5,7)+"/"+s.substring(0,4);
           
           DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
           LocalDate localDate = LocalDate.parse(fecha,formatter);
           dateFechaPub1.setValue(localDate);
           
           comboGeneros1.getSelectionModel().select(libro.getGenero());     
           
           comboGeneros1.requestFocus();
           
           logger.info("Despues de cargar el libro");
    }
    
    
}