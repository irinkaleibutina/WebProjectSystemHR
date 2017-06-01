package controller.command.impl;

import controller.command.Command;
import controller.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Instance of {@link Command}
 */
public class SignOut implements Command {

    private static final String MAIN_PAGE="Controller?command=main_page";

    /**
     * Method allows to sign out from application
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession(false).invalidate();
        response.sendRedirect(MAIN_PAGE);
    }
}
