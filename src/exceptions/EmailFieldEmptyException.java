package exceptions;

import utils.ExceptionTitle;

public class EmailFieldEmptyException extends Exception {

    public EmailFieldEmptyException() {
        super(ExceptionTitle.EMAIL_FIELD_EMPTY.getText());
    }
}
