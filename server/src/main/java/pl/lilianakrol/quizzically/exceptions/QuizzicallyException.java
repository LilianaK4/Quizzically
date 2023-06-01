package pl.lilianakrol.quizzically.exceptions;

public class QuizzicallyException extends RuntimeException {
    public QuizzicallyException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public QuizzicallyException(String exMessage) {
        super(exMessage);
    }
}
