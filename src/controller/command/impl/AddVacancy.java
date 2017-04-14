package controller.command.impl;

import bean.JobVacancy;
import controller.command.Command;
import controller.command.exception.CommandException;
import dao.impl.ApplicantDAOImpl;
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

/**
 * Created by irinaleibutina on 05.04.17.
 */
public class AddVacancy implements Command {
    private static final Logger logger = LogManager.getLogger(AddVacancy.class.getName());

    private static final String ATTR_TITLE = "title";
    private static final String ATTR_DESCRIPTION = "description";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String ATTR_ERROR = "error";
    private static final String INDEX_PAGE = "/";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        JobVacancyService jobVacancyService = serviceFactory.getJobVacancyService();

        JobVacancy jobVacancy = new JobVacancy();
        jobVacancy.setJobTitle(request.getParameter(ATTR_TITLE));
        jobVacancy.setDescription(request.getParameter(ATTR_DESCRIPTION));

        try {
            jobVacancyService.addVacancy(jobVacancy);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
        response.sendRedirect(request.getContextPath() + INDEX_PAGE);
    }
}
