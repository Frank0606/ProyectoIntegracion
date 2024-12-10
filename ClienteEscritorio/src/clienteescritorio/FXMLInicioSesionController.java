package clienteescritorio;

import clienteescritorio.modelo.dao.IniciarSesionDAO;
import clienteescritorio.pojo.IniciarSesion;
import clienteescritorio.utilidades.Alertas;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLInicioSesionController implements Initializable {

    @FXML
    private TextField tfNoPersonal;
    @FXML
    private PasswordField tfContrasena;
    @FXML
    private Button btnIngresar;
    @FXML
    private Label labelErrorNoPersonal;
    @FXML
    private Label labelErrorContrasena;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void clickIniciarSesion(ActionEvent event) {
        String noPersonal = tfNoPersonal.getText();
        String contrasenia = tfContrasena.getText();

        if (validarCamposIniciarSesion(noPersonal, contrasenia)) {
            verificarCredenciales(noPersonal, contrasenia);
        }
    }

    private boolean validarCamposIniciarSesion(String noPersonal, String contrasenia) {
        boolean valido = true;
        labelErrorNoPersonal.setText("");
        labelErrorContrasena.setText("");

        if (noPersonal.length() < 4 || noPersonal.length() > 4 || !noPersonal.startsWith("C") 
                || noPersonal.isEmpty()) {
            valido = false;
            labelErrorNoPersonal.setText("No. de personal no valido");
        }
        if (contrasenia.isEmpty()) {
            valido = false;
            labelErrorContrasena.setText("Contraseña no valida");
        }

        return valido;
    }

    private void verificarCredenciales(String noPersonal, String contrasenia) {
        IniciarSesion respuestaIniciarSesion = IniciarSesionDAO.iniciarSesion(noPersonal, contrasenia);
        if (!respuestaIniciarSesion.getError()) {
            Alertas.mostrarAlertaSimple("¡Credenciales correctas!",
                    "Bienvenido " + respuestaIniciarSesion.getColaborador().getNombreColaborador(), 
                    Alert.AlertType.INFORMATION);
            cambiarPantallaPrincipal();
        } else {
            Alertas.mostrarAlertaSimple("Problema", respuestaIniciarSesion.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void cambiarPantallaPrincipal() {
        try {
            Stage escenarioBase = (Stage) tfNoPersonal.getScene().getWindow();
            Parent principal = FXMLLoader.load(getClass().getResource("FXMLPrincipal.fxml"));
            Scene escenaPrincipal = new Scene(principal);
            escenarioBase.setScene(escenaPrincipal);
            escenarioBase.setTitle("Sistema - Principal");
            escenarioBase.show();
        } catch (IOException ex) {
            Alertas.mostrarAlertaSimple("Error", "En este momento no se pudo ingresar a la pantalla inicial", Alert.AlertType.ERROR);
        }
    }
}
