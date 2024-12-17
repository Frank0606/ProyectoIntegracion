package clienteescritorio;

import clienteescritorio.modelo.dao.ColaboradoresDAO;
import clienteescritorio.modelo.dao.Mensaje;
import clienteescritorio.modelo.dao.RolDAO;
import clienteescritorio.observador.NotificadorOperacion;
import clienteescritorio.pojo.Colaborador;
import clienteescritorio.pojo.Rol;
import clienteescritorio.utilidades.Alertas;
import clienteescritorio.utilidades.ControladorPrincipal;
import clienteescritorio.utilidades.Funciones;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Manzano
 */
public class FXMLFormularioColaboradorController implements Initializable, ControladorPrincipal<Colaborador> {

    @FXML
    private TextField tfNombreColaborador;
    @FXML
    private TextField tfApellidoPaterno;
    @FXML
    private TextField tfApellidoMaterno;
    @FXML
    private TextField tfCURP;
    @FXML
    private TextField tfCorreoElectronico;
    @FXML
    private TextField tfNoPersonal;
    @FXML
    private TextField tfContrasenia;
    @FXML
    private ComboBox<Rol> cbRol;
    private Button btnSubirFotografía;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnCancelar;

    private NotificadorOperacion notificador;
    private Colaborador colaborador;
    private ObservableList<Rol> listaObservableRoles;
    @FXML
    private Label labelErrorNombreColaborador;
    @FXML
    private Label labelErrorApellidoM;
    @FXML
    private Label labelErrorCorreo;
    @FXML
    private Label labelErrorApellidoP;
    @FXML
    private Label labelErrorCURP;
    @FXML
    private Label labelErrorNumeroPersonal;
    @FXML
    private Label labelErrorContrasenia;
    @FXML
    private Label labelErrorLicencia;
    @FXML
    private Label labelErrorRol;
    @FXML
    private TextField tfVIM;
    @FXML
    private Label txNumeroLicencia;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarTiposUsuario();
    }

    private void cargarDatos() {
        tfNombreColaborador.setText(this.colaborador.getNombreColaborador());
        tfApellidoPaterno.setText(this.colaborador.getApellidoPaterno());
        tfApellidoMaterno.setText(this.colaborador.getApellidoMaterno());
        tfCURP.setText(this.colaborador.getCurp());
        tfCorreoElectronico.setText(this.colaborador.getCorreoElectronico());
        tfNoPersonal.setText(this.colaborador.getNoPersonal());
        tfNoPersonal.setEditable(false);
        tfContrasenia.setText(this.colaborador.getContrasenia());
        int posicion = buscarIdRol(this.colaborador.getIdRol());
        cbRol.getSelectionModel().select(posicion);
    }

    @FXML
    private void btnAgregar(ActionEvent event) {
        String nombreColaborador = tfNombreColaborador.getText();
        String apellidoPaterno = tfApellidoPaterno.getText();
        String apellidoMaterno = tfApellidoMaterno.getText();
        String curp = tfCURP.getText();
        String correoElectronico = tfCorreoElectronico.getText();
        String noPersonal = tfNoPersonal.getText();
        String contrasenia = tfContrasenia.getText();
        Integer idRol = ((cbRol.getSelectionModel().getSelectedItem() != null) ? cbRol.getSelectionModel().getSelectedItem().getIdRol() : null);

        Colaborador colaborador = new Colaborador();
        colaborador.setNombreColaborador(nombreColaborador);
        colaborador.setApellidoPaterno(apellidoPaterno);
        colaborador.setApellidoMaterno(apellidoMaterno);
        colaborador.setCurp(curp);
        colaborador.setCorreoElectronico(correoElectronico);
        colaborador.setNoPersonal(noPersonal);
        colaborador.setContrasenia(contrasenia);
        colaborador.setIdRol(idRol);

       if (colaborador == null) {
            colaborador = new Colaborador();
            if (validarCampos(colaborador)) {
                guardarDatosColaborador(colaborador);
            } else {
                Alertas.mostrarAlertaSimple("Campos obligatorios.", "Verifica los campos de tu formulario.", Alert.AlertType.WARNING);
            }
        } else {
            if (validarCampos(colaborador)) {
                editarDatosColaborador(colaborador);
            } else {
                Alertas.mostrarAlertaSimple("Campos obligatorios.", "Verifica los campos de tu formulario.", Alert.AlertType.WARNING);
            }
        }
    }

    private void editarDatosColaborador(Colaborador colaborador) {
        Mensaje msj = ColaboradoresDAO.actualizarColaborador(colaborador);
        if (!msj.isError()) {
            Alertas.mostrarAlertaSimple("El cambio fue exitoso.", "La información del colaborador: " + colaborador.getNombreColaborador() + " " + colaborador.getApellidoPaterno() + ", fue actualizado(a) correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            notificador.notificarOperacion();
        } else {
            Alertas.mostrarAlertaSimple("Campos obligatorios.", "Verifica los campos de tu formulario.", Alert.AlertType.WARNING);
        }
    }

    private boolean validarCampos(Colaborador colaborador) {
        boolean isValid = true;
        return isValid;
    }

    private void guardarDatosColaborador(Colaborador colaborador) {
        Mensaje msj = ColaboradoresDAO.agregarColaborador(colaborador);
        if (!msj.isError()) {
            Alertas.mostrarAlertaSimple("Registro exitoso.", "La información del colaborador: " + colaborador.getNombreColaborador() + " " + colaborador.getApellidoPaterno() + ", fue registrado(a) correctamente", Alert.AlertType.INFORMATION);
            cerrarVentana();
            notificador.notificarOperacion();
        } else {
            Alertas.mostrarAlertaSimple("Error al guardar", msj.getMensaje(), Alert.AlertType.ERROR);
        }
    }

    private void cerrarVentana() {
        AnchorPane contenerdorPrincipal = (AnchorPane) tfContrasenia.getScene().lookup("#contenedorPrincipal");
        Funciones.cargarVista("/clienteescritorio/FXMLColaboradores.fxml", contenerdorPrincipal);
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        cerrarVentana();
    }

    private void cargarTiposUsuario() {
        List<Rol> roles = RolDAO.obtenerRol();
        if (roles != null && !roles.isEmpty()) {
            listaObservableRoles = FXCollections.observableArrayList(roles);
            cbRol.setItems(listaObservableRoles);
        } else {
            Alertas.mostrarAlertaSimple("Error al cargar", "Lo sentiento, no se pudo obtener la informacion de Roles", Alert.AlertType.ERROR);
        }
    }

    private int buscarIdRol(int idRol) {
        for (int i = 0; i < listaObservableRoles.size(); i++) {
            if (listaObservableRoles.get(i).getIdRol() == idRol) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void setDatos(Colaborador colaborador) {
        this.colaborador = colaborador;
        if (colaborador != null) {
            cargarDatos();
            btnAgregar.setText("Editar");
        } else {
            btnAgregar.setText("Agregar");
        }
    }
}

