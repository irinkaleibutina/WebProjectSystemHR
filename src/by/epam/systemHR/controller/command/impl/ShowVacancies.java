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
import java.util.List;

/**
 * Instance of {@link Command}
 */
public class ShowVacancies implements Command {
    private static final Logger logger = LogManager.getLogger();

    private static final String SHOW_PAGE = "/WEB-INF/jsp/take_all_vacancies.jsp";
    private static final String ATTR_FAIL = "fail_take_vac";

    /**
     * Performs a service level call to get all vacancies
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        try {
            ServiceFactory factory = ServiceFactory.getInstance();
            JobVacancyService service = factory.getJobVacancyService();
            List<JobVacancy> vacancies = service.getVacancies();

            if (vacancies.isEmpty()) {
                request.setAttribute(ATTR_FAIL, "Vacancies not found");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute(ParametersName.VACANCY, vacancies);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
