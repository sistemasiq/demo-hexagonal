package dev.monkeys.backend.domain.vo;

public record NombreUsuario(String value){
    public NombreUsuario {
        if ( (value.length()<8) || (value == null) ) {
            throw new IllegalArgumentException("El nombre de usuario debe tener al menos 8 caracteres");
        }
    }

} 
