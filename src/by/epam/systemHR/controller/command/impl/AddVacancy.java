package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.JobVacancyService;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.service.factory.ServiceFactory;
import by.epam.systemHR.bean.JobVacancy;
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
public class AddVacancy implements Command {
    private static final Logger logger = LogManager.getLogger(AddVacancy.class.getName());

    private static final String SHOW_PAGE = "Controller?command=show_vacancies";

    /**
     * Performs a service level call to add new vacancy
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        JobVacancyService jobVacancyService = serviceFactory.getJobVacancyService();

        JobVacancy jobVacancy = new JobVacancy();

        jobVacancy.setJobTitle(request.getParameter(ParametersName.ATTR_TITLE));
        jobVacancy.setDescription(request.getParameter(ParametersName.ATTR_DESCRIPTION));
        jobVacancy.setCountry(request.getParameter(ParametersName.COUNTRY));
        jobVacancy.setCity(request.getParameter(ParametersName.CITY));

        try {
            jobVacancyService.addVacancy(jobVacancy);
            response.sendRedirect(SHOW_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
