package exceptions;

public class NotRoleSelectedException extends Exception {
    public NotRoleSelectedException() {
        super("Ningun rol seleccionado");
    }
}
