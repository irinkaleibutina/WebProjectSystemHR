package by.epam.systemHR.controller.command.exception;

/**
 * Created by irinaleibutina on 19.02.17.
 */
public class CommandException extends Exception {

    public CommandException() {
    }

    public CommandException(String message, Throwable exception) {
        super(message, exception);
    }

    public CommandException(String message) {
        super(message);
    }

    public CommandException(Throwable exception){
        super(exception);
    }
}
