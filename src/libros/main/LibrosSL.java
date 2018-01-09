/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.main;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import libros.datos.manager.ComprasManager;
import libros.datos.manager.ComprasManagerDataGenerator;
import libros.datos.manager.GenerosManager;
import libros.datos.manager.GenerosManagerTestDataGenerator;
import libros.datos.manager.LibrosManager;
import libros.datos.manager.LibrosManagerImplementation;
import libros.gui.controller.LoginFinalController;

/**
 *
 * @author Iker Iglesias, Jon Xabier Gimenez
 */
public class LibrosSL extends Application {

    /**
     * Inicia la aplicación
     *
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        GenerosManager generoManager
                = new GenerosManagerTestDataGenerator();
        LibrosManager libroManager
                = new LibrosManagerImplementation();
        ComprasManager comprasManager
                = new ComprasManagerDataGenerator();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/libros/gui/ui/LoginFinal.fxml"));

        Parent root = (Parent) loader.load();

        LoginFinalController controller = ((LoginFinalController) loader.getController());
        controller.setStage(stage);
        controller.setGenManager(generoManager);
        controller.setLibroManager(libroManager);
        controller.setComprasManager(comprasManager);
        controller.initStage(root);

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
