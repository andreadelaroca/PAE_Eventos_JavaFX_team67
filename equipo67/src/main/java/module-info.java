module com.example.equipo67 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.equipo67 to javafx.fxml;
    exports com.example.equipo67;
}