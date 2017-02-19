package controller.command.impl;

import controller.command.Command;
import controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by irinaleibutina on 19.02.17.
 */
public class SignUp implements Command {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return null;
    }
}
