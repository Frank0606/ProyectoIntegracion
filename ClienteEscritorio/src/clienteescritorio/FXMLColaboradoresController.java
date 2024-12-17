package clienteescritorio;

import clienteescritorio.modelo.dao.ColaboradoresDAO;
import clienteescritorio.modelo.dao.Mensaje;
import clienteescritorio.pojo.Colaborador;
import clienteescritorio.utilidades.Alertas;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class FXMLColaboradoresController implements Initializable {

    @FXML
    private TableView<Colaborador> tablaColaboradores;
    @FXML
    private TableColumn colNombreColaborador;
    @FXML
    private TableColumn colCorreo;
    @FXML
    private Button btnBuscar;
    @FXML
    private Button btnAgregar;
    @FXML
    private Button btnEditar;
    @FXML
    private Button btnEliminar;
    @FXML
    private TextField barraBusqueda;
    
    private ObservableList<Colaborador> colaboradores;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacion();
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
        Colaborador colaborador = tablaColaboradores.getSelectionModel().getSelectedItem();
        if (colaborador != null) {
            eliminarColaborador(colaborador.getCorreoElectronico());
        } else {
            Alertas.mostrarAlertaSimple("Seleccionar colaborador.", "Para eliminar debes seleccionar un colaborador de la tabla.", Alert.AlertType.WARNING);
        }
    }

    private void eliminarColaborador(String correoElectronico) {
        Mensaje msj = ColaboradoresDAO.eliminarColaborador(correoElectronico);
        if (!msj.isError()) {
            Alertas.mostrarAlertaSimple("Colaborador eliminado", "El colaborador ha sido eliminado correctamente.", Alert.AlertType.INFORMATION);
            cargarInformacion();
        } else {
            Alertas.mostrarAlertaSimple("Error al eliminar.", "No se pudo eliminar el colaborador, intente nuevamente.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnEditar(ActionEvent event) {
        Colaborador colaborador = tablaColaboradores.getSelectionModel().getSelectedItem();
        if (colaborador != null) {
            Funciones.cargarVistaConDatos("/clienteescritorio/FXMLFormularioColaborador.fxml", (AnchorPane) barraBusqueda.getScene().lookup("#contenedorPrincipal"), colaborador, new FXMLFormularioColaboradorController());
        } else {
            Alertas.mostrarAlertaSimple("Seleccionar colaborador.", "Para editar debes seleccionar un colaborador de la tabla.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnAgregar(ActionEvent event) {
        Funciones.cargarVistaConDatos("/clienteescritorio/FXMLFormularioColaborador.fxml", (AnchorPane) barraBusqueda.getScene().lookup("#contenedorPrincipal"), null, new FXMLFormularioColaboradorController());
    }

    private void configurarTabla() {
        colNombreColaborador.setCellValueFactory(new PropertyValueFactory("nombreColaborador"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correoElectronico"));
    }

    private void cargarInformacion() {
        colaboradores = FXCollections.observableArrayList();
        List<Colaborador> WSList = ColaboradoresDAO.obtenerColaborador();
        if (WSList != null) {
            colaboradores.addAll(WSList);
            tablaColaboradores.setItems(colaboradores);
        } else {
            Alertas.mostrarAlertaSimple("Error al cargar.",
                    "Lo sentimos, por el momento no se puede cargar la información de los colaboradores, por favor, intentalo más tarde.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        if (!barraBusqueda.getText().trim().isEmpty()) {
            ObservableList<Colaborador> resultadosBusqueda = FXCollections.observableArrayList();
            for (Colaborador colaborador : colaboradores) {
                if (colaborador.getNoPersonal().equalsIgnoreCase(barraBusqueda.getText().trim())) {
                    resultadosBusqueda.add(colaborador);
                }
            }

            if (!resultadosBusqueda.isEmpty()) {
                tablaColaboradores.setItems(resultadosBusqueda);
            } else {
                Alertas.mostrarAlertaSimple("No encontrado", "No se encontró ningún colaborador con el correo proporcionado.", Alert.AlertType.INFORMATION);
            }
        } else {
            tablaColaboradores.setItems(colaboradores);
        }
    }
}

