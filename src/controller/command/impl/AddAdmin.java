package controller.command.impl;

import bean.EmployeeHR;
import controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.EmployeeService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class AddAdmin implements Command {
    private static final Logger logger = LogManager.getLogger(AddAdmin.class.getName());

    /**
     * Performs a service level call to add new employee
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        EmployeeService employeeService = serviceFactory.getEmployeeService();

        EmployeeHR employeeHR = new EmployeeHR();
        employeeHR.setName(request.getParameter(NAME));
        employeeHR.setSurname(request.getParameter(SURNAME));
        employeeHR.setEmail(request.getParameter(EMAIL));
        employeeHR.setPhoneNumber(request.getParameter(PHONE));
        employeeHR.setWorkId(request.getParameter(WORK_ID));
        employeeHR.setLogin(request.getParameter(LOGIN));
        employeeHR.setPassword(request.getParameter(PASSWORD));

        try {
            employeeService.employeeRegistration(employeeHR);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
