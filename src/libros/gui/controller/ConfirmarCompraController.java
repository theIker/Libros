/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.logging.Logger;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import libros.datos.beans.ComprasBean;
import libros.datos.beans.LibroBean;
import libros.datos.mana.ComprasManager;

/**
 * FXML Controller class
 *
 * @author Iker Iglesias
 */
public class ConfirmarCompraController implements Initializable {

    private UsuController usu = new UsuController();
    private Stage stage;
    private final static Logger logger = Logger.getLogger("libros.gui.controller");

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
    private ArrayList<LibroBean> compras = new ArrayList<LibroBean>();
    private ArrayList<ComprasBean> histo = new ArrayList<ComprasBean>();
    private ComprasManager compMana;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Metodo que inicia el stage
     *
     * @param root
     */
    public void initStage(Parent root) {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setOnShowing(this::handleWindowShowing);
        
        stage.setResizable(false);
        stage.setTitle("Carrito compra");
        
        stage.show();
    }

    /**
     * Se ejecuta al iniciar la ventana
     *
     * @param event
     */
    public void handleWindowShowing(WindowEvent event) {
        

    }

    /**
     * Recoge el stage
     *
     * @param reg
     * @param btnComprar
     * @param compras
     */
    void setStage(Stage reg, Button btnComprar, ArrayList<LibroBean> compras) {
        this.stage = reg;
        this.compra = btnComprar;
        this.compras = compras;
    }

    /**
     * Se envia el controlador de usuario
     *
     * @param usu
     */
    void setUsuController(UsuController usu) {
        this.usu = usu;
    }

    /**
     * Se envian las compras a esta clase
     *
     * @param comprasManager
     */
    void setComprasManager(ComprasManager comprasManager) {
        this.compMana = comprasManager;
       
    }

    /**
     * Metodo para borrar compras del carrito
     */
    @FXML
    private void quitarCompra() {
        if (tablaCompra.getSelectionModel().getSelectedItem() != null) {

            compras.remove(tablaCompra.getSelectionModel().getSelectedItem());
            logger.info("Borrando linea compra");
            if (compras.size() == 0) {
                stage.close();
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setContentText("Carrito vacio, se ha cerrado la ventana");
                logger.info("Carro vacio cerrando ventana");
                alert.showAndWait();
                compra.setVisible(false);
            }
            cargarTablaCompras();

        } else {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Selecciona compra");
            alert.showAndWait();

        }
    }

    /**
     * Meotodo quitarCompra() por teclado
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void quitarCompra2(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.SPACE) {
            quitarCompra();
        }
    }

    /**
     * Metodo que confirma la compra
     */
    @FXML
    private void confirmarCompra() {
        BusquedaLibroController controller = new BusquedaLibroController();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();

        histo = (ArrayList<ComprasBean>) compMana.getAllCompras();

        for (int i = 0; i < compras.size(); i++) {

            ComprasBean comp = new ComprasBean(String.valueOf(i), compras.get(i).getIsbn(), dateFormat.format(date), compras.get(i).getTitulo(), compras.get(i).getStock() * compras.get(i).getPrecio(), compras.get(i).getStock());

            logger.info("Compra realizada y guardada");
            histo.add(comp);
        }

        usu.setHistorial(histo);

        controller.resetCompras();
        compras.clear();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Hecho");
        alert.setContentText("Compra realizada");
        alert.showAndWait();
        stage.close();
        compra.setVisible(false);
    }

    /**
     * Metodo confirmarCompra() por teclado
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void confirmarCompra2(KeyEvent event) throws IOException {
        if (event.getCode() == KeyCode.SPACE) {
            confirmarCompra();
        }
    }

    /**
     * Carga el carrito de compras en una tabla
     */
    private void cargarTablaCompras() {
        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
        colAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));
        colEditorial.setCellValueFactory(new PropertyValueFactory<>("editorial"));
        colPrecio.setCellValueFactory(new PropertyValueFactory<>("precio"));
        colUnidades.setCellValueFactory(new PropertyValueFactory<>("stock"));

        ObservableList<LibroBean> list = FXCollections.observableArrayList(compras);

        tablaCompra.setItems(list);
        tablaCompra.setColumnResizePolicy((param) -> true);

    }

    void setCompras(ArrayList<LibroBean> compras) {
        this.compras=compras;
        this.cargarTablaCompras();
    }

}
