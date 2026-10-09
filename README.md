# Sistema de Gestión de Artículos - POO Java

Aplicación de consola desarrollada en Java aplicando Programación Orientada a Objetos (POO), herencia, polimorfismo, clases abstractas y principios de diseño limpio (Clean Code y Patrón Command).

## Autor
* **Bruno Branger**

## Curso
* **Backend / Java**

---

## Estructura del Proyecto

El código está organizado en paquetes para separar responsabilidades de manera limpia:

```text
src/
└── com/
    └── techlab/
        └── articulo/
            ├── App.java
            ├── controller/
            │   └── ArticuloController.java
            ├── model/
            │   ├── Articulo.java
            │   ├── ArticuloAlimenticio.java
            │   ├── ArticuloElectronico.java
            │   └── Categoria.java
            └── ui/
                ├── MenuCommand.java
                └── MenuConsole.java
```

Descripción de Archivos

`App.java`: Clase de arranque (Bootstrap) encargada de inicializar los componentes y dar inicio a la aplicación.
`controller/ArticuloController.java`: Administra las colecciones en memoria (ArrayList) y la lógica de negocio para la búsqueda, alta y baja de elementos.
`model/Articulo.java`: Clase abstracta base que agrupa los atributos comunes y define contratos polimórficos.
`model/ArticuloElectronico.java`: Subtipo de artículo que extiende a Articulo y agrega la gestión de garantía.
`model/ArticuloAlimenticio.java`: Subtipo de artículo que extiende a Articulo y agrega la gestión de días para el vencimiento.
`model/Categoria.java`: Entidad que representa la categoría asociada a los artículos.

    ui/MenuCommand.java: Interfaz funcional para implementar el Patrón Command en las opciones del menú.

    ui/MenuConsole.java: Administra la interacción con el usuario por consola, el ingreso de datos, las validaciones y el enrutamiento de comandos.
