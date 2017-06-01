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

import static controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class Registration implements Command {
    private static final Logger logger = LogManager.getLogger(Registration.class.getName());

    private static final String ADMIN_PROFILE = "index.jsp";
    private static final String ATTR_FAIL= "fail_registration";

    /**
     * Performs a service level call to add new user
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantService applicantService = serviceFactory.getApplicantService();

        String name = request.getParameter(NAME);
        String surname = request.getParameter(SURNAME);
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String login = request.getParameter(LOGIN);
        String phone = request.getParameter(PHONE);

        Applicant applicant = new Applicant();
        applicant.setName(name);
        applicant.setSurname(surname);
        applicant.setLogin(login);
        applicant.setPassword(password);
        applicant.setEmail(email);
        applicant.setPhoneNumber(phone);

        try {
            if (applicantService.applicantRegistration(applicant)) {
                request.getSession(true).setAttribute(APPLICANT, applicant);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PROFILE);
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute(ATTR_FAIL, "Such user is exist");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e.getMessage());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}


