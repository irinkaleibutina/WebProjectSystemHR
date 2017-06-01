package controller.command.impl;

import bean.Applicant;
import controller.command.Command;
import controller.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ApplicantJobVacancyService;
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
public class SubmitApplication implements Command {
    private static final Logger logger = LogManager.getLogger(SubmitApplication.class.getName());

    private static final String SHOW_PAGE = "Controller?command=get_applicant";

    /**
     * Performs a service level call to add apply to concrete applicant
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws  IOException, ServletException {

        Applicant applicant = new Applicant();
        applicant = (Applicant) request.getSession(true).getAttribute(APPLICANT);
        String applicantId = ""+applicant.getId();
        String jobVacancyId = request.getParameter(JOB_VACANCY_ID);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantJobVacancyService applicantJobVacancyService = serviceFactory.getApplicantJobVacancyService();
        try {
            applicantJobVacancyService.submitApplication(applicantId, jobVacancyId);
            response.sendRedirect(SHOW_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }

    }
}
