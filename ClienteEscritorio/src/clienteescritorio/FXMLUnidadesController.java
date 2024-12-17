/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio;

import clienteescritorio.modelo.dao.UnidadesDAO;
import clienteescritorio.pojo.Unidad;
import clienteescritorio.utilidades.Alertas;
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

/**
 * FXML Controller class
 *
 * @author Eric Jair
 */
public class FXMLUnidadesController implements Initializable {
    
    private ObservableList<Unidad> unidades;
    @FXML
    private TableView<Unidad> tablaUnidades;
    @FXML
    private TableColumn<?, ?> colMarca;
    @FXML
    private TableColumn<?, ?> colModelo;
    @FXML
    private TableColumn<?, ?> colAnio;
    @FXML
    private TableColumn<?, ?> colVin;
    @FXML
    private TableColumn<?, ?> colNoIdentificacion;
    @FXML
    private TableColumn<?, ?> colTipUnidad;
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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        configurarTabla();
        cargarInformacion();
        // TODO
    }    
    @FXML
    private void btnBuscar(ActionEvent event) {
    }
    @FXML
    private void btnAgregar(ActionEvent event) {
    }
    
    @FXML
    private void btnEditar(ActionEvent event) {
    }

    @FXML
    private void btnEliminar(ActionEvent event) {
    }
    
    
    private void configurarTabla(){
        
        colMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        colModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        colAnio.setCellValueFactory(new PropertyValueFactory("anio"));
        colVin.setCellValueFactory(new PropertyValueFactory("vin"));
        colNoIdentificacion.setCellValueFactory(new PropertyValueFactory("noIdentificacion"));
        colTipUnidad.setCellValueFactory(new PropertyValueFactory("tipoUnidad"));
    }
    
    private void cargarInformacion(){
        
        unidades = FXCollections.observableArrayList();
        List<Unidad> listaWS = UnidadesDAO.obtenerUnidades();
        if(listaWS != null){
        unidades.addAll(listaWS);
        tablaUnidades.setItems(unidades);
        
        
        }else{
            
        Alertas.mostrarAlertaSimple("Error", "No se puede cargar la informacion", Alert.AlertType.ERROR);
        
        }
      
    }
    
  
    


}
