package utils;

public enum ExceptionTitle {

    PASSWORD_EMPTY_LOGIN("Campo 'Contraseña' Vacio"),
    USERNAME_EMPTY_LOGIN("Campo 'Usuario' Vacio"),
    BAD_EMAIL_FORMAT("Correo Electronico invalido"),
    USER_EMPTY_CREATE_USER("Campo 'Nombre' Vacio"),
    EMAIL_FIELD_EMPTY("Campo 'Email' Vacio"),
    PASSWORD_NOT_EQUALS("Las contraseñas no coinciden");

    String text;

    ExceptionTitle(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}
