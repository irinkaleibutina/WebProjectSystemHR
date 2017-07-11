package by.epam.systemHR.dao.exception;

/**
 * Class {@code DAOException} is the class, that extends {@code Exception} class
 * to get own exceptions for "DAO" layer.
 * @author irinaleibutina
 */
public class DAOException extends Exception {

    public DAOException() {
        super();
    }
    public DAOException(String message) {
        super(message);
    }
    public DAOException(Exception e) {
        super(e);
    }
    public DAOException(String message, Exception e) {
        super(message, e);
    }
}
