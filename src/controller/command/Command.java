package controller.command;

import controller.command.exception.CommandException;
import dao.exception.DAOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by irinaleibutina on 19.02.17.
 */

public interface Command {
    String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException, ServletException, IOException, DAOException;
}
