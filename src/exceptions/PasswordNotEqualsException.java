package exceptions;

import utils.ExceptionTitle;

public class PasswordNotEqualsException extends Exception {
    public PasswordNotEqualsException() {
        super(ExceptionTitle.PASSWORD_NOT_EQUALS.getText());
    }
}
