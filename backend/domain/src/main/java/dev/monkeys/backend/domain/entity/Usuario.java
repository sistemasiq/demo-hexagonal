package dev.monkeys.backend.domain.entity;

import dev.monkeys.backend.domain.vo.Id;
import dev.monkeys.backend.domain.vo.NombreUsuario;
import dev.monkeys.backend.domain.vo.PasswordUsuario;
import dev.monkeys.backend.domain.vo.StatusUsuario;

public class Usuario {
    Id id;
    NombreUsuario nombre;
    PasswordUsuario password;
    StatusUsuario status;

}
