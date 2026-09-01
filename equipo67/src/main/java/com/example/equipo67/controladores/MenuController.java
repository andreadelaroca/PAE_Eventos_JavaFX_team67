package com.example.equipo67.controladores;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import com.example.equipo67.MenuApplication;

public class MenuController {

    ObservableList<String> pantallas = FXCollections.observableArrayList("Inventario de pulpería","Recepcón de café", "Tienda de artesanías");

    @FXML
    private ListView<String> lsvwPantalla;

    @FXML
    private Button btnIniciarPantalla;

    @FXML
    public void initialize() {
        lsvwPantalla.setItems(pantallas);
    }

}
