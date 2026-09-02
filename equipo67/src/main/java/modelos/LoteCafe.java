package com.example.equipo67.modelos;

public class LoteCafe {

    private String codigo;
    private String productor;
    private double cantidadKg;

    public LoteCafe(String codigo, String productor, double cantidadKg) {
        this.codigo = codigo;
        this.productor = productor;
        this.cantidadKg = cantidadKg;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getProductor() {
        return productor;
    }

    public void setProductor(String productor) {
        this.productor = productor;
    }

    public double getCantidadKg() {
        return cantidadKg;
    }

    public void setCantidadKg(double cantidadKg) {
        this.cantidadKg = cantidadKg;
    }
}
