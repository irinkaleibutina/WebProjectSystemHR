package controller.command.impl;

import bean.Applicant;
import bean.EmployeeHR;
import controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ApplicantService;
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
public class UpdateAdmin implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateAdmin.class.getName());

    private static final String SHOW_PAGE = "/WEB-INF/jsp/profile.jsp";

    /**
     * Performs a service level call to update admin info
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EmployeeHR employeeHR = new EmployeeHR();
        employeeHR = (EmployeeHR) request.getSession(true).getAttribute(EMPLOYEE);

        employeeHR.setName(request.getParameter(NAME));
        employeeHR.setSurname(request.getParameter(SURNAME));
        employeeHR.setEmail(request.getParameter(EMAIL));
        employeeHR.setPhoneNumber(request.getParameter(PHONE));
        employeeHR.setWorkId(request.getParameter(WORK_ID));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        EmployeeService employeeService = serviceFactory.getEmployeeService();
        try {
            employeeService.updateEmployeeInfo(employeeHR);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
