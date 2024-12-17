/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio;

import clienteescritorio.modelo.dao.ClientesDAO;
import clienteescritorio.modelo.dao.Mensaje;
import clienteescritorio.pojo.Cliente;
import clienteescritorio.utilidades.Alertas;
import clienteescritorio.utilidades.ControladorPrincipal;
import clienteescritorio.utilidades.Funciones;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Manzano
 */
public class FXMLFormularioClienteController implements Initializable, ControladorPrincipal<Cliente> {

    @FXML
    private TextField tfNombreCliente;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCorreoElectronico;
    @FXML
    private TextField tfContrasenia;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnCancelar;
    private Cliente cliente;
    @FXML
    private Label labelErrorNombreCliente;
    @FXML
    private Label labelErrorApellidoM;
    @FXML
    private Label labelErrorContrasenia;
    @FXML
    private Label labelErrorApellidoP;
    @FXML
    private Label labelErrorCalle;
    @FXML
    private Label labelErrorCorreo;
    @FXML
    private Label labelErrorColonia;
    @FXML
    private Label labelErrorTelefono;
    @FXML
    private Label labelErrorCP;
    @FXML
    private TextField tfColonia;
    @FXML
    private TextField tfTelefono;
    @FXML
    private TextField tfCodigoPostal;
    @FXML
    private TextField tfCalle;
    @FXML
    private TextField tfNumeroCasa;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    private void cargarDatos() {
        tfNombreCliente.setText(this.cliente.getNombreCliente());
        tfApellidoPaterno.setText(this.cliente.getApellidoPaterno());
        tfApellidoMaterno.setText(this.cliente.getApellidoMaterno());
        tfCorreoElectronico.setText(this.cliente.getCorreoElectronico());
        tfCorreoElectronico.setEditable(false);
        tfContrasenia.setText(this.cliente.getContrasenia());
        tfColonia.setText(this.cliente.getColonia());
        tfTelefono.setText(this.cliente.getTelefono());
        tfCodigoPostal.setText(this.cliente.getCp());
        tfCalle.setText(this.cliente.getCalle());
        tfNumeroCasa.setText(this.cliente.getNumeroCasa());
    }

    @FXML
    private void btnAgregar(ActionEvent event) {
        String nombreCliente = tfNombreCliente.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String correoElectronico = tfCorreoElectronico.getText();
        String contrasenia = tfContrasenia.getText();
        String colonia = tfColonia.getText();
        String telefono = tfTelefono.getText();
        String cp = tfCodigoPostal.getText();
        String calle = tfCalle.getText();
        String numeroCasa = tfNumeroCasa.getText();

        cliente.setNombreCliente(nombreCliente);
        cliente.setApellidoPaterno(apellidoPaterno);
        cliente.setApellidoMaterno(apellidoMaterno);
        cliente.setCorreoElectronico(correoElectronico);
        cliente.setContrasenia(contrasenia);
        cliente.setColonia(colonia);
        cliente.setTelefono(telefono);
        cliente.setCp(cp);
        cliente.setCalle(calle);
        cliente.setNumeroCasa(numeroCasa);

        if (cliente == null) {
            cliente = new Cliente();
            if (validarCampos(cliente)) {
                guardarDatosCliente(cliente);
            } else {
                Alertas.mostrarAlertaSimple("Campos obligatorios.", "Verifica los campos de tu formulario.", Alert.AlertType.WARNING);
            }
        } else {
            if (validarCampos(cliente)) {
                editarDatosCliente(cliente);
            } else {
                Alertas.mostrarAlertaSimple("Campos obligatorios.", "Verifica los campos de tu formulario.", Alert.AlertType.WARNING);
            }
        }
    }

    private void editarDatosCliente(Cliente cliente) {
        Mensaje msj = ClientesDAO.actualizarCliente(cliente);
        if (!msj.isError()) {
            Alertas.mostrarAlertaSimple("El cambio fue exitoso.", "La información del cliente: " + cliente.getNombreCliente() + " " + cliente.getApellidoPaterno() + ", fue actualizado(a) correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Alertas.mostrarAlertaSimple("Campos obligatorios.", "Verifica los campos de tu formulario.", Alert.AlertType.WARNING);
        }
    }

    private void guardarDatosCliente(Cliente cliente) {
        Mensaje msj = ClientesDAO.agregarCliente(cliente);
        if (!msj.isError()) {
            Alertas.mostrarAlertaSimple("Registro exitoso.", "La información del cliente: " + cliente.getNombreCliente() + " " + cliente.getApellidoPaterno() + ", fue registrado(a) correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
        } else {
            Alertas.mostrarAlertaSimple("Error al guardar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void cerrarVentana() {
        AnchorPane contenerdorPrincipal = (AnchorPane) tfContrasenia.getScene().lookup("#contenedorPrincipal");
        Funciones.cargarVista("/clienteescritorio/FXMLClientes.fxml", contenerdorPrincipal);
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        cerrarVentana();
    }

    private boolean validarCampos(Cliente cliente) {
        boolean isValid = true;
        return isValid;
    }

    @Override
    public void setDatos(Cliente cliente) {
        this.cliente = cliente;
        if (cliente != null) {
            cargarDatos();
            btnAgregar.setText("Editar");
        } else {
            btnAgregar.setText("Agregar");
        }
    }
}
