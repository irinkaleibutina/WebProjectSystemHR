package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.bean.JobVacancy;
import by.epam.systemHR.controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.systemHR.service.JobVacancyService;
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
public class ShowVacancy implements Command {
    private static final Logger logger = LogManager.getLogger(ShowVacancy.class.getName());

    private static final String SHOW_PAGE = "/WEB-INF/jsp/show_vacancy.jsp";
    private static final String ATTR_FAIL = "fail_vacancy";

    /**
     * Performs a service level call get concrete vacancy
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String jobVacancyId = request.getParameter(JOB_VACANCY_ID);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        JobVacancyService jobVacancyService = serviceFactory.getJobVacancyService();

        JobVacancy jobVacancy =  null;

        try {
            jobVacancy = jobVacancyService.getVacancy(jobVacancyId);
            request.setAttribute(ATTR_VACANCY, jobVacancy);

            if(jobVacancy == null){
                request.setAttribute(ATTR_FAIL, "Vacancy not found");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
                requestDispatcher.forward(request, response);
            }
            else {
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
                requestDispatcher.forward(request, response);
            }
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
