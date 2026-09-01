package com.example.equipo67;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Reto1Application extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(
                Reto1Application.class.getResource(
                        "/com/example/equipo67/reto1-view.fxml"
                )
        );

        Scene scene = new Scene(loader.load());

        stage.setTitle("Reto 1 - Inventario de Pulpería");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
