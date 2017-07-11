package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.bean.Content;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.systemHR.service.NewsService;
import by.epam.systemHR.service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Instance of {@link Command}
 */
public class SearchNews implements Command {

    private static final Logger logger = LogManager.getLogger(SearchNews.class.getName());
    private static final String SHOW_PAGE = "/WEB-INF/jsp/show_news.jsp";
    private static final String ATTR_FAIL = "fail_search_news";

    /**
     * Performs a service level call to search news
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter(ParametersName.TITLE);

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService = serviceFactory.getNewsService();
        Content content = null;

        try {
            content = newsService.searchNews(title);

            if(content == null){
                request.setAttribute(ATTR_FAIL, "News wasn't found");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
                requestDispatcher.forward(request, response);
            }
            else {
                request.setAttribute(ParametersName.CURRENT_NEWS, content);
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
