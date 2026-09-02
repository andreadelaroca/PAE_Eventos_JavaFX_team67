package com.example.equipo67.dao;

import com.example.equipo67.interfaces.CRUD;
import com.example.equipo67.modelos.Producto;

import java.util.ArrayList;
import java.util.List;

public class ProductoCRUD implements CRUD<Producto> {
    private final List<Producto> productos = new ArrayList<>();

    @Override
    public void agregar(Producto producto) {
        productos.add(producto);
    }

    @Override
    public List<Producto> obtenerRegistros() {
        return productos;
    }
}
