package controller;

import controller.command.Command;
import controller.command.exception.CommandException;
import dao.exception.DAOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by irinaleibutina on 19.02.17.
 */

@WebServlet(name = "Controller")
public class Controller extends HttpServlet {
    private static final String COMMAND = "command";
    private static final String ERROR="/WEB-INF/jsp/error.jsp";

    private final CommandProvider provider = new CommandProvider();

    public Controller() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goTo = null;
        String commandName = null;
        Command command = null;

        System.out.println("in java get");

        try {
            commandName = request.getParameter(COMMAND);
            command = provider.getCommand(commandName);
            goTo = command.execute(request,response);
            RequestDispatcher dispatcher = request.getRequestDispatcher(goTo);

            dispatcher.forward(request, response);
        } catch (CommandException e) {
            request.getRequestDispatcher(ERROR).forward(request, response);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String goTo = null;
        String commandName = null;
        Command command = null;


        System.out.println("in java post");
        try {
            commandName = request.getParameter(COMMAND);
            command = provider.getCommand(commandName);
            goTo = command.execute(request, response);
            response.sendRedirect(goTo);
        } catch (CommandException e) {
            request.getRequestDispatcher(ERROR).forward(request, response);
        } catch (DAOException e) {
            e.printStackTrace();
        }
    }
}
