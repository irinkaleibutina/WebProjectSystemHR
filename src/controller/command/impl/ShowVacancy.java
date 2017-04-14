package controller.command.impl;

import bean.JobVacancy;
import controller.command.Command;
import controller.command.exception.CommandException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.JobVacancyService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by irinaleibutina on 4/11/17.
 */
public class ShowVacancy implements Command {
    private static final Logger logger = LogManager.getLogger(ShowVacancy.class.getName());

    private static final String JOB_VACANCY_ID = "item";
    private static final String JOB_VACANCY = "vac";
    private static final String SHOW_PAGE = "/WEB-INF/jsp/show_vacancy.jsp";
    private static final String ATTR_ERROR = "error";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String jobVacancyId = request.getParameter(JOB_VACANCY_ID);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        JobVacancyService jobVacancyService = serviceFactory.getJobVacancyService();

        JobVacancy jobVacancy = new JobVacancy();

        try {
            jobVacancy = jobVacancyService.searchVacancy(jobVacancyId);
            request.setAttribute(JOB_VACANCY, jobVacancy);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
        requestDispatcher.forward(request, response);
    }
}
