package controller.command.impl;

import controller.command.Command;
import controller.command.exception.CommandException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class Redirect implements Command {

    /**
     * Method allows to redirect to other page
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String src = JSP_DIR + request.getParameter(ATTR_PAGE);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(src);
        requestDispatcher.forward(request, response);
    }
}
