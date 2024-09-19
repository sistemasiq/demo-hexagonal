package dev.monkeys.backend.domain.vo;
public record PasswordUsuario(String value) {

    static String error;

    public PasswordUsuario{
        if ( !esSeguro(value) ) {
            throw new IllegalArgumentException("La contraseña no es segura. " + error);
        }
    }

    public boolean esSeguro(String value) {
        // Validaciones comunes para una contraseña segura
        int minLength = 8;
        String upperCasePattern = ".*[A-Z].*";
        String lowerCasePattern = ".*[a-z].*";
        String digitPattern = ".*\\d.*";
        String specialCharPattern = ".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>/?].*" ;

        if (value == null || value.length() < minLength) {
            error = "El password debe tener al menos 8 caracteres";
            return false;
        }
        if (!value.matches(upperCasePattern)) {
            error = "El password debe tener al menos una Mayúscula";
            return false;
        }
        if (!value.matches(lowerCasePattern)) {
            error = "El password debe tener al menos una Mínuscula";
            return false;
        }
        if (!value.matches(digitPattern)) {
            error = "El password debe tener al menos un numero";
            return false;
        }
         
        if (!value.matches(specialCharPattern)) {
            error = "El password debe tener al menos un caracter especial";
            return false;
        }
        
        // Si todas las condiciones se cumplen, la contraseña es segura
        return true;
    }

}
