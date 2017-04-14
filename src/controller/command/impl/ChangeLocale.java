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

/**
 * Created by irinaleibutina on 19.03.17.
 */
public class ChangeLocale implements Command {
    private static final Logger logger = LogManager.getLogger(ChangeLocale.class.getName());

    private static final String ATTR_LOCALE = "locale";
    public static final String ATTR_LANG = "lang";

    private static final String INDEX_PAGE = "/";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {

        String locale = request.getParameter(ATTR_LANG);
        request.getSession(true).setAttribute(ATTR_LOCALE, locale);
        response.sendRedirect(request.getContextPath() + INDEX_PAGE);
    }
}
