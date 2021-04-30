package exceptions;

import utils.ExceptionTitle;

public class BadEmailFormatException extends Exception {
    public BadEmailFormatException() {
        super(ExceptionTitle.BAD_EMAIL_FORMAT);
    }
}
