package exceptions;

import utils.ExceptionTitle;

public class UsernameFieldEmptyException extends Exception {
    public UsernameFieldEmptyException() {
        super(ExceptionTitle.USERNAME_EMPTY_LOGIN.getText());
    }
}
