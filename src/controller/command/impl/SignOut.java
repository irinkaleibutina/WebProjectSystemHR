package controller.command.impl;

import controller.command.Command;
import controller.command.exception.CommandException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by irinaleibutina on 28.03.17.
 */
public class SignOut implements Command {

    private static final String MAIN_PAGE="Controller?command=show_vacancies";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException {
        request.getSession(false).invalidate();
        response.sendRedirect(MAIN_PAGE);
    }
}
