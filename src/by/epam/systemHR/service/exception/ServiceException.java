package by.epam.systemHR.service.exception;

/**
 * Created by irinaleibutina on 20.02.17.
 */

public class ServiceException extends Exception {

    public ServiceException() {
        super();
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Exception e) {
        super(e);
    }

    public ServiceException(String message, Exception e) {
        super(message, e);
    }
}