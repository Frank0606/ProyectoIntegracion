/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clienteescritorio.utilidades;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author f_dan
 */
public class Funciones {

    public static <T> void cargarVistaConDatos(String fxml, AnchorPane contenedorPrincipal, T datos, ControladorPrincipal<T> controlador) {
        try {
            FXMLLoader loader = new FXMLLoader(Funciones.class.getResource(fxml));
            AnchorPane newLoadedPane = loader.load();
            controlador = loader.getController();
            controlador.setDatos(datos);
            contenedorPrincipal.getChildren().clear();
            contenedorPrincipal.getChildren().add(newLoadedPane);
            AnchorPane.setTopAnchor(newLoadedPane, 0.0);
            AnchorPane.setBottomAnchor(newLoadedPane, 0.0);
            AnchorPane.setLeftAnchor(newLoadedPane, 0.0);
            AnchorPane.setRightAnchor(newLoadedPane, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static <T> void cargarVista(String fxml, AnchorPane contenedorPrincipal) {
        try {
            FXMLLoader loader = new FXMLLoader(Funciones.class.getResource(fxml));
            AnchorPane newLoadedPane = loader.load();
            contenedorPrincipal.getChildren().clear();
            contenedorPrincipal.getChildren().add(newLoadedPane);
            AnchorPane.setTopAnchor(newLoadedPane, 0.0);
            AnchorPane.setBottomAnchor(newLoadedPane, 0.0);
            AnchorPane.setLeftAnchor(newLoadedPane, 0.0);
            AnchorPane.setRightAnchor(newLoadedPane, 0.0);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
