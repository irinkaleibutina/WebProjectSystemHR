package controller.command.impl;

import bean.Content;
import controller.command.Command;
import controller.command.CommandName;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.NewsService;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static controller.util.ParametersName.ATTR_ERROR;
import static controller.util.ParametersName.ERROR_PAGE;
import static controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class GetNews implements Command {
    private static final Logger logger = LogManager.getLogger(GetNews.class.getName());

    private static final String SHOW_PAGE = "/WEB-INF/jsp/show_news.jsp";
    private static final String ATTR_FAIL = "fail_get_news";
    private int newsId;

    /**
     * Performs a service level call to get news
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService = serviceFactory.getNewsService();

        Content content = null;
        try {
            newsId = Integer.parseInt(request.getParameter(NEWS_ID));
            content = newsService.getNews(newsId);

            if (content == null) {
                request.setAttribute(ATTR_FAIL, "Describe of news is not available");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
                requestDispatcher.forward(request, response);
            } else {
                content.setNewsId(newsId);
                request.setAttribute(CURRENT_NEWS, content);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher(SHOW_PAGE);
                requestDispatcher.forward(request, response);
            }
        } catch (IllegalArgumentException | NullPointerException | ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
