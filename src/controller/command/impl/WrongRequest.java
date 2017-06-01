package controller.command.impl;

import controller.command.Command;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Instance of {@link Command}
 */
public class WrongRequest implements Command {
    private static final String ERROR404_PAGE = "/WEB-INF/jsp/error404.jsp";

    /**
     * Method redirects to error page
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
        @Override
        public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR404_PAGE);
            requestDispatcher.forward(request, response);
        }
}
