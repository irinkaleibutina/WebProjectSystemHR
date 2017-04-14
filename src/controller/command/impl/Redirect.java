package controller.command.impl;

import controller.command.Command;
import controller.command.exception.CommandException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by irinaleibutina on 05.04.17.
 */
public class Redirect implements Command {
    public final static String JSP_DIR = "/WEB-INF/jsp/";
    public final static String ATTR_PAGE = "page";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        String src = JSP_DIR + request.getParameter(ATTR_PAGE);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher(src);
        requestDispatcher.forward(request, response);
    }
}
