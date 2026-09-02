package com.example.equipo67.controladores;

import com.example.equipo67.MenuApplication;
import com.example.equipo67.modelos.Producto;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import com.example.equipo67.dao.ProductoCRUD;
import javafx.stage.Stage;

import java.io.IOException;

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

    private final ProductoCRUD productoDAO = new ProductoCRUD();
    private final ObservableList<Producto> productos = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        colCodigo.setCellValueFactory(datos -> new SimpleStringProperty(datos.getValue().getCodigo()));
        colNombre.setCellValueFactory(datos -> new javafx.beans.property.SimpleStringProperty(datos.getValue().getNombre()));
        colPrecio.setCellValueFactory(datos -> new javafx.beans.property.SimpleDoubleProperty(datos.getValue().getPrecio()));
        colCantidad.setCellValueFactory(datos -> new javafx.beans.property.SimpleIntegerProperty(datos.getValue().getCantidad()));

        tablaProductos.setItems(productos);
    }

    @FXML
    private void guardarProducto(ActionEvent event) {
        String codigo = txtCodigo.getText().trim();
        String nombre = txtNombre.getText().trim();
        String precioTexto = txtPrecio.getText().trim();
        String cantidadTexto = txtCantidad.getText().trim();

        if (codigo.isEmpty() || nombre.isEmpty() || precioTexto.isEmpty() || cantidadTexto.isEmpty()) {
            mostrarAlerta("Campos vacíos", "Debe llenar todos los campos.");
            return;
        }

        //Verificar códigos repetidos
        for (Producto p : productoDAO.obtenerRegistros()) {
            if (p.getCodigo().equalsIgnoreCase(codigo)) {
                mostrarAlerta("Código repetido","Ya existe un producto con ese código.");
                return;
            }
        }

        try {
            double precio = Double.parseDouble(precioTexto);
            int cantidad = Integer.parseInt(cantidadTexto);
            if (precio <= 0) {
                mostrarAlerta("Precio incorrecto", "El precio debe ser mayor que 0.");
                return;
            }
            if (cantidad < 0) {
                mostrarAlerta("Cantidad incorrecta", "La cantidad no puede ser negativa.");
                return;
            }

            //Crear y guardar producto
            Producto nuevoProducto = new Producto(codigo, nombre, precio, cantidad);
            productoDAO.agregar(nuevoProducto);
            productos.setAll(productoDAO.obtenerRegistros());
            limpiarCampos();

        } catch (NumberFormatException e) {
            mostrarAlerta("Datos incorrectos", "Precio y cantidad deben ser números.");
        }
    }

    @FXML
    private void buscarProducto(KeyEvent event) {
        if (event.getCode() != KeyCode.ENTER) {
            return;
        }
        String busqueda = txtBuscar.getText().trim();
        if (busqueda.isEmpty()) {
            lblResultado.setText("Escriba un código o nombre.");
            return;
        }
        for (Producto producto : productoDAO.obtenerRegistros()) {
            if (producto.getCodigo().equalsIgnoreCase(busqueda) || producto.getNombre().equalsIgnoreCase(busqueda)) {
                tablaProductos.getSelectionModel().select(producto);
                tablaProductos.scrollTo(producto);
                lblResultado.setText("Encontrado: " + producto.getNombre() + " | Precio: C$" + producto.getPrecio() + " | Cantidad: " + producto.getCantidad());
                return;
            }
        }
        lblResultado.setText("Producto no encontrado.");
    }

    @FXML
    private void volverAlMenu(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(MenuApplication.class.getResource("menu-view.fxml"));
            Scene scene = new Scene(loader.load(), 420, 400);
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menú");
            stage.centerOnScreen();
        }
        catch (IOException e) {
            mostrarAlerta("Error", "No se pudo regresar al menú principal");
            e.printStackTrace();
        }

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
