package dev.monkeys.backend.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import dev.monkeys.backend.domain.vo.*;

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
            assertDoesNotThrow(() -> new PasswordUsuario("SecurePass123"));
        }

        @Test
        void constructor_shouldThrowForShortPassword() {
            assertThrows(IllegalArgumentException.class, () -> new PasswordUsuario("1234"));
        }

    @Test
        void constructor_shouldThrowForNullPassword() {
            assertThrows(IllegalArgumentException.class, () -> new PasswordUsuario(null));
        }


}
