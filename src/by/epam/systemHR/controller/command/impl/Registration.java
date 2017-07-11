package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.bean.Applicant;
import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.ApplicantService;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.service.factory.ServiceFactory;
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

        String name = request.getParameter(ParametersName.NAME);
        String surname = request.getParameter(ParametersName.SURNAME);
        String email = request.getParameter(ParametersName.EMAIL);
        String password = request.getParameter(ParametersName.PASSWORD);
        String login = request.getParameter(ParametersName.LOGIN);
        String phone = request.getParameter(ParametersName.PHONE);

        Applicant applicant = new Applicant();
        applicant.setName(name);
        applicant.setSurname(surname);
        applicant.setLogin(login);
        applicant.setPassword(password);
        applicant.setEmail(email);
        applicant.setPhoneNumber(phone);

        try {
            if (applicantService.applicantRegistration(applicant)) {
                request.getSession(true).setAttribute(ParametersName.APPLICANT, applicant);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ADMIN_PROFILE);
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute(ATTR_FAIL, "Such user is exist");
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


