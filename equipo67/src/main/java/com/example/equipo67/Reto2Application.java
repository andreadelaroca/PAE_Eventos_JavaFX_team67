package com.example.equipo67;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Reto2Application extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader =
                new FXMLLoader(
                        Reto2Application.class.getResource(
                                "/com/example/equipo67/reto2-view.fxml"
                        )
                );

        Scene scene =
                new Scene(loader.load());

        stage.setTitle(
                "Reto 2 - Recepción de Café"
        );

        stage.setScene(scene);

        stage.setResizable(false);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
