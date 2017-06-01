package controller.command.impl;

import controller.command.Command;
import controller.command.exception.CommandException;
import dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.exception.ServiceException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import static controller.util.ParametersName.*;

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

        String locale = request.getParameter(ATTR_LANG);
        request.getSession(true).setAttribute(ATTR_LOCALE, locale);
        response.sendRedirect(request.getContextPath() + INDEX_PAGE);
    }
}
