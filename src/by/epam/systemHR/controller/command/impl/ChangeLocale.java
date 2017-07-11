package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Instance of {@link Command}
 */
public class ChangeLocale implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeLocale.class.getName());

    /**
     * Method changes locale for application
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String locale = request.getParameter(ParametersName.ATTR_LANG);
        request.getSession(true).setAttribute(ParametersName.ATTR_LOCALE, locale);
        response.sendRedirect(request.getContextPath() + ParametersName.INDEX_PAGE);
    }
}
