package org.backend.ui;

import org.backend.service.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

public class MenuPrincipal {
    private static final Scanner sc = new Scanner(System.in);
    private static int opcionElegida;


    //==========================================================
    // MENU PRINCIPAL
    // ==========================================================

    public static void mostrarMenu() {

        List<String> listMenuBase = List.of(
                "Configuración y Carga de Datos",
                "Gestión de Stock (Altas)",
                "Taller y Equipamiento",
                "Ventas",
                "Consultas e informes"
        );


        while (true) {

            System.out.println("\n=== MENU PRINCIPAL - RED CONCESIONARIO ===\n");

            IntStream.range(0, listMenuBase.size())
                    .forEach((i -> System.out.println((i + 1) + "." + listMenuBase.get(i)))
                    );

            System.out.println("0. SALIR");


            int opcion = leerOpcion(listMenuBase.size());

            switch (opcion) {

                case 0 -> {
                    System.out.println("Saliendo del sistema...");
                    System.exit(0);
                }
                case 1 -> {
                    subMenuConfiguracion();
                }
                case 2 -> {
                    subMenuGestionStock();
                }
                case 3 -> {
                    subMenuTaller();
                }
                case 4 -> {
                    subMenuVentas();
                }
                case 5 -> {
                    subMenuConsultas();
                }


            }


        }

    }


    // TODOS -> SUB-MENU ------------------------------------------------------>
    // -------------------------------------------------------------------------
    // CASE 1
    public static void subMenuConfiguracion() {
        List<String> listSubMenuConfiguracion = List.of(
                "Iniciar EntityManager",
                "Cargar datos de prueba - Seed"
        );

        while (true) {

            System.out.println("\n=== CONFIGURACIÓN Y CARGA DE DATOS ===\n");

            IntStream.range(0, listSubMenuConfiguracion.size())
                    .forEach(i ->
                            System.out.println((i + 1) + ". " + listSubMenuConfiguracion.get(i))
                    );
            System.out.println("0. VOLVER");


            int opt = leerOpcion(listSubMenuConfiguracion.size());

            switch (opt) {

                case 0 -> {
                    return;
                }
                case 1 -> {
                    GestorEntityManager.iniciarConexion();
                }
                case 2 -> {
                    Seeder.seed();
                }


            }

        }


    }


    // -------------------------------------------------------------------------
    // CASE 2 -> GESTIÓN DE STOCK (ALTAS)
    public static void subMenuGestionStock() {
        List<String> listSubMenuGestionStock = List.of(
                "Alta de Concesionario",
                "Alta de Coche"
        );


        while (true) {
            System.out.println("\n=== GESTIÓN DE STOCK (ALTAS) ===\n");
            IntStream.range(0, listSubMenuGestionStock.size())
                    .forEach(i ->
                            System.out.println((i + 1) + ". " + listSubMenuGestionStock.get(i))
                    );
            System.out.println("0. VOLVER");

            int opt = leerOpcion(listSubMenuGestionStock.size());

            switch (opt) {
                case 0 -> {
                    return;
                }
                case 1 -> {
                    pedirDatosParaAltaConcesionario();
                }
                case 2 -> {
                    pedirDatosParaAltaCoche();

                }


            }

        }


    }

    // -------------------------------------------------------------------------
    // CASE 3
    public static void subMenuTaller() {
        List<String> listSubMenuTaller = List.of(

                "Instalar Extra",
                "Registrar Reparación"
        );


        while (true) {

            System.out.println("\n=== TALLER Y EQUIPAMIENTO ===\n");

            IntStream.range(0, listSubMenuTaller.size())
                    .forEach(i ->
                            System.out.println((i + 1) + ". " + listSubMenuTaller.get(i))
                    );
            System.out.println("0. VOLVER");


            int opt = leerOpcion(listSubMenuTaller.size());

            switch (opt) {

                case 0 -> {
                    return;
                }
                case 1 -> {
                }
                case 2 -> {
                }


            }

        }


    }

