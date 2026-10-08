package com.techlab.articulo.model;

import java.util.function.Consumer;
import java.util.function.IntSupplier;

@FunctionalInterface
public interface FabricaArticulo {

    Articulo crear(int codigo, String nombre, double precio, Categoria categoria,
                   Consumer<String> mensaje, IntSupplier entrada);
}