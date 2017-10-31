/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package libros.gui.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;

/**
 * FXML Controller class
 *
 * @author iker
 */
public class CambiarPassController implements Initializable {

    @FXML
    private PasswordField contraAnt;
    @FXML
    private PasswordField nuevaContra;
    @FXML
    private PasswordField nuevaContra2;
    @FXML
    private Button btnAtras;
    @FXML
    private Button btnCambiarPass;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
