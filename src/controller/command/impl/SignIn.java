package controller.command.impl;

import controller.command.Command;
import controller.command.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by irinaleibutina on 19.02.17.
 */
public class SignIn implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        System.out.println("login " + request.getParameter(LOGIN));
        System.out.println("password "  + request.getParameter(PASSWORD));

        System.out.println("In SignIn");
        return null;
    }
}
