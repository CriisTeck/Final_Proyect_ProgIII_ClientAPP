package exceptions;

public class NotNumbersIngresedException extends Exception {
    public NotNumbersIngresedException(String text) {
        super(String.format("El campo '%s' solo debe contener numeros",text));
    }
}
