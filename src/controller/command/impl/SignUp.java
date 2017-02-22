package controller.command.impl;

import controller.command.Command;
import controller.command.exception.CommandException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by irinaleibutina on 19.02.17.
 */
public class SignUp implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException, ServletException, IOException {

        System.out.println("SignUp");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/signUp.jsp");
        requestDispatcher.forward(request, response);
        return null;
    }
}


