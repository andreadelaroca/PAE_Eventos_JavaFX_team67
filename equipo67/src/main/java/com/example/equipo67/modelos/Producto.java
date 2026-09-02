package com.example.equipo67.modelos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Producto {
    private String codigo;
    private String nombre;
    private double precio;
    private int cantidad;
    }

