package dev.monkeys.backend.domain.vo;
public record PasswordUsuario(String value) {

    public PasswordUsuario{
        if ( !esSeguro(value) ) {
            throw new IllegalArgumentException("La contraseña no es segura");
        }
    }

    public boolean esSeguro(String value) {
        // Validaciones comunes para una contraseña segura
        int minLength = 8;
        String upperCasePattern = ".*[A-Z].*";
        String lowerCasePattern = ".*[a-z].*";
        String digitPattern = ".*\\d.*";
        String specialCharPattern = ".*[!@#$%^&*()_+\\-=[\\]{};':\"\\\\|,.<>/?].*";

        if (value == null || value.length() < minLength) {
            return false;
        }
        if (!value.matches(upperCasePattern)) {
            return false;
        }
        if (!value.matches(lowerCasePattern)) {
            return false;
        }
        if (!value.matches(digitPattern)) {
            return false;
        }
        /* 
        if (!value.matches(specialCharPattern)) {
            System.out.println("fallo especiales");
            return false;
        }*/
        
        // Si todas las condiciones se cumplen, la contraseña es segura
        return true;
    }

}
