package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.bean.Applicant;
import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.ApplicantService;
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
public class Login implements Command {
    private static final Logger logger = LogManager.getLogger(Login.class.getName());

    private static final String ADMIN_PROFILE = "index.jsp";
    private static final String ATTR_FAIL = "fail_login";

    /**
     * Performs a service level call get concrete user
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantService applicantService = serviceFactory.getApplicantService();
        EmployeeService employeeService = serviceFactory.getEmployeeService();

        String login = request.getParameter(ParametersName.LOGIN);
        String password = request.getParameter(ParametersName.PASSWORD);

        Applicant applicant = null;
        EmployeeHR employeeHR = null;

        try {
            applicant = applicantService.signIn(login, password);
            employeeHR = employeeService.signIn(login, password);
            if (applicant != null) {
                request.getSession(true).setAttribute(ParametersName.APPLICANT, applicant);
                 RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PROFILE);
                requestDispatcher.forward(request, response);
            }
            if (employeeHR != null) {
                request.getSession(true).setAttribute(ParametersName.EMPLOYEE, employeeHR);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PROFILE);
                requestDispatcher.forward(request, response);
            }
            if (applicant == null && employeeHR == null) {
                request.setAttribute(ATTR_FAIL, "user not found");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
