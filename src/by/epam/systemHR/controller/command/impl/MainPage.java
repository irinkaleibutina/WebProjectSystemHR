package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.bean.Content;
import by.epam.systemHR.bean.JobVacancy;
import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.JobVacancyService;
import by.epam.systemHR.service.NewsService;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.service.factory.ServiceFactory;
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

            request.setAttribute(ParametersName.NEWS_LIST, newsList);
            request.setAttribute(ParametersName.VACANCY, jobVacancyList);

            RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
            requestDispatcher.forward(request, response);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
