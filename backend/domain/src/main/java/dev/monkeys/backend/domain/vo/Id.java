package dev.monkeys.backend.domain.vo;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public class Id {

    private final UUID id;

    private Id(UUID id){
        this.id = id;
    }

    public static Id withId(String id){
        return new Id(UUID.fromString(id));
    }
   
    public static Id withoutId() {
        /* Este método genera un UUID versión 7 utilizando la marca de tiempo y 
        un componente aleatorio, garantizando unicidad y orden cronológico */

        long time = Instant.now().toEpochMilli();  // Marca de tiempo en milisegundos
        long mostSigBits = time << 16;  // Dejar espacio para los bits de la versión
        mostSigBits |= (7 << 12);  // Establecer la versión 7

        Random random = new Random();
        long leastSigBits = random.nextLong();  // Bits menos significativos (aleatorios)

        // El UUID tiene 128 bits; completar los bits más significativos con valores aleatorios
        mostSigBits |= (random.nextLong() & 0x000000000000FFFFL);

        return new Id(new UUID(mostSigBits, leastSigBits));
    }

}
