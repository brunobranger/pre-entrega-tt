package com.techlab.articulo.model;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

public final class RegistroTiposArticulo {

    private static final Map<Integer, TipoArticulo> tipos = new LinkedHashMap<>();

    static {
        registrar(1, new TipoArticulo("Artículo electrónico", ArticuloElectronico::fabricar));
        registrar(2, new TipoArticulo("Artículo alimenticio", ArticuloAlimenticio::fabricar));
    }

    private RegistroTiposArticulo() {
    }

    public static void registrar(int codigo, TipoArticulo tipo) {
        tipos.put(codigo, tipo);
    }

    public static TipoArticulo buscar(int codigo) {
        return tipos.get(codigo);
    }

    public static Map<Integer, TipoArticulo> obtenerTodos() {
        return Collections.unmodifiableMap(tipos);
    }
}