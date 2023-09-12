package za.co.msrv.incubator.service;

public class ExternalAPIException extends RuntimeException {
    public ExternalAPIException(String message) {
        super("External API Call Exception: " + message);
    }
}
