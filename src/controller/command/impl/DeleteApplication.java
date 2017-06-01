package controller.command.impl;

import bean.Applicant;
import controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ApplicantJobVacancyService;
import service.JobVacancyService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.util.ParametersName.ATTR_ERROR;
import static controller.util.ParametersName.ERROR_PAGE;

import static controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class DeleteApplication implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteApplication.class.getName());

    private static final String SHOW_PAGE = "Controller?command=get_applicant";

    /**
     * Performs a service level call to delete application
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Applicant applicant = (Applicant) request.getSession(true).getAttribute(APPLICANT);

        String applicantId = "" + applicant.getId();
        String jobVacancyId = "" + applicant.getJobVacancy().getId();

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        ApplicantJobVacancyService applicantJobVacancyService = serviceFactory.getApplicantJobVacancyService();
        try {
            applicantJobVacancyService.deleteApplication(applicantId, jobVacancyId);
            response.sendRedirect(SHOW_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
