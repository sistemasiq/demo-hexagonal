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
}
