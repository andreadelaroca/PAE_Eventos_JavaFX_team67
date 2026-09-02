package com.example.equipo67.controladores;

import com.example.equipo67.modelos.Producto;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Reto1Controller {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtPrecio;

    @FXML
    private TextField txtCantidad;

    @FXML
    private TextField txtBuscar;

    @FXML
    private Label lblResultado;

    @FXML
    private TableView<Producto> tablaProductos;

    @FXML
    private TableColumn<Producto, String> colCodigo;

    @FXML
    private TableColumn<Producto, String> colNombre;

    @FXML
    private TableColumn<Producto, Number> colPrecio;

    @FXML
    private TableColumn<Producto, Number> colCantidad;

    private final ObservableList<Producto> productos =
            FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        colCodigo.setCellValueFactory(datos ->
                new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getCodigo()
                )
        );

        colNombre.setCellValueFactory(datos ->
                new javafx.beans.property.SimpleStringProperty(
                        datos.getValue().getNombre()
                )
        );

        colPrecio.setCellValueFactory(datos ->
                new javafx.beans.property.SimpleDoubleProperty(
                        datos.getValue().getPrecio()
                )
        );

        colCantidad.setCellValueFactory(datos ->
                new javafx.beans.property.SimpleIntegerProperty(
                        datos.getValue().getCantidad()
                )
        );

        tablaProductos.setItems(productos);
    }

    @FXML
    private void guardarProducto(ActionEvent event) {

        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String precioTexto = txtPrecio.getText().trim();
        String cantidadTexto = txtCantidad.getText().trim();

        if (codigo.isEmpty()
                || nombre.isEmpty()
                || precioTexto.isEmpty()
                || cantidadTexto.isEmpty()) {

            mostrarAlerta(
                    "Campos vacíos",
                    "Debe llenar todos los campos."
            );

            return;
        }

        for (Producto producto : productos) {

            if (producto.getCodigo().equalsIgnoreCase(codigo)) {

                mostrarAlerta(
                        "Código repetido",
                        "Ya existe un producto con ese código."
                );

                return;
            }
        }

        try {

            double precio = Double.parseDouble(precioTexto);
            int cantidad = Integer.parseInt(cantidadTexto);

            if (precio <= 0) {

                mostrarAlerta(
                        "Precio incorrecto",
                        "El precio debe ser mayor que 0."
                );

                return;
            }

            if (cantidad < 0) {

                mostrarAlerta(
                        "Cantidad incorrecta",
                        "La cantidad no puede ser negativa."
                );

                return;
            }

            Producto nuevoProducto = new Producto();
            productos.add(nuevoProducto);
            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    "Datos incorrectos",
                    "Precio y cantidad deben ser números."
            );
        }
    }

    @FXML
    private void buscarProducto(KeyEvent event) {

        if (event.getCode() != KeyCode.ENTER) {
            return;
        }

        String busqueda = txtBuscar.getText().trim();

        if (busqueda.isEmpty()) {

            lblResultado.setText(
                    "Escriba un código o nombre."
            );

            return;
        }

        for (Producto producto : productos) {

            if (producto.getCodigo().equalsIgnoreCase(busqueda)
                    || producto.getNombre().equalsIgnoreCase(busqueda)) {

                tablaProductos.getSelectionModel()
                        .select(producto);

                tablaProductos.scrollTo(producto);

                lblResultado.setText(
                        "Encontrado: "
                                + producto.getNombre()
                                + " | Precio: C$"
                                + producto.getPrecio()
                                + " | Cantidad: "
                                + producto.getCantidad()
                );

                return;
            }
        }

        lblResultado.setText(
                "Producto no encontrado."
        );
    }

    private void limpiarCampos() {

        txtCodigo.clear();
        txtNombre.clear();
        txtPrecio.clear();
        txtCantidad.clear();

        txtCodigo.requestFocus();
    }

    private void mostrarAlerta(
            String titulo,
            String mensaje
    ) {

        Alert alerta =
                new Alert(Alert.AlertType.WARNING);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
}
