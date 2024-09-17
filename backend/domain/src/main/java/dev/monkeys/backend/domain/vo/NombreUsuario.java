package dev.monkeys.backend.domain.vo;

public record NombreUsuario(String value){
    public NombreUsuario {
        if ( (value == null) || (value.length()<8) ) {
            throw new IllegalArgumentException("El nombre de usuario debe tener al menos 8 caracteres");
        }
    }

} 
