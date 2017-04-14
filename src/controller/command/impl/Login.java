package controller.command.impl;

import bean.Applicant;
import bean.EmployeeHR;
import controller.command.Command;
import controller.command.exception.CommandException;
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

/**
 * Created by irinaleibutina on 21.03.17.
 */
public class Login implements Command {
    private static final Logger logger = LogManager.getLogger(Login.class.getName());

    private static final String ATTR_LOGIN = "login";
    private static final String ATTR_PASSWORD = "password";

    private static final String AUTH_PAGE = "/WEB-INF/jsp/signIn.jsp";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
    private static final String ADMIN_PROFILE = "/WEB-INF/jsp/adminProfile.jsp";  //// check using jsp ????
    private static final String APPLICANT_PROFILE = "/WEB-INF/jsp/userProfile.jsp";
    private static final String ATTR_APPLICANT = "applicant";
    private static final String ATTR_EMPLOYEE = "employee";
    private static final String ATTR_FAIL = "fail";
    private static final String ATTR_ERROR = "error";


    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantService applicantService = serviceFactory.getApplicantService();
        EmployeeService employeeService = serviceFactory.getEmployeeService();

        String login = request.getParameter(ATTR_LOGIN);
        String password = request.getParameter(ATTR_PASSWORD);

        Applicant applicant = null;
        EmployeeHR employeeHR = null;

        try {
            applicant = applicantService.signIn(login, password);
            employeeHR = employeeService.signIn(login, password);
            if (applicant != null) {
                request.getSession(true).setAttribute(ATTR_APPLICANT, applicant);
                //
            }
            if (employeeHR != null) {
                request.getSession(true).setAttribute(ATTR_EMPLOYEE, employeeHR);
                //
            }
            if (applicant == null && employeeHR == null) {
                request.setAttribute(ATTR_FAIL, "user not found");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(AUTH_PAGE);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }

        // redirect
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PROFILE);
        requestDispatcher.forward(request, response);

    }
}
