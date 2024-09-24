package dev.monkeys.backend.domain.vo;

import java.util.Set;

public record NombrePermiso(String value)  {

    // Lista de permisos válidos (esto puede provenir de una base de datos o configuración)
    private static final Set<String> PERMISOS_VALIDOS = Set.of(
        "READ", "WRITE", "DELETE", "ADMIN", "ALTER", "DISABLED"
    );

    public NombrePermiso {
        // Verificar que el valor no sea nulo ni vacío
        if (value == null || value.isBlank()) {
            throw new IllegalArgumentException("El nombre del permiso no puede ser nulo o vacío.");
        }

        // Validar que esté en mayúsculas
        if (!value.equals(value.toUpperCase())) {
            throw new IllegalArgumentException("El nombre del permiso debe estar en mayúsculas.");
        }

        // Validar que el nombre del permiso sea uno de los permitidos
        if (!PERMISOS_VALIDOS.contains(value)) {
            throw new IllegalArgumentException("El permiso no es válido. Permisos válidos: " + PERMISOS_VALIDOS);
        }
    }
}
