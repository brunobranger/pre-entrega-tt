package com.techlab.articulo.model;

import java.util.function.Consumer;
import java.util.function.IntSupplier;

public class ArticuloAlimenticio extends Articulo {

    private int diasParaVencimiento;

    public ArticuloAlimenticio(int codigo, String nombre, double precio, Categoria categoria, int diasParaVencimiento) {
        super(codigo, nombre, precio, categoria);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    public int getDiasParaVencimiento() {
        return diasParaVencimiento;
    }

    public void setDiasParaVencimiento(int diasParaVencimiento) {
        this.diasParaVencimiento = diasParaVencimiento;
    }

    @Override
    public String getTipoArticulo() {
        return "Alimenticio";
    }

    @Override
    public String getDetalleEspecifico() {
        return "Días para vencimiento: " + diasParaVencimiento;
    }

    public static Articulo fabricar(int codigo, String nombre, double precio, Categoria categoria,
                                    Consumer<String> mensaje, IntSupplier entrada) {
        mensaje.accept("Ingrese los días para vencimiento: ");
        int diasParaVencimiento = entrada.getAsInt();
        return new ArticuloAlimenticio(codigo, nombre, precio, categoria, diasParaVencimiento);
    }

    @Override
    public void actualizarAtributosEspeciales(Consumer<String> mensaje, IntSupplier entrada) {
        mensaje.accept("Ingrese los nuevos días para vencimiento: ");
        this.diasParaVencimiento = entrada.getAsInt();
    }

    @Override
    public String toString() {
        return super.toString() + " [subtipo alimenticio]";
    }
}