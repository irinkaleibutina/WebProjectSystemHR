package controller.command.impl;

import bean.User;
import controller.command.Command;
import controller.command.exception.CommandException;
import service.SignUpService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

/**
 * Created by irinaleibutina on 19.02.17.
 */
public class SignUp implements Command {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws CommandException, ServletException, IOException{


        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        SignUpService signUpService = serviceFactory.getSignUpService();


        //    employeeHR.getName());
        //    employeeHR.getSurname());
        //    employeeHR.getPassword());
        //    employeeHR.getLogin());
        //   employeeHR.getPhoneNumber());
        //    employeeHR.getEmail());
        //    employeeHR.getWorkId());
//

        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String password = request.getParameter("password");
        String login = request.getParameter("login");

        System.out.println("Param from form"+ name+surname+login + password);

       User user = new User();
       user.setName(name);
       user.setSurname(surname);
       user.setPassword(password);
       user.setLogin(login);


        try {
            if (signUpService.SignUp(user)) {
                request.getSession().setAttribute("dsd", user);
            } else {
                request.setAttribute("dsfdsf", true);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/jsp/error.jsp");
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}


