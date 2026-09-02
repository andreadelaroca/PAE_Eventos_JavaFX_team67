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

    ObservableList<String> pantallas = FXCollections.observableArrayList("Inventario de pulpería","Recepción de café", "Tienda de artesanías");

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
        if (lsvwPantalla.getSelectionModel().getSelectedItem() == "Inventario de pulpería") {
            abrirReto1();
        }
        else if (lsvwPantalla.getSelectionModel().getSelectedItem() == "Recepción de café") {
            abrirReto2();
        }
        else if (lsvwPantalla.getSelectionModel().getSelectedItem() == "Tienda de artesanías") {
            abrirReto3();
        }
        else {
            mostrarAlerta("Error", "No se ha seleccionado ninguna pantalla.");
        }
    }

    private void abrirReto1() {
        try {
            FXMLLoader Loader =new FXMLLoader(getClass().getResource("/com/example/equipo67/reto1-view.fxml"));
            Scene scene = new Scene(Loader.load());
            Stage stage = (Stage) lsvwPantalla.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Inventario de pulpería");
            stage.centerOnScreen();
        }

        catch (IOException e) {
            mostrarAlerta("Error", "No se pudo abrir la pantalla seleccionada");
            e.printStackTrace();
        }
    }

    private void abrirReto2() {
        try {
            FXMLLoader Loader =new FXMLLoader(getClass().getResource("/com/example/equipo67/reto2-view.fxml"));
            Scene scene = new Scene(Loader.load());
            Stage stage = (Stage) lsvwPantalla.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Recepción de café");
            stage.centerOnScreen();
        }

        catch (IOException e) {
            mostrarAlerta("Error", "No se pudo abrir la pantalla seleccionada");
            e.printStackTrace();
        }
    }

    private void abrirReto3() {
        try {
            FXMLLoader Loader =new FXMLLoader(getClass().getResource("/com/example/equipo67/reto3-view.fxml"));
            Scene scene = new Scene(Loader.load());
            Stage stage = (Stage) lsvwPantalla.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Tienda de inventario");
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
