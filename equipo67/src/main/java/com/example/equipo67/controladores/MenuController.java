package com.example.equipo67.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import com.example.equipo67.MenuApplication;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {

    private final ObservableList<String> pantallas = FXCollections.observableArrayList("Inventario de pulpería","Recepción de café", "Tienda de artesanías");

    @FXML
    private ListView<String> lsvwPantalla;

    @FXML
    private Button btnIniciarPantalla;

    @FXML
    public void initialize() {
        lsvwPantalla.setItems(pantallas);
    }

    @FXML
    public void entrarOnClick() {
        String pantalla = lsvwPantalla.getSelectionModel().getSelectedItem();

        if (pantalla == null) {
            mostrarAlerta("Advertencia", "No se ha seleccionado ninguna pantalla");
            return;
        }
        switch (pantalla) {
            case "Inventario de pulpería":
                abrirPantalla("reto1-view.fxml", "Inventario de pulpería");
                break;
            case "Recepción de café":
                abrirPantalla("reto2-view.fxml", "Recepción de café");
                break;
            case "Tienda de artesanías":
                abrirPantalla("reto3-view.fxml", "Tienda de artesanías");
                break;
            default:
                mostrarAlerta("Error", "Opción no válida seleccionada");
                break;
    }
    }

    private void abrirPantalla(String fxml, String titulo) {
        try {
            FXMLLoader loader =new FXMLLoader(MenuApplication.class.getResource(fxml));
            if (loader.getLocation() == null) {
                mostrarAlerta("Error", "Dirección FXML inválida: " + fxml);
                return;
            }
            Scene scene = new Scene(loader.load());
            Stage stage = (Stage) btnIniciarPantalla.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle(titulo);
            stage.centerOnScreen();
        }
        catch (IOException e) {
            mostrarAlerta("Error", "No se pudo abrir la pantalla seleccionada");
            e.printStackTrace();
        }

    }

    private void mostrarAlerta(String titulo, String mensaje){
        Alert alerta = new Alert(Alert.AlertType.WARNING);
        alerta.setTitle(titulo);
        alerta.setContentText(mensaje);
        alerta.showAndWait();
    }

}
