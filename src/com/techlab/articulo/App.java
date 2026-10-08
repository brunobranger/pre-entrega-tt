package com.techlab.articulo;

import com.techlab.articulo.controller.ControladorArticulos;
import com.techlab.articulo.ui.ConsolaDeMenu;

import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ControladorArticulos controlador = new ControladorArticulos();
        ConsolaDeMenu consolaDeMenu = new ConsolaDeMenu(scanner, controlador);

        consolaDeMenu.iniciar();

        scanner.close();
    }
}