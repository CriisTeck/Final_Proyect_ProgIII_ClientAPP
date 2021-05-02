package exceptions;

import utils.ExceptionTitle;

public class UserFieldEmptyException extends Exception {

    public UserFieldEmptyException() {
        super(ExceptionTitle.USER_EMPTY_CREATE_USER.getText());
    }
}
