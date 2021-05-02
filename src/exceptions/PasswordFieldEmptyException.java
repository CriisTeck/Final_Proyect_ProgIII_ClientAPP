package exceptions;

import utils.ExceptionTitle;

public class PasswordFieldEmptyException extends Exception {

    public PasswordFieldEmptyException() {
        super(ExceptionTitle.PASSWORD_EMPTY_LOGIN.getText());
    }

}
