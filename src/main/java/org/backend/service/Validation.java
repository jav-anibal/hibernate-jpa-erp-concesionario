package org.backend.service;

public class Validation {

    public static final String REGEX_MATRICULA = "^\\d{4}[A-Za-z]{3}$";
    private static final String REGEX_DNI = "^\\d{8}[A-Z]$";


    // ===== VALIDAR MATRÍCULA =====
    public static String validarMatricula(String matricula) throws ApplicationException {
        if (matricula == null || matricula.isBlank()) {
            throw new ApplicationException("La matrícula no puede estar vacía");
        }

        // Limpiar y convertir a mayúsculas
        matricula = matricula.trim().toUpperCase();

        if (!matricula.matches(REGEX_MATRICULA)) {
            throw new ApplicationException(
                    "Matrícula inválida. Formato: 4 dígitos + 3 letras (ej: 1234ABC)"
            );
        }

        return matricula;  // Devuelve la matrícula limpia
    }

    // ===== VALIDAR DNI =====
    public static String validarDni(String dni) throws ApplicationException {
        if (dni == null || dni.isBlank()) {
            throw new ApplicationException("El DNI no puede estar vacío");
        }

        dni = dni.trim().toUpperCase();

        if (!dni.matches(REGEX_DNI)) {
            throw new ApplicationException(
                    "DNI inválido. Formato: 8 dígitos + 1 letra (ej: 12345678A)"
            );
        }

        return dni;
    }

    // ===== VALIDAR PRECIO =====
    public static void validarPrecio(Double precio, String campo) throws ApplicationException {
        if (precio == null || precio <= 0) {
            throw new ApplicationException(
                    campo + " debe ser mayor que 0"
            );
        }
    }




}



