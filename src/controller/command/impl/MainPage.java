package controller.command.impl;

import bean.Content;
import bean.JobVacancy;
import controller.command.Command;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.JobVacancyService;
import service.NewsService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class MainPage implements Command {
    private static final Logger logger = LogManager.getLogger(MainPage.class.getName());

    private static final String SHOW_PAGE = "/WEB-INF/jsp/home.jsp";

    /**
     * Performs a service level call to get info
     * which will full main page
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<JobVacancy> jobVacancyList ;
        List<Content> newsList;
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        JobVacancyService jobVacancyService = serviceFactory.getJobVacancyService();
        NewsService newsService = serviceFactory.getNewsService();

        try {
            jobVacancyList = jobVacancyService.getVacancies();
            newsList = newsService.getNews();

            request.setAttribute(NEWS_LIST, newsList);
            request.setAttribute(VACANCY, jobVacancyList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
