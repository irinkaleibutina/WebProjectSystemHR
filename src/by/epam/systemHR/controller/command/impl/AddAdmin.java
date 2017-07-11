package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.EmployeeService;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.service.factory.ServiceFactory;
import by.epam.systemHR.bean.EmployeeHR;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        employeeHR.setName(request.getParameter(ParametersName.NAME));
        employeeHR.setSurname(request.getParameter(ParametersName.SURNAME));
        employeeHR.setEmail(request.getParameter(ParametersName.EMAIL));
        employeeHR.setPhoneNumber(request.getParameter(ParametersName.PHONE));
        employeeHR.setWorkId(request.getParameter(ParametersName.WORK_ID));
        employeeHR.setLogin(request.getParameter(ParametersName.LOGIN));
        employeeHR.setPassword(request.getParameter(ParametersName.PASSWORD));

        try {
            employeeService.employeeRegistration(employeeHR);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
