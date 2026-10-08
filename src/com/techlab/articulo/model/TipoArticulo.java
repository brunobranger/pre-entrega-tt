package com.techlab.articulo.model;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.IntSupplier;

public class TipoArticulo {

    private final String descripcion;
    private final FabricaArticulo fabrica;

    public TipoArticulo(String descripcion, FabricaArticulo fabrica) {
        this.descripcion = Objects.requireNonNull(descripcion);
        this.fabrica = Objects.requireNonNull(fabrica);
    }

    public String descripcion() {
        return descripcion;
    }

    public Articulo crear(int codigo, String nombre, double precio, Categoria categoria,
                          Consumer<String> mensaje, IntSupplier entrada) {
        return fabrica.crear(codigo, nombre, precio, categoria, mensaje, entrada);
    }
}