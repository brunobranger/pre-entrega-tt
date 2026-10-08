package com.techlab.articulo.ui;

import com.techlab.articulo.controller.ControladorArticulos;
import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;
import com.techlab.articulo.model.RegistroTiposArticulo;
import com.techlab.articulo.model.TipoArticulo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConsolaDeMenu {

    private final Scanner scanner;
    private final ControladorArticulos controlador;
    private final Map<Integer, ComandoMenu> comandos;

    public ConsolaDeMenu(Scanner scanner, ControladorArticulos controlador) {
        this.scanner = scanner;
        this.controlador = controlador;
        this.comandos = new HashMap<>();
        inicializarComandos();
    }

    private void inicializarComandos() {
        comandos.put(1, this::ingresarArticulo);
        comandos.put(2, this::listarArticulos);
        comandos.put(3, this::consultarArticulo);
        comandos.put(4, this::modificarArticulo);
        comandos.put(5, this::eliminarArticulo);
        comandos.put(6, this::listarCategorias);
    }

    public void iniciar() {
        int opcion;
        do {
            mostrarMenu();
            opcion = leerEntero("Ingrese una opción: ");

            if (opcion == 0) {
                System.out.println("\nSaliendo del sistema. ¡Hasta luego!");
                break;
            }

            ComandoMenu comando = comandos.get(opcion);
            if (comando != null) {
                comando.ejecutar();
            } else {
                System.out.println("\nError: la opción ingresada no es válida.");
            }

        } while (true);
    }

    private void mostrarMenu() {
        System.out.println("\n======================================================");
        System.out.println(" SISTEMA DE ARTÍCULOS ");
        System.out.println("======================================================");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Listar artículos");
        System.out.println("3 - Consultar un artículo");
        System.out.println("4 - Modificar un artículo");
        System.out.println("5 - Eliminar un artículo");
        System.out.println("6 - Listar categorías");
        System.out.println("0 - Salir");
        System.out.println("======================================================");
    }

    private void ingresarArticulo() {
        System.out.println("\n--- INGRESAR ARTÍCULO ---");
        for (Map.Entry<Integer, TipoArticulo> entrada : RegistroTiposArticulo.obtenerTodos().entrySet()) {
            System.out.println(entrada.getKey() + " - " + entrada.getValue().descripcion());
        }

        int numeroTipo;
        TipoArticulo tipo;
        do {
            numeroTipo = leerEntero("Seleccione el tipo de artículo: ");
            tipo = RegistroTiposArticulo.buscar(numeroTipo);
            if (tipo == null) {
                System.out.println("Error: el tipo de artículo no es válido.");
            }
        } while (tipo == null);

        int codigo = leerEntero("Ingrese el código del artículo: ");
        if (controlador.buscarArticuloPorCodigo(codigo) != null) {
            System.out.println("Error: ya existe un artículo con ese código.");
            return;
        }

        String nombre = leerTextoNoVacio("Ingrese el nombre del artículo: ");
        double precio = leerDoubleNoNegativo("Ingrese el precio del artículo: ");

        listarCategorias();
        Categoria categoria = pedirCategoriaExistente();

        Articulo articulo = tipo.crear(
                codigo, nombre, precio, categoria,
                System.out::print,
                () -> leerEnteroNoNegativo("")
        );

        controlador.agregarArticulo(articulo);
        System.out.println("Artículo ingresado correctamente.");
        System.out.println("Resumen del objeto creado:\n" + articulo);
    }

    private void listarArticulos() {
        System.out.println("\n--- LISTADO DE ARTÍCULOS ---");
        if (controlador.getArticulos().isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        for (Articulo articulo : controlador.getArticulos()) {
            System.out.println(articulo);
        }
    }

    private void consultarArticulo() {
        System.out.println("\n--- CONSULTAR ARTÍCULO ---");
        if (controlador.getArticulos().isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero("Ingrese el código del artículo a consultar: ");
        Articulo articulo = controlador.buscarArticuloPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        System.out.println("Artículo encontrado:\n" + articulo);
        System.out.println("Detalle específico: " + articulo.getDetalleEspecifico());
    }

    private void modificarArticulo() {
        System.out.println("\n--- MODIFICAR ARTÍCULO ---");
        if (controlador.getArticulos().isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero("Ingrese el código del artículo a modificar: ");
        Articulo articulo = controlador.buscarArticuloPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("El artículo no existe.");
            return;
        }

        String nuevoNombre = leerTextoNoVacio("Ingrese el nuevo nombre del artículo: ");
        double nuevoPrecio = leerDoubleNoNegativo("Ingrese el nuevo precio del artículo: ");

        listarCategorias();
        Categoria nuevaCategoria = pedirCategoriaExistente();

        articulo.setNombre(nuevoNombre);
        articulo.setPrecio(nuevoPrecio);
        articulo.setCategoria(nuevaCategoria);

        // Actualización polimórfica sin instanceof
        articulo.actualizarAtributosEspeciales(
                System.out::print,
                () -> leerEnteroNoNegativo("")
        );

        System.out.println("Artículo modificado correctamente.");
    }

    private void eliminarArticulo() {
        System.out.println("\n--- ELIMINAR ARTÍCULO ---");
        if (controlador.getArticulos().isEmpty()) {
            System.out.println("No hay artículos cargados.");
            return;
        }

        int codigo = leerEntero("Ingrese el código del artículo a eliminar: ");
        if (controlador.eliminarArticulo(codigo)) {
            System.out.println("Artículo eliminado correctamente.");
        } else {
            System.out.println("El artículo no existe.");
        }
    }

    private void listarCategorias() {
        System.out.println("\n--- CATEGORÍAS DISPONIBLES ---");
        for (Categoria categoria : controlador.getCategorias()) {
            System.out.println(categoria);
        }
    }

    private Categoria pedirCategoriaExistente() {
        while (true) {
            int codigo = leerEntero("Ingrese el código de la categoría: ");
            Categoria categoria = controlador.buscarCategoriaPorCodigo(codigo);
            if (categoria != null) {
                return categoria;
            }
            System.out.println("Error: la categoría no existe.");
        }
    }

    // Métodos auxiliares de lectura
    private int leerEntero(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero válido.");
            }
        }
    }

    private int leerEnteroNoNegativo(String mensaje) {
        while (true) {
            int valor = leerEntero(mensaje);
            if (valor >= 0) {
                return valor;
            }
            System.out.println("Error: el valor no puede ser negativo.");
        }
    }

    private double leerDoubleNoNegativo(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                double valor = Double.parseDouble(scanner.nextLine().trim());
                if (valor >= 0 && !Double.isNaN(valor) && !Double.isInfinite(valor)) {
                    return valor;
                }
                System.out.println("Error: el precio debe ser un número finito no negativo.");
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número decimal válido.");
            }
        }
    }

    private String leerTextoNoVacio(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String texto = scanner.nextLine().trim();
            if (!texto.isEmpty()) {
                return texto;
            }
            System.out.println("Error: el texto no puede estar vacío.");
        }
    }
}