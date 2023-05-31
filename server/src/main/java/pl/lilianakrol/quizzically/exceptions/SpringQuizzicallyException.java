package pl.lilianakrol.quizzically.exceptions;

public class SpringQuizzicallyException extends RuntimeException {
    public SpringQuizzicallyException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public SpringQuizzicallyException(String exMessage) {
        super(exMessage);
    }
}
