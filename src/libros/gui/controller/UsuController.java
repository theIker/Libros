/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Optional;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import libros.datos.beans.ComprasBean;
import libros.datos.beans.LibroBean;
import libros.datos.beans.UserBean;
import libros.datos.exceptions.CompraException;
import libros.datos.manager.ComprasManager;
import libros.datos.manager.GenerosManager;
import libros.datos.manager.LibrosManager;

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
    private final static Logger logger = Logger.getLogger("libros.gui.controller");
    private ArrayList<LibroBean> compras = new ArrayList<>();
    private UsuController usu;

    private Stage stage;

    @FXML
    private Label lblUsu;
    @FXML
    private Tab tabLibro;
    @FXML
    private Tab tabUsuario;
    @FXML
    private Tab tabCarrito;
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
    //confirmarcompra
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
    @FXML
    private Button btnUpdate;
    @FXML
    private TextField tfUnidades;
    @FXML
    private Button btnElim;
    
    private Button compra;

    private ArrayList<ComprasBean> histo = new ArrayList<ComprasBean>();
    //confirmarcompra

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Se inicia el stage y carga la ventana de Busqueda libro en una pestaña de
     * usuario
     *
     * @param root
     * @throws IOException
     */
    public void initStage(Parent root) throws IOException {
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Usuario");
        stage.setResizable(false);
        stage.centerOnScreen();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/libros/gui/ui/BusquedaLibro.fxml"));
        Parent pane = null;
        pane = loader.load();

        BusquedaLibroController controller = ((BusquedaLibroController) loader.getController());
        controller.setUsuController(this);
        controller.setLibroManager(librosManager);
        controller.setComprasManager(comprasManager);

        tabLibro.setContent(pane);
        //se carga la ventana busquedaLibro en un panel del menu Tab

        //carga el historial de compras del uuario
        cargarTabla();

    }

    /**
     * Recoge el stage
     *
     * @param stage
     * @param e
     */
    public void setStage(Stage stage, String e) {
        this.stage = stage;
        lblUsu.setText(e);
    }

    /**
     * Se envian las compras a esta clase
     *
     * @param comprasManager
     */
    public void setComprasManager(ComprasManager comprasManager) {
        this.comprasManager = comprasManager;
    }

    /**
     * Se envian los libros a esta clase
     *
     * @param lib
     */
    public void setLibrosManager(LibrosManager lib) {
        this.librosManager = lib;
    }

    /**
     * Se envian los generos a esta clase
     *
     * @param gen
     */
    void setGenerosManager(GenerosManager gen) {

        this.generosManager = gen;
    }

    /**
     * se cargan datos en el historial de compras
     *
     * @param historial
     */
    public void setHistorial(Collection<ComprasBean> historial) {
        this.historial = historial;

        ObservableList<ComprasBean> comprasData;
        comprasData = FXCollections.observableArrayList(this.historial);
        tableHisto.setItems(comprasData);
        //se insertan las compras en la tabla historial de compras
    }

    /**
     * Se inicia la ventana para cambiar contraseñs
     *
     * @param event
     * @throws IOException
     */
    public void cambiarPass(ActionEvent event) throws IOException {
        Stage reg = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/libros/gui/ui/CambiarPass.fxml"));

        Parent root = (Parent) loader.load();
        logger.info("Abriendo ventana para cambiar contraseña(CambiarPass)");
        CambiarPassController controller = ((CambiarPassController) loader.getController());
        controller.setStage(reg);
        controller.initStage(root);
    }

    /**
     * Metodo para actualizar los datos del usuario
     *
     * @param event
     */
    @FXML
    public void actualizarUsu(ActionEvent event) {

        if (!(textFieldNombre.getText().equals("") || textFieldApel1.getText().equals("")
                || textFieldApel2.getText().equals("") || textFieldEmail.getText().equals("")
                || textFieldDir.getText().equals("") || textFieldNumCuenta.getText().equals("")
                || textFieldTfno.getText().equals(""))) {

            if (textFieldEmail.getText().matches("^[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,4}$")) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Hecho");
                alert.setContentText("Usuario modificado");
                logger.info("Usuario modificado");
                alert.showAndWait();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Email no valido");
                alert.showAndWait();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Rellena todos los campos");
            alert.showAndWait();
        }

    }

    /**
     * Se borra el usuario con el que estemos conectados
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void deleteUsuario(ActionEvent event) throws IOException {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Dialogo de confirmación");
        alert.setHeaderText(lblUsu.getText());
        alert.setContentText("¿Estas seguro de esto?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {

            Alert alert2 = new Alert(Alert.AlertType.WARNING);
            alert2.setTitle("Borrado");
            alert2.setContentText("Usuario borrado");
            logger.info("Usuario Borrado");
            alert2.showAndWait();
            // stage=(Stage) btnBorrarUsu.getScene().getWindow();
            // stage.close();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/libros/gui/ui/LoginFinal.fxml"));

            Parent root = (Parent) loader.load();

            LoginFinalController controller = ((LoginFinalController) loader.getController());
            controller.setComprasManager(comprasManager);
            controller.setLibroManager(librosManager);
            controller.setGenManager(generosManager);
            controller.setStage(stage);
            controller.initStage(root);

        }

    }

    /**
     * Desconectarse de la ventana usuario
     *
     * @param event
     * @throws IOException
     */
    @FXML
    public void salirLogOut(ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/libros/gui/ui/LoginFinal.fxml"));

        Parent root = (Parent) loader.load();
        logger.info("Saliendo y abriendo la ventana Login");
        LoginFinalController controller = ((LoginFinalController) loader.getController());
        controller.setComprasManager(comprasManager);
        controller.setLibroManager(librosManager);
        controller.setGenManager(generosManager);
        controller.setStage(stage);
        controller.initStage(root);

    }
    @FXML
    public void eliminarHistorial(){
        if(tableHisto.getSelectionModel().getSelectedItem() != null){
            try {
                //se borra la compra seleccionada
                comprasManager.deleteCompra(tableHisto.getSelectionModel().getSelectedItem());
                logger.info("borrando compra");
                cargarTabla();
            } catch (CompraException ex) {
                 logger.severe("Fallo con el servidor");
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Fallo con el servidor");
                alert.show();
            }
        }
        else{
              //en el caso de no selecionar compra para borrar
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Selecciona compra");
            alert.showAndWait();
        }
    }

    /**
     * Metodo para cargar la tabla historial compras
     */
    private void cargarTabla() {
        SimpleDateFormat dt = new SimpleDateFormat("dd/MM/yyy");
        try {
            Collection<ComprasBean> pur = comprasManager.getAllCompras("u");
            //recibe las compras del usuario u ya que es el unico que he implementado
            pur.forEach((c) -> {
                String fecha;
                if (c.getFechaCompra() == null) {
                    Date date = new Date();
                    fecha = dt.format(date);
                } else {
                    fecha = c.getFechaCompra().substring(8, 10) + "/" + c.getFechaCompra().substring(5, 7) + "/" + c.getFechaCompra().substring(0, 4);

                }
                c.setFechaCompra(fecha);
            });
            //bucle para poner el formato de la fecha de forma correcta

            tbTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));
            tbISBN.setCellValueFactory(new PropertyValueFactory<>("isbn"));
            tbFecha.setCellValueFactory(new PropertyValueFactory<>("fechaCompra"));
            tbTotal.setCellValueFactory(new PropertyValueFactory<>("precioTotal"));
            tbUnidades.setCellValueFactory(new PropertyValueFactory<>("unidades"));

            ObservableList<ComprasBean> comprasData = null;
            comprasData = FXCollections.observableArrayList(pur);

            tableHisto.setItems(comprasData);
            //Historial de compras 
            tableHisto.refresh();
            tableHisto.setColumnResizePolicy((param) -> true);

        } catch (CompraException ex) {
            logger.severe("Fallo con el servidor");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Fallo con el servidor");
            alert.show();
        }

    }

    /**
     * Metodo para cambiar las unidades de un libro en el carrito de la compra
     */
    @FXML
    private void cambiarUnidades() {
        try {
            if (!(Integer.valueOf(tfUnidades.getText()) <= 0) && tablaCompra.getSelectionModel().getSelectedItem() != null) {
                LibroBean lb = new LibroBean();
                lb = tablaCompra.getSelectionModel().getSelectedItem();
                compras.remove(lb);
                lb.setStock(Integer.valueOf(tfUnidades.getText()));
                compras.add(lb);
                //se carga otra vez la tabla del carrito para visualizar las nuevas unidades de ese libro
                cargarTablaCompras();

            } else {
                //en el caso de no insertar unidades
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setContentText("Inserta Unidades");
                alert.showAndWait();
            }
        } catch (NumberFormatException ex) {
            //en el caso de no introducir un numero valido
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Campo numerico");
            alert.showAndWait();
        }

    }

    //confirmar compra
    /**
     * Metodo para borrar compras del carrito
     */
    @FXML
    private void quitarCompra() {
        if (tablaCompra.getSelectionModel().getSelectedItem() != null) {
            //borra un libro del carrito
            compras.remove(tablaCompra.getSelectionModel().getSelectedItem());
            logger.info("Borrando linea compra");

            cargarTablaCompras();

        } else {
            //en el caso de no selecionar libro para borrar
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
     * Metodo que confirma la compra y se guarda en la base de datos
     */
    @FXML
    private void confirmarCompra() {
        BusquedaLibroController controller = new BusquedaLibroController();
        Date date = new Date();
        UserBean user = new UserBean();
        user.setUsuario("u");
        user.setNombre("Manu");
        user.setApellidos("Carrillo");
        user.setEmail("manu@gmail.com");
        user.setDireccion("Mexico");
        user.setTelefono("123456789");
        //este es el usuario definido en la base de datos
        try {
            histo = (ArrayList<ComprasBean>) comprasManager.getAllCompras(user.getUsuario());
            //se reciben las compras del usuario
            if (compras.size() > 0) {
                //en el caso de que compras sea mayor que 0 quiere decir que el carro esta lleno y se puede comprar

                for (int i = 0; i < compras.size(); i++) {

                    ComprasBean comp = new ComprasBean();
                    comp.setFechaCompra(date.toString());
                    comp.setPrecioTotal(compras.get(i).getPrecio() * compras.get(i).getStock());
                    comp.setUnidades(compras.get(i).getStock());
                    comp.setBook(compras.get(i));
                    comp.setUsuario(user);
                    try {
                        comprasManager.createCompras(comp);
                        //se van insertando compras 
                       
                    } catch (CompraException ex) {
                        logger.severe("Fallo con el servidor");
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setContentText("Fallo con el servidor");
                        alert.show();
                    }

                }
                
                      logger.info("Compra realizada y guardada");
                        //compras de busquedalibrocontroller se limpian
                        controller.resetCompras();
                        //compras de usucontroller se limpian
                        compras.clear();
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Hecho");
                        alert.setContentText("Compra realizada");
                        alert.show();
                        //se carga tabla de carrito
                        cargarTablaCompras();
                        //se carga tabla historial compras
                        cargarTabla();

            } else {
                //en el caso de que compras sea menor que 0 
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ERROR");
                alert.setContentText("Carrito vacio");
                alert.show();
            }
        } catch (CompraException ex) {
            logger.severe("Fallo con el servidor");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setContentText("Fallo con el servidor");
            alert.show();
        }

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
        //se añaden los datosal carrito
        tablaCompra.refresh();
        tablaCompra.setColumnResizePolicy((param) -> true);

    }
    //confirmar compra

    void setCompras(ArrayList<LibroBean> compras) {

        this.compras = compras;
        cargarTablaCompras();
    }

    void setUsuController(UsuController usu) {
        this.usu = usu;
    }

}
