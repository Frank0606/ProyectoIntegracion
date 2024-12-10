package clienteescritorio;

import clienteescritorio.utilidades.Alertas;
import clienteescritorio.utilidades.Funciones;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class FXMLPrincipalController implements Initializable {

    @FXML
    private Button btnCerrarSesion;
    @FXML
    private Button btnColaboradores;
    @FXML
    private Button btnClientes;
    @FXML
    private Button btnUnidades;
    @FXML
    private Button btnEnvios;
    @FXML
    private Button btnPaquetes;
    @FXML
    private AnchorPane contenedorPrincipal;
    
    Funciones funciones = new Funciones();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        btnColaboradores.setOnAction(e -> funciones.cargarVista("/clienteescritorio/FXMLColaboradores.fxml", contenedorPrincipal));
        btnClientes.setOnAction(e -> funciones.cargarVista("FXMLClientes.fxml", contenedorPrincipal));
        btnUnidades.setOnAction(e -> funciones.cargarVista("FXMLUnidades.fxml", contenedorPrincipal));
        btnEnvios.setOnAction(e -> funciones.cargarVista("FXMLEnvios.fxml", contenedorPrincipal));
        btnPaquetes.setOnAction(e -> funciones.cargarVista("FXMLPaquetes.fxml", contenedorPrincipal));
        btnCerrarSesion.setOnAction(e -> cerrarSesion());
    }

    private void cerrarSesion() {
        Alertas.mostrarAlertaSimple("Cerrar sesión", "¿Estas seguro de cerrar sesión y salir?", Alert.AlertType.CONFIRMATION);
        
    }
}
