package controller.command.impl;

import bean.Applicant;
import controller.command.Command;
import controller.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ApplicantService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by irinaleibutina on 4/12/17.
 */
public class UpdateApplicant implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateApplicant.class.getName());

    private static final String ATTR_APPLICANT  = "applicant";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String EMAIL = "email";
    private static final String PHONE = "phone";
    private static final String ATTR_ERROR = "error";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Applicant applicant = new Applicant();
        applicant = (Applicant) request.getSession(true).getAttribute(ATTR_APPLICANT);
        applicant.setCountry(request.getParameter(COUNTRY));
        applicant.setEmail(request.getParameter(EMAIL));
        // Add parametrs

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantService applicantService = serviceFactory.getApplicantService();
        try {
            applicantService.updateInfo(applicant);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
