package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.JobVacancyService;
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
public class DeleteVacancy implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteVacancy.class.getName());

    private static final String SHOW_PAGE = "Controller?command=show_vacancies";

    /**
     * Performs a service level call to delete vacancy
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {
        String jobVacancyId = request.getParameter(ParametersName.JOB_VACANCY_ID);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        JobVacancyService jobVacancyService = serviceFactory.getJobVacancyService();

        try {
            jobVacancyService.deleteVacancy(jobVacancyId);
            response.sendRedirect(SHOW_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
