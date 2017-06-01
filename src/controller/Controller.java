package controller;

import controller.command.Command;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Front controller of the application
 *
 * Handles HTTP requests and HTTP response to certain instance of
 * {@link Command}
 */
@WebServlet(name = "Controller")
public class Controller extends HttpServlet {

    private static final String COMMAND = "command";

    private final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();
    }

    /**
     * Called by the server to handle client GET request type
     *
     * Delegates request processing to
     * {@link #processRequest(HttpServletRequest, HttpServletResponse)}
     *
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Called by the server to handle client POST request type
     *
     * Delegates request processing to
     * {@link #processRequest(HttpServletRequest, HttpServletResponse)}
     *
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        processRequest(request, response);
    }

    /**
     * Method, which processing both POST and GET request from a server client.
     * and return the response.
     *
     * Defining type of command, getting certain instance of {@link Command}
     * and execute it.
     *
     * @param request
     *            contains the request object of a server client
     * @param response
     *            contains the response object which will be send to the client
     * @throws ServletException
     * @throws IOException
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String commandName = null;
        Command command = null;
        commandName = request.getParameter(COMMAND);
        command = provider.getCommand(commandName);
        command.execute(request, response);
    }
}
