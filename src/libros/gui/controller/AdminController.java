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
import java.util.Collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
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
import libros.datos.exceptions.BusquedaLibroException;
import libros.datos.exceptions.GeneroException;
import libros.datos.exceptions.LibroException;
import libros.datos.manager.GenerosManager;
import libros.datos.manager.LibrosManager;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;


/**
  * Clase que define los manejadores de eventos de la interfaz definida mediante
 *  el archivo admin.fmxl: una UI para mantenimiento de datos de libros. 
 * @author Jon Xabier Gimenez
 */
public class AdminController implements Initializable {
    
    //Referencia para el objeto Ventana de la UI que controla esta clase
    private Stage stage;
    //Referencia para el objeto de la capa de lógica 
    private GenerosManager generosManager;
    //Referencia para el objeto de la capa de lógica 
    private LibrosManager lib;
    //Referencia para cargar un libro desde otro controlador
    private LibroBean libro;
    private final static Logger logger = Logger.getLogger("libros.gui.controller");

    
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

    
    public void setLibro(LibroBean libro) {
        this.libro = libro;
    }
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    /**
     *
      *Metodo para cambiar la scene al stage de AdminController. 
     * @param root El objeto padre que representa el nodo raíz/root del gráfico de vista.
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.centerOnScreen();
        stage.setTitle("Administrador");
        //Codificar comportamiento
        btnBorrar.setDisable(true);
        btnModi.setDisable(true);
        TextIsbn.requestFocus();
        try{
            //Carga las combos con los generos.
            ObservableList<String> list = FXCollections.observableArrayList(generosManager.getNombresGenero(generosManager.getAllGeneros()));
            comboGeneros.setItems(list);
            comboGeneros1.setItems(list);
        }catch(GeneroException e){
            logger.severe(e.getMessage());
            logger.severe("Fallo al coger los datos de los generos");
         }
        
        stage.show();
        logger.info("Despues de mostrar ventana Administrador");
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    void setGenManager(GenerosManager gen) {
        this.generosManager = gen;
    }

    void setLibroManager(LibrosManager lib) {
        this.lib = lib;
    }

    /**
     * Metodo que soporta el onAction del btnInsertar.
     * Metodo en el cual se inserta un libro en la Base de Datos. Controlando los posibles fallos.
     */
    @FXML
    public void handlerInsertarLibro() {
        //Controla que no quede ningun campo en blanco
       if (TextIsbn.getText().equals("") || TextAutor.getText().equals("") || TextDescripcion.getText().equals("")
                || TextEditorial.getText().equals("") || TextPrecio.getText().equals("") || TextStock.getText().equals("")
                || TextTitulo.getText().equals("") || comboGeneros.getSelectionModel().getSelectedIndex() == -1) {
           //En caso de quedar algun campo se muestra una alerta informando
            Alert alert = new Alert(Alert.AlertType.ERROR, "Rellena todos los campos");
            alert.showAndWait();
        } else {
            try {
                //Cargo un LibroBean con todos los datos
                LibroBean aux = new LibroBean(TextIsbn.getText(), TextTitulo.getText(), TextAutor.getText(),
                        TextEditorial.getText(), TextDescripcion.getText(), dateFechaPUb.getValue().toString(), Float.parseFloat(TextPrecio.getText().replace(',', '.')),
                        Integer.parseInt(TextStock.getText()), new GeneroBean(comboGeneros.getSelectionModel().getSelectedIndex()+1, ((String) comboGeneros.getSelectionModel().getSelectedItem()))); 
                //Iterator para recorrer todos los libros que contengan parte del isbn del libro actual
                for (Iterator it = lib.getLibrosIsbn(TextIsbn.getText()).iterator(); it.hasNext();) {
                    LibroBean e = (LibroBean) it.next();
                    if(e.getIsbn().equals(TextIsbn.getText())){
                        //En el caso de tener mismo ISBN provoco la excepcion
                        throw new BusquedaLibroException();
                    }
                }
               
                //llamada para crear el libro 
                lib.createLibro(aux);
                logger.info("Libro  insertado");
                //Una vez insertado se procede a limpiar todos los campos
                TextIsbn.setText("");
                TextAutor.setText("");
                TextDescripcion.setText("");
                TextStock.setText("");
                TextPrecio.setText("");
                TextTitulo.setText("");
                dateFechaPUb.setValue(null);
                TextEditorial.setText("");
                comboGeneros.getSelectionModel().clearSelection();
                //Se informa de que todo ha ido bien
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Libro Insertado");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                //Si el precio o stock no son numericos vendra a esta excepcion
                Alert alert = new Alert(Alert.AlertType.ERROR, "Precio y stock deben ser numéricos");
                alert.showAndWait();
            } catch (NullPointerException ex) {
                //Al no insertar un formato valido en el datePicker vendra a esta excepcion
                Alert alert = new Alert(Alert.AlertType.ERROR, "Revisa la fecha");
                alert.showAndWait();
            }catch (LibroException ex){
                //Aqui entrara cuando tenga cualquier problema al crear el libro
                Alert alert = new Alert(Alert.AlertType.ERROR, "Fallo al crear el libro");
                alert.showAndWait();
            }catch(BusquedaLibroException ex){
                 Alert alert = new Alert(Alert.AlertType.ERROR, "ISBN repetido");
                alert.showAndWait();
            }
        }
    }
    /**
     * Metodo que escucha por teclado en el btnInsertar
     * y cuando se pulsa espacio sobre este hace una llamada al metodo handlerInsertarLibro()
     * @param event Evento que sucede cuando se pulsa una tecla cuando el foco esta en el btnInsertar
     */
    @FXML
    public void handlerInsertarLibro2(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            handlerInsertarLibro();
        }
    }
    /**
     * Metodo que soporta el onAction del btnBUscar.
     * En el cual se abre el documento BusquedaLibro.fxml en otro stage.
     * 
     */
    @FXML
    public void handlerBuscar() {
        try{
            //Se inicia un stage y se carga el fxml
            Stage reg = new Stage();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/libros/gui/ui/BusquedaLibro.fxml"));
            Parent root = (Parent) loader.load();
            logger.info("Abriendo Ventana Busqueda Libro");
            //Se le pasa todo lo necesario al Controlador del otro fxml
            BusquedaLibroController controller = ((BusquedaLibroController) loader.getController());
            controller.setLibroManager(lib);
            controller.setStage(reg);
            controller.setAdminController(this);
            controller.initStage(root);         
            logger.info("Despues de Buscar(Ventana)");
        }catch(IOException ex){
            //Aqui entrara cuando tenga algun problema al cargar la ventana
            logger.severe(ex.getMessage());
             Alert alert = new Alert(Alert.AlertType.ERROR, "Fallo al abrir la ventana BusquedaLibro");
             alert.showAndWait();
        }
    }
    /**
     * Metodo que escucha por teclado en el btnBUscar
     * y cuando se pulsa espacio sobre este hace una llamada al metodo handlerBuscar()
     * @param event Evento que sucede cuando se pulsa una tecla cuando el foco esta en el btnInsertar
     */
    @FXML
    public void handlerBuscar2(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            handlerBuscar();
        }
    }
    /**
     * Metodo que soporta el onAction del btnModi.
     * Metodo en el cual se actualiza un libro en la Base de Datos. Controlando los posibles fallos.
     */
    @FXML
    public void handlerModificar() {
        //Se comprueba que no hay ningun campo vacio
        if (TextIsbn1.getText().equals("") || TextAutor1.getText().equals("") || TextDescripcion1.getText().equals("")
                || TextEditorial1.getText().equals("") || TextPrecio1.getText().equals("") || TextStock1.getText().equals("")
                || TextTitulo1.getText().equals("") || comboGeneros1.getSelectionModel().getSelectedIndex() == -1) {
            //En el caso de estar algun campo en blanco se le informa mediante un error
            Alert alert = new Alert(Alert.AlertType.ERROR, "Rellena todos los campos");
            alert.showAndWait();
        } else {
            try {
                //Se carga un LibroBean con los datos finales
                LibroBean modificado = new LibroBean(TextIsbn1.getText(), TextTitulo1.getText(), TextAutor1.getText(),
                        TextEditorial1.getText(), TextDescripcion1.getText(), dateFechaPub1.getValue().toString(), Float.parseFloat(TextPrecio1.getText().replace(',', '.')),
                        Integer.parseInt(TextStock1.getText()),new GeneroBean(comboGeneros1.getSelectionModel().getSelectedIndex()+1, ((String) comboGeneros.getSelectionModel().getSelectedItem())));
                //Se actualiza el libro en la BD
                lib.updateLibro(modificado);
                logger.info("Disco  modificado");
                //Llamada a metodo que deja en blanco los campos 
                limpiarModificar();
                //Se informa al usuario que todo ha ido bien
                Alert alert = new Alert(Alert.AlertType.INFORMATION, "Libro Modificado");
                alert.showAndWait();
            } catch (NumberFormatException e) {
                //Si el precio o stock no son numericos vendra a esta excepcion
                Alert alert = new Alert(Alert.AlertType.ERROR, "Precio y stock deben ser numéricos");
                alert.showAndWait();
            } catch (NullPointerException ex) {
                //Al no insertar un formato valido en el datePicker vendra a esta excepcion
                Alert alert = new Alert(Alert.AlertType.ERROR, "Revisa la fecha");
                alert.showAndWait();
            }catch(LibroException e){
                 //Aqui entrara cuando tenga cualquier problema al hacer la modificacion
                 Alert alert = new Alert(Alert.AlertType.ERROR, "Fallo de servidor");
                 alert.showAndWait();
            }
        }
    }
    /**
     * Metodo que escucha por teclado en el btnModi
     * y cuando se pulsa espacio sobre este hace una llamada al metodo handlerModificar()
     * @param event Evento que sucede cuando se pulsa una tecla cuando el foco esta en el btnInsertar
     */
    @FXML
    public void handlerModificar2(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            handlerModificar();
        }
    }
    /**
     * Metodo que soporta el onAction del btnBorrar.
     * Metodo en el cual se borra un libro en la Base de Datos. Controlando los posibles fallos.
     */
    @FXML
    public void handlerBorrar() {
        //Confirmo que realmente lo quiere borrar
        Alert alert = new Alert(AlertType.WARNING,
                "Seguro que quiere borrar el libro?",
                ButtonType.YES, ButtonType.NO);
        //Guarda el resultado de la alerta anterior
        Optional<ButtonType> result = alert.showAndWait();
        //Si el resultado es afirmativo
        if (result.get() == ButtonType.YES) {
            try {
                //Borra el libro
                lib.deleteLibro(libro);
                logger.info("Disco borrado");
                limpiarModificar();
            } catch (LibroException ex) {
                //Si hay algun problema al borrar saltara esta excepcion
                logger.severe(ex.getMessage());
                logger.severe("Fallo con el servidor");
                alert = new Alert(Alert.AlertType.ERROR, "Fallo de servidor");
                alert.showAndWait();
            }
        }
    }
    /**
     * Metodo que escucha por teclado en el btnBorrar
     * y cuando se pulsa espacio sobre este hace una llamada al metodo handlerBorrar()
     * @param event Evento que sucede cuando se pulsa una tecla cuando el foco esta en el btnInsertar
     */
    @FXML
    public void handlerBorrar2(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            handlerBorrar();
        }
    }
    /**
     * Limpia en la pestaña de Modificar/Borrar libro una vez se 
     * modifica o se borra un libro.
     */
    private void limpiarModificar() {
        //Deja todos lso campos en blanco
        TextIsbn1.setText("");
        TextAutor1.setText("");
        TextDescripcion1.setText("");
        TextStock1.setText("");
        TextPrecio1.setText("");
        TextTitulo1.setText("");
        dateFechaPub1.setValue(null);
        TextEditorial1.setText("");
        comboGeneros1.getSelectionModel().clearSelection();
        //Deshabilita los campos 
        TextAutor1.setDisable(true);
        TextTitulo1.setDisable(true);
        TextEditorial1.setDisable(true);
        TextPrecio1.setDisable(true);
        TextStock1.setDisable(true);
        TextDescripcion1.setDisable(true);
        comboGeneros1.setDisable(true);
        dateFechaPub1.setDisable(true);
        //Deshabilita los botones
        btnBorrar.setDisable(true);
        btnModi.setDisable(true);
    }
    /**
     * Metodo el cual carga un libroBean en el Ventana
     * @param libro LibroBean seleccionado en otra ventana BusquedaLibro
     */
    void cargarLibro(LibroBean libro) {
        this.libro = libro;
        //Se habilitan los campos
        TextAutor1.setDisable(false);
        TextTitulo1.setDisable(false);
        TextEditorial1.setDisable(false);
        TextPrecio1.setDisable(false);
        TextStock1.setDisable(false);
        TextDescripcion1.setDisable(false);
        comboGeneros1.setDisable(false);
        dateFechaPub1.setDisable(false);
        //Se habilitan los botones
        btnBorrar.setDisable(false);
        btnModi.setDisable(false);
        //Se cargan los datos del libro
        TextIsbn1.setText(libro.getIsbn());
        TextAutor1.setText(libro.getAutor());
        TextEditorial1.setText(libro.getEditorial());
        TextPrecio1.setText(libro.getPrecio().toString().replace('.', ','));
        TextStock1.setText(libro.getStock().toString());
        TextDescripcion1.setText(libro.getDescripcion());
        TextTitulo1.setText(libro.getTitulo());
        //Se da formato a la fecha antes de cargarla
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        LocalDate localDate = LocalDate.parse(libro.getFechaPub(), formatter);
        dateFechaPub1.setValue(localDate);
        //Se selecciona el genero del libro en la combo
        comboGeneros1.getSelectionModel().select(libro.getGenero().getGenero());
        //Pido el focus en la comboBox
        comboGeneros1.requestFocus();
        logger.info("Despues de cargar el libro");
    }
    
    /**
     * Metodo encargado de cargar la tabla de Generos y actualizar las comboBox de Generos
     * Comentada ya que la pestaña de Insertar/Borrar generos no esta en uso.
     */
    private void cargarTabla() {
        try{   
            ObservableList<String> list = FXCollections.observableArrayList(generosManager.getNombresGenero(generosManager.getAllGeneros()));
            comboGeneros.setItems(list);
            comboGeneros1.setItems(list);
            ObservableList<GeneroBean> lista = FXCollections.observableArrayList(generosManager.getAllGeneros());
            tableGenero.setCellValueFactory(new PropertyValueFactory<>("Genero"));
            tablaGeneros.setItems(lista);
            tablaGeneros.setColumnResizePolicy((param) -> true);
            }catch(GeneroException e){
            }
    }
    
    
    /**
     * Metodo utilizado para insertar genero en un pasado.
     * Ahora mismo esta inaccesible
     */
    @FXML
    public void insertarGenero() {
        /*if (!generosManager.getNombresGenero().contains((String) textGenero.getText())) {
            generosManager.getAllGeneros().add(new GeneroBean(generosManager.getAllGeneros().size() + 1, (String) textGenero.getText()));
            cargarTabla();
            logger.info("Genero insertado");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Genero Introducido");
            alert.showAndWait();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Genero existente");
            alert.showAndWait();
        }*/
    }
    /**
     * Metodo que escucha por teclado en el btnInsertgen
     * y cuando se pulsa espacio sobre este hace una llamada al metodo insertarGenero()
     * Ahora mismo esta inaccesible
     * @param event Evento que sucede cuando se pulsa una tecla cuando el foco esta en el btnInsertar
     */
    @FXML
    public void insertarGenero2(KeyEvent event) {
        if (event.getCode() == KeyCode.SPACE) {
            insertarGenero();
        }
    }
    /**
     * Metodo utilizado para borrar genero en un pasado.
     * Ahora mismo esta inaccesible
     */
    @FXML
    public void borrarGenero() {
       /* if (tablaGeneros.getSelectionModel().getSelectedIndex() != -1) {
            generosManager.getAllGeneros().remove(tablaGeneros.getSelectionModel().getSelectedItem());
            cargarTabla();
            logger.info("Genero borrado");
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Genero borrado");
            alert.showAndWait();

        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "Selecciona un genero");
            alert.showAndWait();
        }*/
    }
    /**
     * Metodo que escucha por teclado en el btnBorrar2
     * y cuando se pulsa espacio sobre este hace una llamada al metodo handlerBorrar()
     * Ahora mismo esta inaccesible
     * @param event Evento que sucede cuando se pulsa una tecla cuando el foco esta en el btnInsertar
     */
    @FXML
    public void borrarGenero2(KeyEvent event) {
        /*if (event.getCode() == KeyCode.SPACE) {
            borrarGenero();
        }*/
    }

}