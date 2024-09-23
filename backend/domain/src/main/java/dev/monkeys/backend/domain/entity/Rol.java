package dev.monkeys.backend.domain.entity;

import dev.monkeys.backend.domain.vo.Id;
import dev.monkeys.backend.domain.vo.TipoRol;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Rol {
    Id id;
    TipoRol tipo;
}
