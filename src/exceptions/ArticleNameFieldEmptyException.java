package exceptions;

public class ArticleNameFieldEmptyException extends Exception {
    public ArticleNameFieldEmptyException() {
        super("Campo 'Articulo' Vacio");
    }
}
