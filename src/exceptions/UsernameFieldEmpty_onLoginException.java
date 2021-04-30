package exceptions;

import utils.ExceptionTitle;

public class UsernameFieldEmpty_onLoginException extends Exception {
    public UsernameFieldEmpty_onLoginException() {
        super(ExceptionTitle.USERNAME_EMPTY_LOGIN);
    }
}
