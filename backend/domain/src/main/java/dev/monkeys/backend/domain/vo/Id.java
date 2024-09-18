package dev.monkeys.backend.domain.vo;

import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Id {

    private static final SecureRandom random = new SecureRandom();
    private final UUID id;

    private Id(UUID id) {
        ValidUUIDv7(id);
        this.id = id; 
    }

    public static Id withId(String id) {
        return new Id(UUID.fromString(id));
    }

    public static Id withoutId() {
        byte[] value = randomBytes();
        ByteBuffer buf = ByteBuffer.wrap(value);
        long high = buf.getLong();
        long low = buf.getLong();
        return new Id(new UUID(high, low));
    }

    public static byte[] randomBytes() {
        // random bytes
        byte[] value = new byte[16];
        random.nextBytes(value);

        // current timestamp in ms
        ByteBuffer timestamp = ByteBuffer.allocate(Long.BYTES);
        timestamp.putLong(System.currentTimeMillis());

        // timestamp
        System.arraycopy(timestamp.array(), 2, value, 0, 6);

        // version and variant
        value[6] = (byte) ((value[6] & 0x0F) | 0x70);
        value[8] = (byte) ((value[8] & 0x3F) | 0x80);

        return value;
    }

    private void ValidUUIDv7(UUID uuid) {
        if (7 != uuid.version())
            throw new IllegalArgumentException("Version " + uuid.version() + " del UUID invalida");

        // Verificar que la variante es correcta (2)
        if (2 != uuid.variant())
            throw new IllegalArgumentException("Variante " + uuid.variant() + " del UUID invalida");

        // Verificar que los últimos bits son aleatorios
        // Esto es una comprobación simplificada, ya que no podemos predecir los bits
        // aleatorios
        if (0 == (uuid.getLeastSignificantBits() & 0xFFFF))
            throw new IllegalArgumentException("Bits del UUID invalidos");
    }
}
