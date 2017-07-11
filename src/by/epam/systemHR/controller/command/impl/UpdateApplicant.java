package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.bean.Applicant;
import by.epam.systemHR.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.systemHR.service.ApplicantService;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static by.epam.systemHR.controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class UpdateApplicant implements Command {
    private static final Logger logger = LogManager.getLogger(UpdateApplicant.class.getName());

    private static final String SHOW_PAGE = "Controller?command=get_applicant";

    /**
     * Performs a service level call to update applicant info
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
        applicant.setCountry(request.getParameter(COUNTRY));
        applicant.setEmail(request.getParameter(EMAIL));
        applicant.setCity(request.getParameter(CITY));
        applicant.setName(request.getParameter(NAME));
        applicant.setSurname(request.getParameter(SURNAME));
        applicant.setPhoneNumber(request.getParameter(PHONE));

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantService applicantService = serviceFactory.getApplicantService();
        try {
            applicantService.updateInfo(applicant);

//            RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
//            requestDispatcher.forward(request, response);
            response.sendRedirect(SHOW_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
