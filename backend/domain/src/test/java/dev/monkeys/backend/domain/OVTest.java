package dev.monkeys.backend.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import dev.monkeys.backend.domain.vo.Id;
import dev.monkeys.backend.domain.vo.NombreUsuario;
import dev.monkeys.backend.domain.vo.PasswordUsuario;

public class OVTest {
    @Test
    void constructor_shouldAcceptValidUsername() {
        assertDoesNotThrow(() -> new NombreUsuario("devmonkey"));
    }

    @Test
    void constructor_shouldThrowForShortUsername() {
        assertThrows(IllegalArgumentException.class, () -> new NombreUsuario("hola"));
    }

    @Test
    void constructor_shouldThrowForNullUsername() {
        assertThrows(IllegalArgumentException.class, () -> new NombreUsuario(null));
    }

    @Test
    void constructor_shouldAcceptValidPassword() {
        assertDoesNotThrow(() -> new PasswordUsuario("Secure[]Pass123#"));
    }

    @Test
    void constructor_shouldThrowForShortPassword() {
        assertThrows(IllegalArgumentException.class, () -> new PasswordUsuario("1234"));
    }

    @Test
    void constructor_shouldThrowForNullPassword() {
        assertThrows(IllegalArgumentException.class, () -> new PasswordUsuario(null));
    }

    @Test
    void generate_shouldReturnUniqueIds() {
        Id id1 = Id.withoutId();
        Id id2 = Id.withoutId();
        assertNotEquals(id1, id2);
    }

    @RepeatedTest(100) // Generar 100 tests para comprobar la generacion correcta de UUIDs v7
    void generate_shouldReturnValidUUIDv7() {
        assertDoesNotThrow(() -> Id.withoutId());
    }

    @Test
    void constructor_shouldAcceptValidUUIDv7() {
        String uuid = Id.withoutId().getId().toString();
        assertDoesNotThrow(() -> Id.withId(uuid));
    }

    @Test
    void constructor_shouldNotAcceptInvalidUUIDv7() {
        UUID uuid = UUID.randomUUID();
        assertThrows(IllegalArgumentException.class, () -> Id.withId(uuid.toString()));
    }
    

    @Test
    void withId_shouldAcceptVersion7UUID() {
        // Crear un UUID de versión 7 válido
        UUID version7UUID = UUID.fromString("7f84b243-7796-7def-9877-c29583c3c31b");

        // No debería lanzar ninguna excepción
        assertDoesNotThrow(() -> Id.withId(version7UUID.toString()));
    }

    @Test
    void withId_shouldThrowExceptionForNonVersion7UUID() {
        // Crear un UUID de versión 4 (o cualquier otra versión)
        UUID version4UUID = UUID.randomUUID(); // Esto generará un UUID versión 4

        // Debe lanzar IllegalArgumentException para versiones que no son 7
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            Id.withId(version4UUID.toString());
        });

        // Verificar el mensaje de la excepción
        assertEquals("El UUID proporcionado no es de versión 7", exception.getMessage());
    }
}
