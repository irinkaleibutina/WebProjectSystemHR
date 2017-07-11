package by.epam.systemHR.controller.command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface Command {

    /**
     * Interface for Command pattern realization
     *
     * @param request  from application's client
     * @param response which will be send to the client
     * @throws IOException
     * @throws ServletException
     */

    void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
