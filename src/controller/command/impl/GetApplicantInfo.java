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
public class GetApplicantInfo implements Command {
    private static final Logger logger = LogManager.getLogger(GetApplicantInfo.class.getName());

    private static final String PROFILE_PAGE = "/WEB-INF/jsp/profile.jsp";

    /**
     * Performs a service level call to get info about applicant
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Applicant applicant = new Applicant();
        applicant = (Applicant) request.getSession(true).getAttribute(APPLICANT);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantService applicantService = serviceFactory.getApplicantService();
        try {
            applicant = applicantService.getActualInformation(applicant.getLogin());

            request.getSession(true).setAttribute(APPLICANT, applicant);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PROFILE_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
