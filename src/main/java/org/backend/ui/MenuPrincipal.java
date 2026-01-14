package org.backend.ui;

import org.backend.service.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
                    System.out.println("Cerrando conexión del sistema...");
                    GestorEntityManager.cerrarConexion();
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
                    SeederService.seed();
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

    // ==========================================================
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


    // ==========================================================
    // PUNTO 2.2 - SOLICITAR DATOS PARA EL COCHE
    // ==========================================================


    public static void pedirDatosParaAltaCoche() {
        System.out.println("\n=== ALTA DE COCHE ===\n");

        try {

            // 1. Pedir matrícula
            System.out.print("Ingrese la matrícula : ");

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
                    pedirDatosParaInstalarExtra();
                }
                case 2 -> {
                    pedirDatosParaRegistrarReparacion();
                }


            }

        }


    }

    // ==========================================================
    // PUNTO 3.1 -> SOLICITAR DATOS PARA INSTALAR EXTRA
    // ==========================================================

    private static void pedirDatosParaInstalarExtra() {
        System.out.println("\n=== INSTALAR EXTRA ===\n");

        try {
            // 1. Pedir matrícula
            System.out.print("Ingrese la matrícula del coche: ");
            String matricula = Validation.validarMatricula(sc.nextLine().trim());

            // 2. Pedir ID equipamiento
            System.out.print("ID del equipamiento a instalar: ");
            Long idEquipamiento = sc.nextLong();
            sc.nextLine();

            // 3. Llamar al servicio
            BigDecimal precioTotal = CocheService.instalarExtra(matricula, idEquipamiento);

            // 4. Mostrar nuevo precio total
            System.out.println("\n>> PRECIO TOTAL DEL COCHE (Base + Extras): " + precioTotal + " €");

        } catch (ApplicationException e) {
            System.err.println(e.getMessage());
        }
    }

    // ==========================================================
    // PUNTO 3.2 -> SOLICITAR DATOS PARA REGISTRAR REPARACIÓN
    // ==========================================================

    private static void pedirDatosParaRegistrarReparacion() {
        System.out.println("\n=== REGISTRAR REPARACIÓN ===\n");

        try {
            // 1. Pedir matrícula
            System.out.print("Ingrese la matrícula del coche: ");
            String matricula = Validation.validarMatricula(sc.nextLine().trim());

            // 2. Pedir ID mecánico
            System.out.print("ID del mecánico: ");
            Long idMecanico = sc.nextLong();
            sc.nextLine();

            // 3. Pedir fecha (formato dd/MM/yyyy)
            System.out.print("Fecha de la reparación (dd/MM/yyyy): ");
            String fechaStr = sc.nextLine().trim();
            LocalDate fecha;
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                fecha = LocalDate.parse(fechaStr, formatter);
            } catch (DateTimeParseException e) {
                throw new ApplicationException("Formato de fecha inválido. Use dd/MM/yyyy");
            }

            // 4. Pedir coste
            System.out.print("Coste de la intervención: ");
            BigDecimal coste = new BigDecimal(sc.nextLine().trim());

            // 5. Pedir descripción
            System.out.print("Descripción breve: ");
            String descripcion = sc.nextLine().trim();

            // 6. Llamar al servicio
            CocheService.registrarReparacion(matricula, idMecanico, fecha, coste, descripcion);

        } catch (ApplicationException e) {
            System.err.println(e.getMessage());
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
                    pedirDatosParaVenderCoche();
                }
            }
        }
    }

    // ==========================================================
    // PUNTO 4.1 -> SOLICITAR DATOS PARA VENDER COCHE
    // ==========================================================

    private static void pedirDatosParaVenderCoche() {
        System.out.println("\n=== VENDER COCHE ===\n");

        try {
            // 1. Pedir datos del nuevo propietario
            System.out.print("DNI del comprador: ");
            String dni = Validation.validarDni(sc.nextLine().trim());

            System.out.print("Nombre del comprador: ");
            String nombrePropietario = sc.nextLine().trim();

            // 2. Pedir matrícula del coche
            System.out.print("Matrícula del coche a vender: ");
            String matricula = Validation.validarMatricula(sc.nextLine().trim());

            // 3. Pedir ID del concesionario que vende
            System.out.print("ID del concesionario que realiza la venta: ");
            Long idConcesionario = sc.nextLong();
            sc.nextLine();

            // 4. Pedir precio final pactado
            System.out.print("Precio final pactado: ");
            BigDecimal precioFinal = new BigDecimal(sc.nextLine().trim());

            // 5. Llamar al servicio
            VentaService.venderCoche(dni, nombrePropietario, matricula, idConcesionario, precioFinal);

        } catch (ApplicationException e) {
            System.err.println(e.getMessage());
        }
    }

    // -------------------------------------------------------------------------
    // CASE 5
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
                case 1 -> consultarStockConcesionario();
                case 2 -> consultarHistorialMecanico();
                case 3 -> consultarVentasPorConcesionario();
                case 4 -> consultarCosteActualCoche();


            }

        }


    }

    //==========================================================
    // PUNTO 5.1 -> STOCK DE CONCESIONARIO
    // ==========================================================

    private static void consultarStockConcesionario() {
        System.out.println("\n=== STOCK DE CONCESIONARIO ===\n");

        try {
            System.out.print("ID del concesionario: ");
            Long idConcesionario = sc.nextLong();
            sc.nextLine();

            var coches = ConsultaService.stockDeConcesionario(idConcesionario);

            if (coches.isEmpty()) {
                System.out.println("No hay coches disponibles en este concesionario.");
            } else {
                System.out.println("\n>> Coches en stock (no vendidos):");
                System.out.println("-".repeat(50));
                for (var coche : coches) {
                    System.out.println("  Matrícula: " + coche.getMatricula() +
                            " | " + coche.getMarca() + " " + coche.getModelo() +
                            " | Precio: " + coche.getPrecioBase() + " €");
                }
                System.out.println("-".repeat(50));
                System.out.println("Total: " + coches.size() + " coche(s)");
            }

        } catch (ApplicationException e) {
            System.err.println(e.getMessage());
        }
    }

    //==========================================================
    // PUNTO 5.2 -> HISTORIAL DE MECÁNICO
    // ==========================================================

    private static void consultarHistorialMecanico() {
        System.out.println("\n=== HISTORIAL DE MECÁNICO ===\n");

        try {
            System.out.print("ID del mecánico: ");
            Long idMecanico = sc.nextLong();
            sc.nextLine();

            var reparaciones = ConsultaService.historialDeMecanico(idMecanico);

            if (reparaciones.isEmpty()) {
                System.out.println("Este mecánico no tiene reparaciones registradas.");
            } else {
                System.out.println("\n>> Reparaciones realizadas:");
                System.out.println("-".repeat(60));
                for (var rep : reparaciones) {
                    System.out.println("  Fecha: " + rep.getFecha() +
                            " | Matrícula: " + rep.getCoche().getMatricula() +
                            " | Coste: " + rep.getCoste() + " €");
                    System.out.println("    Descripción: " + rep.getDescripcion());
                }
                System.out.println("-".repeat(60));
                System.out.println("Total: " + reparaciones.size() + " reparación(es)");
            }

        } catch (ApplicationException e) {
            System.err.println(e.getMessage());
        }
    }

    //==========================================================
    // PUNTO 5.3 -> VENTAS POR CONCESIONARIO
    // ==========================================================

    private static void consultarVentasPorConcesionario() {
        System.out.println("\n=== VENTAS POR CONCESIONARIO ===\n");

        try {
            System.out.print("ID del concesionario: ");
            Long idConcesionario = sc.nextLong();
            sc.nextLine();

            var ventas = ConsultaService.ventasPorConcesionario(idConcesionario);
            var totalRecaudado = ConsultaService.totalRecaudadoPorConcesionario(idConcesionario);

            if (ventas.isEmpty()) {
                System.out.println("Este concesionario no tiene ventas registradas.");
            } else {
                System.out.println("\n>> Ventas realizadas:");
                System.out.println("-".repeat(70));
                for (var venta : ventas) {
                    System.out.println("  Fecha: " + venta.getFecha() +
                            " | Coche: " + venta.getCoche().getMatricula() +
                            " | Comprador: " + venta.getPropietario().getNombre() +
                            " | Precio: " + venta.getPrecioFinal() + " €");
                }
                System.out.println("-".repeat(70));
                System.out.println("Total ventas: " + ventas.size());
                System.out.println("IMPORTE TOTAL RECAUDADO: " + totalRecaudado + " €");
            }

        } catch (ApplicationException e) {
            System.err.println(e.getMessage());
        }
    }

    //==========================================================
    // PUNTO 5.4 -> COSTE ACTUAL DE COCHE
    // ==========================================================

    private static void consultarCosteActualCoche() {
        System.out.println("\n=== COSTE ACTUAL DE COCHE ===\n");

        try {
            System.out.print("Matrícula del coche: ");
            String matricula = Validation.validarMatricula(sc.nextLine().trim());

            BigDecimal costeTotal = ConsultaService.costeActualDeCoche(matricula);

            System.out.println("\n>> COSTE TOTAL INVERTIDO EN EL COCHE " + matricula + ":");
            System.out.println("   (Precio compra + Reparaciones + Extras)");
            System.out.println("-".repeat(40));
            System.out.println("   TOTAL: " + costeTotal + " €");

        } catch (ApplicationException e) {
            System.err.println(e.getMessage());
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
                sc.nextLine();
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




}



