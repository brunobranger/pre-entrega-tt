package com.techlab.articulo.controller;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;

import java.util.ArrayList;
import java.util.List;

public class ControladorArticulos {

    private final List<Articulo> articulos = new ArrayList<>();
    private final List<Categoria> categorias = new ArrayList<>();

    public ControladorArticulos() {
        precargarCategorias();
    }

    private void precargarCategorias() {
        categorias.add(new Categoria(1, "Electrónica", "Productos tecnológicos y electrónicos"));
        categorias.add(new Categoria(2, "Periféricos", "Accesorios para computadora"));
        categorias.add(new Categoria(3, "Alimentos", "Productos alimenticios"));
        categorias.add(new Categoria(4, "Limpieza", "Artículos de limpieza del hogar"));
    }

    public List<Articulo> getArticulos() {
        return articulos;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void agregarArticulo(Articulo articulo) {
        articulos.add(articulo);
    }

    public Articulo buscarArticuloPorCodigo(int codigo) {
        for (Articulo articulo : articulos) {
            if (articulo.getCodigo() == codigo) {
                return articulo;
            }
        }
        return null;
    }

    public Categoria buscarCategoriaPorCodigo(int codigo) {
        for (Categoria categoria : categorias) {
            if (categoria.getCodigo() == codigo) {
                return categoria;
            }
        }
        return null;
    }

    public boolean eliminarArticulo(int codigo) {
        Articulo articulo = buscarArticuloPorCodigo(codigo);
        if (articulo != null) {
            articulos.remove(articulo);
            return true;
        }
        return false;
    }
}