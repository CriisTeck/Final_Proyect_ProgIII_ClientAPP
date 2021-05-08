package exceptions;

public class EmptyFieldException extends Exception {
    public EmptyFieldException(String text) {
        super(String.format("El campo '%s' esta vacio",text));
    }
}
