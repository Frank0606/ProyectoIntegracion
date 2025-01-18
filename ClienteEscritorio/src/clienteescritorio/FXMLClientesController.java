package clienteescritorio;

import clienteescritorio.modelo.dao.ClientesDAO;
import clienteescritorio.modelo.dao.Mensaje;
import clienteescritorio.pojo.Cliente;
import clienteescritorio.utilidades.Alertas;
import clienteescritorio.utilidades.Funciones;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
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

public class FXMLClientesController implements Initializable {

    @FXML
    private TableView<Cliente> tablaClientes;
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
    @FXML
    private TableColumn colNombreCliente;

    private ObservableList<Cliente> clientes;

    @FXML
    private TableColumn colApellidoPaterno;
    @FXML
    private TableColumn colApellidoMaterno;
    @FXML
    private TableColumn<Cliente, String> colDestino;
    @FXML
    private TableColumn colTelefono;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacion();
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
        Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            eliminarCliente(cliente.getCorreoElectronico());
        } else {
            Alertas.mostrarAlertaSimple("Seleccionar cliente.", "Para eliminar debes seleccionar un cliente "
                    + "de la tabla.", Alert.AlertType.WARNING);
        }
    }

    private void eliminarCliente(String correoElectronico) {
        Mensaje msj = ClientesDAO.eliminarCliente(correoElectronico);
        if (!msj.isError()) {
            Alertas.mostrarAlertaSimple("Cliente eliminado", "El cliente ha sido eliminado correctamente.",
                    Alert.AlertType.INFORMATION);
            cargarInformacion();
        } else {
            Alertas.mostrarAlertaSimple("Error al eliminar.", "No se pudo eliminar al cliente. Intente de nuevo mas tarde.",
                    Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnEditar(ActionEvent event) {
        Cliente cliente = tablaClientes.getSelectionModel().getSelectedItem();
        if (cliente != null) {
            Funciones.cargarVistaConDatos("/clienteescritorio/FXMLFormularioCliente.fxml",
                    (AnchorPane) barraBusqueda.getScene().lookup("#contenedorPrincipal"), cliente,
                    new FXMLFormularioClienteController());
        } else {
            Alertas.mostrarAlertaSimple("Seleccionar cliente.", "Para editar debes seleccionar un cliente "
                    + "de la tabla.", Alert.AlertType.WARNING);
        }
    }

    @FXML
    private void btnAgregar(ActionEvent event) {
        Funciones.cargarVistaConDatos("/clienteescritorio/FXMLFormularioCliente.fxml",
                (AnchorPane) barraBusqueda.getScene().lookup("#contenedorPrincipal"), null,
                new FXMLFormularioClienteController());
    }

    private void configurarTabla() {
        colNombreCliente.setCellValueFactory(new PropertyValueFactory("nombreCliente"));
        colCorreo.setCellValueFactory(new PropertyValueFactory("correoElectronico"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory("apellidoMaterno"));
        colDestino.setCellValueFactory(cellData -> {
            Cliente cliente = cellData.getValue();
            String direccionCompleta = cliente.getCalle() + " " + cliente.getNumeroCasa() + ", "
                    + cliente.getColonia() + ", " + cliente.getCp();
            return new SimpleStringProperty(direccionCompleta);
        });
        colTelefono.setCellValueFactory(new PropertyValueFactory("telefono"));
    }

    private void cargarInformacion() {
        clientes = FXCollections.observableArrayList();
        List<Cliente> WSList = ClientesDAO.obtenerClientes();
        if (WSList != null) {
            clientes.addAll(WSList);
            tablaClientes.setItems(clientes);
        } else {
            Alertas.mostrarAlertaSimple("Error al cargar.",
                    "Losentimos, por el momento no se puede cargar la informacion "
                    + "de los clientes, por favor, intentalo más tarde.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnBuscar(ActionEvent event) {
        if (!barraBusqueda.getText().trim().isEmpty()) {
            ObservableList<Cliente> resultadosBusqueda = FXCollections.observableArrayList();
            String textoBusqueda = barraBusqueda.getText().trim().toUpperCase();
            for (Cliente cliente : clientes) {
                String correo = cliente.getCorreoElectronico().chars()
                        .mapToObj(c -> Character.isLetter(c) ? Character.toUpperCase((char) c) : (char) c)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();

                String nombre = cliente.getNombreCliente().chars()
                        .mapToObj(c -> Character.isLetter(c) ? Character.toUpperCase((char) c) : (char) c)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();

                String telefono = cliente.getTelefono().chars()
                        .mapToObj(c -> Character.isLetter(c) ? Character.toUpperCase((char) c) : (char) c)
                        .collect(StringBuilder::new, StringBuilder::append, StringBuilder::append)
                        .toString();

                String barraBusquedaTexto = barraBusqueda.getText().trim().chars().mapToObj(c -> Character.isLetter(c)
                        ? Character.toUpperCase((char) c) : (char) c).collect(StringBuilder::new, StringBuilder::append,
                        StringBuilder::append).toString();

                if (correo.startsWith(barraBusquedaTexto)) {
                    resultadosBusqueda.add(cliente);
                } else {
                    if (nombre.startsWith(barraBusquedaTexto)) {
                        resultadosBusqueda.add(cliente);
                    } else {
                        if (telefono.startsWith(barraBusquedaTexto)) {
                            resultadosBusqueda.add(cliente);
                        }
                    }
                }
            }
            if (!resultadosBusqueda.isEmpty()) {
                tablaClientes.setItems(resultadosBusqueda);
            } else {
                Alertas.mostrarAlertaSimple("No encontrado", "No se encontró ningún cliente con el "
                        + "correo electronico proporcionado.", Alert.AlertType.INFORMATION);
                tablaClientes.setItems(null);
            }
        } else {
            tablaClientes.setItems(clientes);
        }
    }

}
