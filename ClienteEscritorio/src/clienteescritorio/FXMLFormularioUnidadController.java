/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Eric Jair
 */
public class FXMLFormularioUnidadController implements Initializable {

    @FXML
    private TextField tfVin;
    @FXML
    private TextField tfModelo;
    @FXML
    private TextField tfMarca;
    @FXML
    private Button btnGuardar;
    @FXML
    private Button btnCancelar;
    @FXML
    private Label lbModelo;
    @FXML
    private Label lbMarca;
    @FXML
    private Label lbAño;
    @FXML
    private Label lbVin;
    @FXML
    private Label lbNoIdentificacion;
    @FXML
    private Label lbTipoUnidad;
    @FXML
    private ComboBox<?> comboBTipoUnidad;
    @FXML
    private TextField tfMarca1;
    @FXML
    private TextField tfMarca11;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void btnGuardar(ActionEvent event) {
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
    }
    
}