    // -------------------------------------------------------------------------
    // CASE 4
    public static void subMenuVentas() {
        List<String> listSubMenuVentas = List.of(

                "Vender Coche"
        );


        while (true) {

            System.out.println("\n=== VENTAS ===\n");

            IntStream.range(0, listSubMenuVentas.size())
                    .forEach(i ->
                            System.out.println((i + 1) + ". " + listSubMenuVentas.get(i))
                    );
            System.out.println("0. VOLVER");


            int opt = leerOpcion(listSubMenuVentas.size());

            switch (opt) {

                case 0 -> {
                    return;
                }
                case 1 -> {
                }


            }

        }


    }

    // -------------------------------------------------------------------------
    //CASE 5
    public static void subMenuConsultas() {
        List<String> listSubMenuConsultas = List.of(

                "Stock de Concesionario",
                "Historial de Mecánico",
                "Ventas por Concesionario",
                "Coste actual de Coche"
        );


        while (true) {

            System.out.println("\n=== CONSULTAS E INFORMES ===\n");

            IntStream.range(0, listSubMenuConsultas.size())
                    .forEach(i ->
                            System.out.println((i + 1) + ". " + listSubMenuConsultas.get(i))
                    );
            System.out.println("0. VOLVER");


            int opt = leerOpcion(listSubMenuConsultas.size());

            switch (opt) {

                case 0 -> {
                    return;
                }
                case 1 -> System.out.println("Consultando stock de concesionario...");
                case 2 -> System.out.println("Consultando historial de mecánico...");
                case 3 -> System.out.println("Consultando ventas por concesionario...");
                case 4 -> System.out.println("Calculando coste actual de coche...");


            }

        }


    }

    // -------------------------------------------------------------------------
    // FINAL -> SUB-MENU ------------------------------------------------------>


    // MÉT-DO AUXILIAR ------------------------------------------------------>

    public static int leerOpcion(int max) {

        while (true) {
            System.out.println("  ==> Elija opción: ");
            if (sc.hasNextInt()) {
                opcionElegida = sc.nextInt();
                sc.nextLine();  // <-- Consumir el \n residual
                if (opcionElegida >= 0 && opcionElegida <= max) {
                    return opcionElegida;
                } else {
                    System.out.println("Opción fuera de rango");
                }
            } else {
                System.out.println("Debe ingresar un número");
                sc.next();
            }
        }

    }


    //==========================================================
    // PUNTO 2.1 -> SOLICITAR DATOS PARA EL CONCESIONARIO
    // ==========================================================

    private static void pedirDatosParaAltaConcesionario() {
        System.out.println("\n=== ALTA DE CONCESIONARIO ===\n");

        System.out.println("Nombre del Concesionario nuevo: ");
        String nuevoConcesionario = sc.nextLine().trim().toUpperCase();

        System.out.println("Dirección del Concesionario nuevo: ");
        String nuevaDireccion = sc.nextLine().trim().toUpperCase();

        try {
            ConcesionarioService.altaConcesionario(nuevoConcesionario, nuevaDireccion);
        } catch (ApplicationException e) {
            System.err.println(e.getMessage());
        }


    }


    //==========================================================
    // PUNTO 2.2 - SOLICITAR DATOS PARA EL COCHE
    // ==========================================================


    public static void pedirDatosParaAltaCoche() {
        System.out.println("\n=== ALTA DE COCHE ===\n");


        try {

            // 1. Pedir matrícula
            System.out.print(" Ingrese la matrícula : ");

            // Aqui llamamos a la clase VALIDATION
            String matricula = Validation.validarMatricula(sc.nextLine().trim());


            // 2. Pedir marca
            System.out.print("Marca: ");
            String marca = sc.nextLine();

            // 3. Pedir modelo
            System.out.print("Modelo: ");
            String modelo = sc.nextLine();

            // 4. Pedir precio
            System.out.print("Precio base: ");
            BigDecimal precioBase = new BigDecimal(sc.nextLine());

            // 5. Pedir concesionario (esto es más complejo)
            System.out.print("ID del concesionario: ");
            Long idConcesionario = sc.nextLong();


            CocheService.insertarCoche(matricula, marca, modelo, precioBase, idConcesionario);
        } catch (ApplicationException e) {
            System.err.println(e.getMessage());
        }

    }


}



