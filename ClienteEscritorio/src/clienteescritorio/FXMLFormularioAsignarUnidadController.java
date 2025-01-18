package clienteescritorio;

import clienteescritorio.modelo.dao.ColaboradoresDAO;
import clienteescritorio.modelo.dao.Mensaje;
import clienteescritorio.modelo.dao.UnidadesDAO;
import clienteescritorio.pojo.Colaborador;
import clienteescritorio.pojo.Unidad;
import clienteescritorio.utilidades.Alertas;
import clienteescritorio.utilidades.ControladorPrincipal;
import clienteescritorio.utilidades.Funciones;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.layout.AnchorPane;

public class FXMLFormularioAsignarUnidadController implements Initializable, ControladorPrincipal<Unidad> {

    @FXML
    private ComboBox<Colaborador> cbColaborador;
    @FXML
    private ComboBox<Unidad> cbUnidad;
    @FXML
    private Button btnAsignar;
    @FXML
    private Label labelErrorColaborador;
    @FXML
    private Label labelErrorUnidad;

    private Unidad unidad;
    private Colaborador colaborador;
    private ObservableList<Unidad> unidades;
    private ObservableList<Colaborador> conductores;

    @FXML
    private Button btnCancelar;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cargarUnidades();
        cargarConductores();
    }

    @FXML
    private void btnAsignar(ActionEvent event) {
        if (verificarCampos()) {
            if (this.unidad == null) {
                this.unidad = new Unidad();
            }

            if (this.colaborador == null) {
                this.colaborador = new Colaborador();
            }

            String noPersonalColaborador = cbColaborador.getSelectionModel().getSelectedItem().getNoPersonal();
            Integer idUnidad = cbUnidad.getSelectionModel().getSelectedItem().getIdUnidad();

            this.colaborador.setNoPersonal(noPersonalColaborador);
            this.colaborador.setIdUnidad(idUnidad);

            Mensaje msj = ColaboradoresDAO.asignarUnidad(this.colaborador);
            if (!msj.isError()) {
                Alertas.mostrarAlertaSimple("Asignación exitosa.", msj.getMensaje(), Alert.AlertType.INFORMATION);
                cerrarVentana();
            } else {
                Alertas.mostrarAlertaSimple("Datos incorrectos", "Ocurrió un error con los datos. " + msj.getMensaje(), Alert.AlertType.WARNING);
            }

        } else {
            Alertas.mostrarAlertaSimple("Problema con los datos", "Tiene datos incorrectos, revise e intente de nuevo.",
                    Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void btnCancelar(ActionEvent event) {
        cerrarVentana();
    }

    private void cerrarVentana() {
        AnchorPane contenerdorPrincipal = (AnchorPane) cbUnidad.getScene().lookup("#contenedorPrincipal");
        Funciones.cargarVista("/clienteescritorio/FXMLUnidades.fxml", contenerdorPrincipal);
    }

    private void cargarUnidades() {
        List<Unidad> unidades = UnidadesDAO.obtenerUnidad();
        if (unidades != null && !unidades.isEmpty()) {
            this.unidades = FXCollections.observableArrayList(unidades);
            cbUnidad.setItems(this.unidades);
        } else {
            Alertas.mostrarAlertaSimple("Error al cargar", "Lo sentiento, no se pudo obtener la informacion de Unidades",
                    Alert.AlertType.ERROR);
        }
    }

    private void cargarConductores() {
        List<Colaborador> colaboradores = ColaboradoresDAO.obtenerColaborador();
        List<Colaborador> conductores = new ArrayList();
        if (colaboradores != null && !colaboradores.isEmpty()) {

            Colaborador liberar = new Colaborador();
            liberar.setNoPersonal("Liberar");
            liberar.setNombreColaborador("Liberar");
            liberar.setApellidoPaterno("Unidad");
            conductores.add(liberar);

            for (Colaborador colaborador : colaboradores) {
                if (colaborador.getIdRol() == 3) {
                    conductores.add(colaborador);
                }
            }
            this.conductores = FXCollections.observableArrayList(conductores);
            cbColaborador.setItems(this.conductores);
        } else {
            Alertas.mostrarAlertaSimple("Error al cargar", "Lo sentiento, no se pudo obtener la informacion de Conductores",
                    Alert.AlertType.ERROR);
        }
    }

    private boolean verificarCampos() {
        boolean valid = true;
        labelErrorColaborador.setText("");
        labelErrorUnidad.setText("");

        if (cbColaborador.getValue() == null) {
            labelErrorColaborador.setText("Seleccione un conductor.");
            valid = false;
        }

        if (cbUnidad.getValue() == null) {
            labelErrorUnidad.setText("Seleccione una unidad.");
            valid = false;
        }

        return valid;
    }

    private int buscarUnidad(String vin) {
        for (int i = 0; i < unidades.size(); i++) {
            if (unidades.get(i).getVin().equals(vin)) {
                return i;
            }
        }
        return 0;
    }

    private int buscarConductor(String noPersonal) {
        for (int i = 0; i < conductores.size(); i++) {
            if (conductores.get(i).getNoPersonal().equals(noPersonal)) {
                return i;
            }
        }
        return 0;
    }

    @Override
    public void setDatos(Unidad unidad) {
        this.unidad = unidad;

        if (unidad != null) {
            int posicion = buscarUnidad(this.unidad.getVin());
            cbUnidad.getSelectionModel().select(posicion);
            if (unidad.getNoPersonal() != null) {
                posicion = buscarConductor(this.unidad.getNoPersonal());
                cbColaborador.getSelectionModel().select(posicion);
            }
        }
    }
}
