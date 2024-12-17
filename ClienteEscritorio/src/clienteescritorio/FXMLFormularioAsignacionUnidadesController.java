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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author Eric Jair
 */
public class FXMLFormularioAsignacionUnidadesController implements Initializable {

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
    private Label labelErrorAlto;
    @FXML
    private Label labelErrorAncho;
    @FXML
    private Label labelErrorProfundidad;
    @FXML
    private Label labelErrorPeso;
    @FXML
    private Label labelErrorNoGuia;
    @FXML
    private Label labelErrorDescripcion;
    @FXML
    private ComboBox<?> comboBTipoUnidad;
    @FXML
    private DatePicker DatePickFecha;
    @FXML
    private ComboBox<?> comboBNoIdenti;

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
