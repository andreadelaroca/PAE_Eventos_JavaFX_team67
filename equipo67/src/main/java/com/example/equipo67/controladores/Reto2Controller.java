package com.example.equipo67.controladores;

<<<<<<< HEAD
import com.example.equipo67.modelos.LoteCafe;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;

import java.util.Optional;

public class Reto2Controller {

    @FXML
    private TextField txtCodigo;

    @FXML
    private TextField txtProductor;

    @FXML
    private TextField txtCantidad;

    @FXML
    private Button btnGuardar;

    @FXML
    private TableView<LoteCafe> tablaLotes;

    @FXML
    private TableColumn<LoteCafe, String> colCodigo;

    @FXML
    private TableColumn<LoteCafe, String> colProductor;

    @FXML
    private TableColumn<LoteCafe, Number> colCantidad;

    private final ObservableList<LoteCafe> listaLotes =
            FXCollections.observableArrayList();

    private LoteCafe loteEditando = null;

    @FXML
    public void initialize() {

        colCodigo.setCellValueFactory(datos ->
                new SimpleStringProperty(
                        datos.getValue().getCodigo()
                )
        );

        colProductor.setCellValueFactory(datos ->
                new SimpleStringProperty(
                        datos.getValue().getProductor()
                )
        );

        colCantidad.setCellValueFactory(datos ->
                new SimpleDoubleProperty(
                        datos.getValue().getCantidadKg()
                )
        );

        tablaLotes.setItems(listaLotes);

        crearMenuContextual();
    }

    @FXML
    private void guardarLote(ActionEvent event) {

        String codigo = txtCodigo.getText().trim();
        String productor = txtProductor.getText().trim();
        String cantidadTexto = txtCantidad.getText().trim();

        if (codigo.isEmpty()
                || productor.isEmpty()
                || cantidadTexto.isEmpty()) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Campos vacíos",
                    "Debe completar todos los campos."
            );

            return;
        }

        try {

            double cantidad = Double.parseDouble(cantidadTexto);

            if (cantidad <= 0) {

                mostrarAlerta(
                        Alert.AlertType.WARNING,
                        "Cantidad incorrecta",
                        "La cantidad debe ser mayor que cero."
                );

                return;
            }

            /*
             * Si loteEditando es null,
             * estamos registrando uno nuevo.
             */
            if (loteEditando == null) {

                for (LoteCafe lote : listaLotes) {

                    if (lote.getCodigo().equalsIgnoreCase(codigo)) {

                        mostrarAlerta(
                                Alert.AlertType.WARNING,
                                "Código repetido",
                                "Ya existe un lote con ese código."
                        );

                        return;
                    }
                }

                LoteCafe nuevoLote =
                        new LoteCafe(
                                codigo,
                                productor,
                                cantidad
                        );

                listaLotes.add(nuevoLote);

            } else {

                loteEditando.setCodigo(codigo);
                loteEditando.setProductor(productor);
                loteEditando.setCantidadKg(cantidad);

                tablaLotes.refresh();

                loteEditando = null;

                btnGuardar.setText("Guardar lote");
            }

            limpiarCampos();

        } catch (NumberFormatException e) {

            mostrarAlerta(
                    Alert.AlertType.ERROR,
                    "Dato incorrecto",
                    "La cantidad debe ser un número."
            );
        }
    }

    /*
     * MouseEvent:
     * doble clic izquierdo sobre un lote
     * para mostrar sus detalles.
     */
    @FXML
    private void mostrarDetalles(MouseEvent event) {

        if (event.getButton() == MouseButton.PRIMARY
                && event.getClickCount() == 2) {

            LoteCafe lote =
                    tablaLotes.getSelectionModel().getSelectedItem();

            if (lote != null) {

                Alert alerta =
                        new Alert(Alert.AlertType.INFORMATION);

                alerta.setTitle("Detalles del lote");
                alerta.setHeaderText("Información del lote");

                alerta.setContentText(
                        "Código: " + lote.getCodigo()
                                + "\nProductor: " + lote.getProductor()
                                + "\nCantidad: "
                                + lote.getCantidadKg()
                                + " kg"
                );

                alerta.showAndWait();
            }
        }
    }

    /*
     * ContextMenu:
     * clic derecho sobre la tabla.
     */
    private void crearMenuContextual() {

        MenuItem opcionEditar =
                new MenuItem("Editar");

        MenuItem opcionEliminar =
                new MenuItem("Eliminar");

        opcionEditar.setOnAction(event -> editarLote());

        opcionEliminar.setOnAction(event -> eliminarLote());

        ContextMenu menu =
                new ContextMenu();

        menu.getItems().addAll(
                opcionEditar,
                opcionEliminar
        );

        tablaLotes.setContextMenu(menu);
    }

    private void editarLote() {

        LoteCafe lote =
                tablaLotes.getSelectionModel().getSelectedItem();

        if (lote == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Seleccione un lote",
                    "Primero debe seleccionar un lote."
            );

            return;
        }

        loteEditando = lote;

        txtCodigo.setText(lote.getCodigo());
        txtProductor.setText(lote.getProductor());
        txtCantidad.setText(
                String.valueOf(lote.getCantidadKg())
        );

        btnGuardar.setText("Actualizar lote");
    }

    private void eliminarLote() {

        LoteCafe lote =
                tablaLotes.getSelectionModel().getSelectedItem();

        if (lote == null) {

            mostrarAlerta(
                    Alert.AlertType.WARNING,
                    "Seleccione un lote",
                    "Primero debe seleccionar un lote."
            );

            return;
        }

        Alert confirmacion =
                new Alert(Alert.AlertType.CONFIRMATION);

        confirmacion.setTitle("Confirmar eliminación");

        confirmacion.setHeaderText(
                "¿Desea eliminar este lote?"
        );

        confirmacion.setContentText(
                "Lote: "
                        + lote.getCodigo()
                        + "\nProductor: "
                        + lote.getProductor()
        );

        Optional<ButtonType> resultado =
                confirmacion.showAndWait();

        if (resultado.isPresent()
                && resultado.get() == ButtonType.OK) {

            listaLotes.remove(lote);

            limpiarCampos();

            loteEditando = null;

            btnGuardar.setText("Guardar lote");
        }
    }

    private void limpiarCampos() {

        txtCodigo.clear();
        txtProductor.clear();
        txtCantidad.clear();

        txtCodigo.requestFocus();
    }

    private void mostrarAlerta(
            Alert.AlertType tipo,
            String titulo,
            String mensaje) {

        Alert alerta = new Alert(tipo);

        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);

        alerta.showAndWait();
    }
=======
public class Reto2Controller {
>>>>>>> 092cb5d1a959eeb5d09b01ca9fdb18f84c2ea64e
}
