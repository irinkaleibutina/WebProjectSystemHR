package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        String src = ParametersName.JSP_DIR + request.getParameter(ParametersName.ATTR_PAGE);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(src);
        requestDispatcher.forward(request, response);
    }
}
