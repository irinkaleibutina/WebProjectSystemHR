package controller.command.impl;

import bean.Content;
import controller.command.Command;
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

import static controller.util.ParametersName.*;

/**
 * Instance of {@link Command}
 */
public class AddNews implements Command {
    private static final Logger logger = LogManager.getLogger(AddNews.class.getName());

    private static String SHOW_PAGE = "Controller?command=take_news";

    /**
     * Performs a service level call to add news
     *
     * @param request  contains a user request object
     * @param response will be send to the client
     * @throws IOException
     * @throws ServletException
     */
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Content news = new Content();

        news.setNewsTitle(request.getParameter(TITLE));
        news.setNewsDescription(request.getParameter(DESCRIPTION));
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService  = serviceFactory.getNewsService();

        try {
            newsService.addNews(news);
            response.sendRedirect(SHOW_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
