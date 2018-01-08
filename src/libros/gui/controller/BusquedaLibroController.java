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
 * @author Iker Iglesias, Jon Xabier Gimenez
 */
public class BusquedaLibroController implements Initializable {

    private Stage stage;
    private LibrosManager librosManager;
    private ComprasManager comprasManager;
    private UsuController usu = new UsuController();
    private AdminController x = new AdminController();
    private ArrayList<LibroBean> compras = new ArrayList<LibroBean>();
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
        textFieldBusqueda.setPromptText("Parámetro de Búsqueda");
        comboBusqueda.setPromptText("Criterio");
    }

    /**
     * Inicia el stage
     *
     * @param root
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
     * @param event metodo que se llama al iniciar la ventana(InitStage)
     */
    public void handleWindowShowing(WindowEvent event) {
        //se ejecuta antes de iniciar la ventana
        stage.setResizable(false);
        btnCargar.setVisible(true);
        btnAdd.setVisible(false);
        btnComprar.setVisible(false);
        textFieldUnidades.setVisible(false);
        stage.setTitle("Busqueda Libro");

    }

    /**
     *
     * @param stage Coge la stage utilizada por la ventana anterior
     */
    public void setStage(Stage stage) {

        this.stage = stage;
    }

    /**
     * Pasa los liros generados a esta clase y los carga en una tabla
     *
     * @param lib
     */
    void setLibroManager(LibrosManager lib) {
        this.librosManager = lib;
        ObservableList<LibroBean> list = FXCollections.observableArrayList(librosManager.getAllLibros());
        this.cargarTabla(list);
    }

    /**
     * Se envia el controllador del administrador
     */
    void setAdminController(AdminController x) {
        this.x = x;
    }

    /**
     * Limpia el carrito de compras
     */
    void resetCompras() {
        compras.clear();
    }

    /**
     * se envia el controlador del usuario
     *
     * @param aThis
     */
    void setUsuController(UsuController aThis) {
        this.usu = aThis;
    }

    /**
     * Se envian las compras a esta clase
     *
     * @param comprasManager
     */
    void setComprasManager(ComprasManager comprasManager) {

        this.comprasManager = comprasManager;
    }

    /**
     * Metodo en el que se añade al carrito un libro que hayamos seleccionado
     */
    @FXML
    private void anadirCompra() {
        boolean esta = false;
        try {
            if (!(textFieldUnidades.getText().equals("")) && tablaBusqueda.getSelectionModel().getSelectedItem() != null) {

                LibroBean libro = tablaBusqueda.getSelectionModel().getSelectedItem();
                libro.setStock(Integer.valueOf(textFieldUnidades.getText()));
                for (int i = 0; i < compras.size(); i++) {
                    if (compras.get(i).getIsbn().equals(libro.getIsbn())) {
                        LibroBean x = compras.get(i);
                        compras.remove(compras.get(i));
                        x.setStock(x.getStock() + Integer.parseInt(textFieldUnidades.getText()));
                        compras.add(x);
                        esta = true;
                        break;
                    }
                }
                if (!esta) {
                    compras.add(libro);
                }
                  
            usu.setUsuController(usu);
            usu.setCompras(compras);
            usu.setComprasManager(comprasManager);
            
                    
                
            
                
                logger.info("Añadido al carro");
                textFieldUnidades.setText("");
             

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
     * Se encarga de buscar el libro dependiendo el filtro que le haya asignado
     * el usuario. Se ejecuta cuando se clica sobre el boton btnBuscar.
     */
    @FXML
    private void buscar() {
        ObservableList<LibroBean> lista = null;
        if (comboBusqueda.getSelectionModel().getSelectedIndex() != -1) {
            if (comboBusqueda.getSelectionModel().getSelectedIndex() == 0) {
                lista = FXCollections.observableArrayList(librosManager.getLibrosIsbn((String) textFieldBusqueda.getText().toLowerCase()));

            } else if (comboBusqueda.getSelectionModel().getSelectedIndex() == 1) {
                lista = FXCollections.observableArrayList(librosManager.getLibrosTitulo((String) textFieldBusqueda.getText().toLowerCase()));
            } else if (comboBusqueda.getSelectionModel().getSelectedIndex() == 2) {
                lista = FXCollections.observableArrayList(librosManager.getLibrosAutor((String) textFieldBusqueda.getText().toLowerCase()));
            }
            cargarTabla(lista);
            logger.info("Busqueda realizada");
        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Selecciona criterio de busqueda");
            alert.showAndWait();
        }
    }

    /**
     *
     * @param event
     * @throws IOException Hace una llamada al metodo buscar cuando se pulsa el
     * espacio y el elemento btnBuscar tiene el foco
     */
    @FXML
    public void buscar2(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.SPACE) {
            buscar();
        }
    }

    /**
     * Metodo para cargar el libro en la ventana de modificado del administrador
     */
    @FXML
    private void cargarLibro() {

        if (tablaBusqueda.getSelectionModel().getSelectedItem() != null) {
            LibroBean aux = tablaBusqueda.getSelectionModel().getSelectedItem();
            x.cargarLibro(aux);
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
     * Metodo cargarLibro() por teclado
     *
     * @param event
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
     *
     * @param list Recibe un ObservableList y se ocupa de mostrarlo en la
     * tableView (tablaBusqueda)
     */
    private void cargarTabla(ObservableList<LibroBean> list) {
        if (list != null) {
            isbn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            titulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            autor.setCellValueFactory(new PropertyValueFactory<>("autor"));
            editorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
            precio.setCellValueFactory(new PropertyValueFactory<>("precio"));
            fechaP.setCellValueFactory(new PropertyValueFactory<>("fechaPub"));
            descripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

            tablaBusqueda.setItems(list);
            tablaBusqueda.setColumnResizePolicy((param) -> true);
        }
    }

}
