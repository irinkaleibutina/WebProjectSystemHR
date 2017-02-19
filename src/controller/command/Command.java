package controller.command;

import controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by irinaleibutina on 19.02.17.
 */

public interface Command {
    String execute(HttpServletRequest request) throws CommandException;
}
