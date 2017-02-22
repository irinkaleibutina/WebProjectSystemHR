package controller.command.impl;

import bean.User;
import controller.command.Command;
import controller.command.exception.CommandException;
import dao.UserDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by irinaleibutina on 19.02.17.
 */
public class SignIn implements Command {
    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException, DAOException {

        System.out.println("login " + request.getParameter(LOGIN));
        System.out.println("password " + request.getParameter(PASSWORD));

        System.out.println("In SignIn");

        DAOFactory daoFactory = DAOFactory.getInstance();
        UserDAO user = daoFactory.getUserDAO();

        user.getUser("", "");
        System.out.println("Should !!! ");

        return null;
    }
}
