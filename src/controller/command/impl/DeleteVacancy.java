package controller.command.impl;

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

/**
 * Created by irinaleibutina on 01.04.17.
 */
public class DeleteVacancy implements Command {
    private static final Logger logger = LogManager.getLogger(DeleteVacancy.class.getName());

    private static final String JOB_VACANCY_ID = "item";
    private static final String ATTR_ERROR = "error";
    private static final String INDEX_PAGE = "/";
    private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response)
            throws  IOException, ServletException {
        String jobVacancyId = request.getParameter(JOB_VACANCY_ID);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        JobVacancyService jobVacancyService = serviceFactory.getJobVacancyService();

        try {
            jobVacancyService.deleteVacancy(jobVacancyId);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
        response.sendRedirect(request.getContextPath() + INDEX_PAGE);
    }
}
