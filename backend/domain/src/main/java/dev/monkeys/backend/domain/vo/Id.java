package dev.monkeys.backend.domain.vo;

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
        
    public static Id withoutId(){
        /* Este método genera un UUID versión 7 utilizando la marca de tiempo y 
        un componente aleatorio, garantizando unicidad y orden cronológico */
        return new Id(UUID.randomUUID(UUID.Version.V7));
    }

}
