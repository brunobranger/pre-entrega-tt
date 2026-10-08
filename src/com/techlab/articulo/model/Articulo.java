package com.techlab.articulo.model;

import java.util.function.Consumer;
import java.util.function.IntSupplier;

public abstract class Articulo {

    private int codigo;
    private String nombre;
    private double precio;
    private Categoria categoria;

    public Articulo(int codigo, String nombre, double precio, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.categoria = categoria;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public abstract String getTipoArticulo();
    public abstract String getDetalleEspecifico();

    /**
     * Permite que cada subtipo actualice sus datos particulares
     * delegando la solicitud y lectura del valor ingresado.
     */
    public abstract void actualizarAtributosEspeciales(Consumer<String> mensaje, IntSupplier entrada);

    @Override
    public String toString() {
        return "Artículo {" +
                "código=" + codigo +
                ", nombre='" + nombre + '\'' +
                ", precio=" + precio +
                ", categoría='" + categoria.getNombre() + '\'' +
                ", tipo='" + getTipoArticulo() + '\'' +
                ", detalle='" + getDetalleEspecifico() + '\'' +
                '}';
    }
}