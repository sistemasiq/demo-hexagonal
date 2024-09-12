public record PasswordUsuario(String value) {

    public PasswordUsuario{
        if ( !esSeguro() ) {
            throw new IllegalArgumentException("La contraseña debe tener al menos 8 caracteres");
        }
    }

    public boolean esSeguro() {
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
        if (!value.matches(specialCharPattern)) {
            return false;
        }
        
        // Si todas las condiciones se cumplen, la contraseña es segura
        return true;
    }

}
