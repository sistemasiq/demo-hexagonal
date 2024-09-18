package dev.monkeys.backend.domain;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.UUID;

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
        
    @Test
        void generate_shouldReturnValidUUIDv7() {
            Id id = Id.withoutId();
            System.out.println(id);
            UUID uuid =id.getId();
            System.out.println(uuid);
            // Verificar que es versión 7
            System.out.println(uuid.version());
            assertEquals(7, uuid.version());
            
            // Verificar que la variante es correcta (2)
            System.out.println(uuid.variant());
            assertEquals(2, uuid.variant());
            
            // Extraer el timestamp del UUID
            long timestamp = extractTimestampFromUUID(uuid);
            
            // Verificar que el timestamp está cerca del tiempo actual
            long now = Instant.now().toEpochMilli();
            assertTrue(Math.abs(now - timestamp) < 1000, "El timestamp del UUID debe estar dentro de 1 segundo del tiempo actual");
            
            // Verificar que los últimos bits son aleatorios
            // Esto es una comprobación simplificada, ya que no podemos predecir los bits aleatorios
            assertNotEquals(0, uuid.getLeastSignificantBits() & 0xFFFF);
        }
        
        private long extractTimestampFromUUID(UUID uuid) {
            long msb = uuid.getMostSignificantBits();
            long timePart = msb >>> 16;
            return timePart;
        }  


}
