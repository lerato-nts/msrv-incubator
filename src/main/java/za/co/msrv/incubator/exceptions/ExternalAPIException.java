package za.co.msrv.incubator.exceptions;

public class ExternalAPIException extends RuntimeException {
    public ExternalAPIException(String message) {
        super("External API Call Exception: " + message);
    }
}
