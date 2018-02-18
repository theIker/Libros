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
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import libros.datos.beans.LibroBean;
import libros.datos.exceptions.BusquedaLibroException;
import libros.datos.manager.ComprasManager;
import libros.datos.manager.LibrosManager;
import java.text.DecimalFormat;
import libros.datos.beans.ComprasBean;

import java.awt.Dialog;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import libros.datos.beans.LibroBean;
import libros.datos.exceptions.BusquedaLibroException;
import libros.datos.manager.ComprasManager;
import libros.datos.manager.LibrosManager;
import java.text.NumberFormat;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;


/**
 * Clase que define los manejadores de eventos de la interfaz definida mediante
 * el archivo BusquedaLibro.fmxl: una UI de busqueda de libros. Tambien utilizada
 * para introducir compras y carga de datos.
 * @author Iker Iglesias, Jon Xabier Gimenez
 */
public class BusquedaLibroController implements Initializable {
    
    //Referencia para el objeto Ventana de la UI que controla esta clase
    private Stage stage;
     //Referencia para el objeto de la capa de lógica 
    private LibrosManager librosManager;
     //Referencia para el objeto de la capa de lógica 
    private ComprasManager comprasManager;
    //Referencia para el controlador de la ventana ejecutandose(USUARIO)
    private UsuController usu = new UsuController();
     //Referencia para el controlador de la ventana ejecutandose en el fondo(ADMIN)
    private AdminController adminControl = new AdminController();
    private ArrayList<LibroBean> compras = new ArrayList<>();
    private ArrayList<ComprasBean> c = new ArrayList<>();
    private final static Logger logger = Logger.getLogger("libros.gui.controller");
  

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
    @FXML
    private Button btnInforme;
    @FXML
    private Label lbUds;
    


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        comboBusqueda.getItems().addAll(
                "ISBN",
                "Titulo",
                "Autor",
                "Todos"
        );
        textFieldBusqueda.setPromptText("Parámetro de Búsqueda");
        comboBusqueda.setPromptText("Criterio");
    }

    /**
     * *Metodo para cambiar la scene al stage actual. 
     *
     * @param root El objeto padre que representa el nodo raíz/root del gráfico de vista.
     */
    public void initStage(Parent root) {

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnShowing(this::handleWindowShowing);
        stage.showAndWait();

    }

    /**
     * Metodo que inicializa la ventana. Deshabilita/Habilita los campos
     * @param event Evento provocado al iniciar la ventana(InitStage)
     */
    public void handleWindowShowing(WindowEvent event) {
        //se ejecuta antes de iniciar la ventana
        //Ajusta la visibilidad de los componentes
        stage.setResizable(false);
        btnCargar.setVisible(true);
        btnAdd.setVisible(false);
        textFieldUnidades.setVisible(false);
        stage.setTitle("Busqueda Libro");
        lbUds.setVisible(false);

    }

    public void setStage(Stage stage) {

        this.stage = stage;
    }

    /**
     * Inicializa el librosManager de esta ventana.
     * Aprovecha este para obtener todos los libros y cargarlos en la tabla.
     */
    void setLibroManager(LibrosManager lib) {
        this.librosManager = lib;
        try {
             ObservableList<LibroBean> list= FXCollections.observableArrayList(librosManager.getAllLibros());
              this.cargarTabla(list);
        } catch (BusquedaLibroException ex) {
            Logger.getLogger(BusquedaLibroController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }

    void setAdminController(AdminController admin) {
        this.adminControl = admin;
    }

    /**
     * Limpia el carrito de compras
     */
    void resetCompras() {
        compras.clear();
    }

    void setUsuController(UsuController aThis) {
        this.usu = aThis;
    }

    void setComprasManager(ComprasManager comprasManager) {

        this.comprasManager = comprasManager;
    }

    /**
     * Metodo en el que se añade al carrito un libro que hayamos seleccionado
     */
    @FXML
    private void anadirCompra() {
          boolean esta = false;
          LibroBean libro;
        try {
            if (!(Integer.valueOf(textFieldUnidades.getText())<=0) && tablaBusqueda.getSelectionModel().getSelectedItem() != null) {
                libro= new LibroBean();
                libro = tablaBusqueda.getSelectionModel().getSelectedItem();
                libro.setStock(Integer.valueOf(textFieldUnidades.getText()));
                
               
                for (int i = 0; i < compras.size(); i++) {
                    
                    if (compras.get(i).getIsbn().equals(libro.getIsbn())) {
                      
                        esta = true;
                        
                        break;
                    }
                  
                
                }
                    if(!esta){
                    compras.add(libro);
                        usu.setUsuController(usu);
            usu.setCompras(compras);
            usu.setComprasManager(comprasManager);
            
                        
                logger.info("Añadido al carro");
                textFieldUnidades.setText("");
                        
                        
                }
                    else{
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Ese libro ya esta en el carro");
                alert.showAndWait();
                    }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Inserta unidades");
                alert.showAndWait();
            }
        } catch (NumberFormatException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Campo numerico");
            alert.showAndWait();
        }

    }

    /**
     * Metodo anadirCompra() por teclado
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void anadirCompra2(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.SPACE) {
            anadirCompra();
        }
    }

    /**
     * Metodo que soporta el onAction del btnBuscar.
     * Metodo en el cual se busca un libro en la Base de Datos, teniendo en cuenta los parametros de busqueda.
     */
    @FXML
    private void buscar(){
        ObservableList<LibroBean> lista = null;
        //Se comprueba que hay algo seleccionado en la comboBox
        if (comboBusqueda.getSelectionModel().getSelectedIndex() != -1) {
            try{
                //Se aplica el filtro correspondiente
                if (comboBusqueda.getSelectionModel().getSelectedIndex() == 0 && !textFieldBusqueda.getText().equals("")) {
                    lista = FXCollections.observableArrayList(librosManager.getLibrosIsbn((String) textFieldBusqueda.getText().toLowerCase()));
                } else if (comboBusqueda.getSelectionModel().getSelectedIndex() == 1 && !textFieldBusqueda.getText().equals("")) {
                    lista = FXCollections.observableArrayList(librosManager.getLibrosTitulo((String) textFieldBusqueda.getText().toLowerCase()));
                } else if (comboBusqueda.getSelectionModel().getSelectedIndex() == 2 && !textFieldBusqueda.getText().equals("")) {
                    lista = FXCollections.observableArrayList(librosManager.getLibrosAutor((String) textFieldBusqueda.getText().toLowerCase()));
                } else if (comboBusqueda.getSelectionModel().getSelectedIndex() == 3 || textFieldBusqueda.getText().equals("")) {
                    lista = FXCollections.observableArrayList(librosManager.getAllLibros());
                }
                cargarTabla(lista);
                logger.info("Busqueda realizada");
            }catch(BusquedaLibroException e){
                 //Fallo en la busqueda del libro
                 Alert alert = new Alert(Alert.AlertType.ERROR);
                 alert.setTitle("Error");
                 alert.setContentText("Fallo en la busqueda del libro");
                 alert.showAndWait();
            }
           } else {
                //Cuando escribe un texto para filtrar pero no ha elegido un criterio
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Selecciona criterio de busqueda");
                alert.showAndWait();
        }
    }

    /**
     *
     * Metodo que escucha por teclado en el btnBuscar
     * y cuando se pulsa espacio sobre este hace una llamada al metodo buscar()
     * @param event Evento que sucede cuando se pulsa una tecla cuando el foco esta en el btnBuscar
     * @throws IOException 
     */
    @FXML
    public void buscar2(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.SPACE) {
            buscar();
        }
    }

    /**
     * Metodo que soporta el onAction del btnCargar.
     * Sirve para cargar el libro en la ventana controlado por AdminController
     * Ademas cierra la ventana actual.
     */
    @FXML
    private void cargarLibro() {
        if (tablaBusqueda.getSelectionModel().getSelectedItem() != null) {
            LibroBean aux = tablaBusqueda.getSelectionModel().getSelectedItem();
            //Llamada al controlador de la ventana de fondo para coger el libro,
            adminControl.cargarLibro(aux);
            logger.info("Libro cargado en administrador(Ventana)");
            stage.close();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Selecciona libro");
            alert.showAndWait();
        }
    }

    /**
     * Metodo que escucha por teclado en el btnCargar
     * y cuando se pulsa espacio sobre este hace una llamada al metodo  cargarLibro()
     * @param event Evento que sucede cuando se pulsa una tecla cuando el foco esta en el btnBuscar
     * @throws IOException
     */
    @FXML
    public void cargarLibro2(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.SPACE) {
            cargarLibro();
        }
    }

    /**
     * Metodo que nos abre otra pestaña para ver el carrito de compra y
     * confirmar la compra
     *
     * @throws IOException
     */
    @FXML
    private void comprarLibro() throws IOException {
        if (compras.size() > 0) {
            usu.setUsuController(usu);
            usu.setCompras(compras);
            usu.setComprasManager(comprasManager);
        } else {
            btnComprar.setVisible(false);
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Selecciona libro");
            alert.showAndWait();

        }
    }

    /**
     * Metodo comprarLibro() por teclado
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void comprarLibro2(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.SPACE) {
            comprarLibro();
        }
    }

    /**
     * Carga el ObservableList recibido en la TableView de la ventana
     * @param list Lista de LibroBean a mostrar
     */
    private void cargarTabla(ObservableList<LibroBean> list) {
        if (list != null) {
            for(LibroBean e: list){
                //Se da un formato legible a las fechas 
                String fecha = e.getFechaPub().substring(8, 10) + "/" + e.getFechaPub().substring(5, 7) + "/" + e.getFechaPub().substring(0, 4);
                e.setFechaPub(fecha);      
            } 
            //Se le asigna a cada celda un valor a mostrar
            isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
            editorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
            precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            fechaP.setCellValueFactory(new PropertyValueFactory<>("fechaPub"));
            descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
            //Se le asigna el ObservableList a la tabla
            tablaBusqueda.setItems(list);
            //Quita posibilidad de cambiar el tamaño a las columnas
            tablaBusqueda.setColumnResizePolicy((param) -> true);
        }
    }
    
    /**
     * Metodo que se ocupa de hacer un reporte detallado de los libros
     * cargados actualmente en la tabla
     */
    @FXML
    private void handleImprimirLibros(){
        try {
            JasperReport report=JasperCompileManager.compileReport(getClass().getResourceAsStream("/libros/gui/report/jasperReport.jrxml"));
            //Data for the report: a collection of LibroBean passed as a JRDataSource 
            //implementation 
            JRBeanCollectionDataSource dataItems=new JRBeanCollectionDataSource((Collection<LibroBean>)this.tablaBusqueda.getItems());
            //Map of parameter to be passed to the report
            Map<String,Object> parameters=new HashMap<>();
            //Fill report with data
            JasperPrint jasperPrint = JasperFillManager.fillReport(report,parameters,dataItems);
            //Create and show the report window.
            logger.info("Mostrando Informe Jasper");
            JasperViewer jasperViewer = new JasperViewer(jasperPrint,false);
            jasperViewer.setTitle("Informe Libros");
            jasperViewer.setVisible(true);
            logger.info("Despues de imprimir informe");
         } catch (JRException ex) {
                Alert alert=new Alert(Alert.AlertType.ERROR,ex.getMessage(),ButtonType.OK);
                alert.getDialogPane().getStylesheets().add(getClass().getResource("/libros/gui/ui/Custom.css").toExternalForm());
                alert.showAndWait();
        }
    }
    

}
