package com.techlab.articulo.model;

import java.util.function.Consumer;
import java.util.function.IntSupplier;

public class ArticuloElectronico extends Articulo {

    private int garantiaMeses;

    public ArticuloElectronico(int codigo, String nombre, double precio, Categoria categoria, int garantiaMeses) {
        super(codigo, nombre, precio, categoria);
        this.garantiaMeses = garantiaMeses;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getTipoArticulo() {
        return "Electrónico";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Garantía: " + garantiaMeses + " meses";
    }

    public static Articulo fabricar(int codigo, String nombre, double precio, Categoria categoria,
                                    Consumer<String> mensaje, IntSupplier entrada) {
        mensaje.accept("Ingrese la garantía en meses: ");
        int garantiaMeses = entrada.getAsInt();
        return new ArticuloElectronico(codigo, nombre, precio, categoria, garantiaMeses);
    }

    public String telefonoDeSoporte() {
        return "0800-123-4567";
    }

    @Override
    public void actualizarAtributosEspeciales(Consumer<String> mensaje, IntSupplier entrada) {
        mensaje.accept("Ingrese la nueva garantía en meses: ");
        this.garantiaMeses = entrada.getAsInt();
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo electrónico]";
    }
}