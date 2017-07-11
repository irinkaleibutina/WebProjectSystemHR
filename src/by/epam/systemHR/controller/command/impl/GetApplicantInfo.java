package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.bean.Applicant;
import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.systemHR.service.ApplicantService;
import by.epam.systemHR.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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
        applicant = (Applicant) request.getSession(true).getAttribute(ParametersName.APPLICANT);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantService applicantService = serviceFactory.getApplicantService();
        try {
            applicant = applicantService.getActualInformation(applicant.getLogin());

            request.getSession(true).setAttribute(ParametersName.APPLICANT, applicant);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(PROFILE_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
