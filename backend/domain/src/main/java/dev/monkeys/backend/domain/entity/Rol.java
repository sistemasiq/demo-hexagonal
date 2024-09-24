package dev.monkeys.backend.domain.entity;

import dev.monkeys.backend.domain.vo.Id;
import dev.monkeys.backend.domain.vo.TipoRol;
import java.util.List;

public record Rol(Id id, TipoRol tipo, List<Permiso> permisos) {
}
