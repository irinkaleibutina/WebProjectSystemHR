package by.epam.systemHR.controller.command.impl;

import by.epam.systemHR.bean.Content;
import by.epam.systemHR.controller.command.Command;
import by.epam.systemHR.controller.util.ParametersName;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.systemHR.service.NewsService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

        news.setNewsTitle(request.getParameter(ParametersName.TITLE));
        news.setNewsDescription(request.getParameter(ParametersName.DESCRIPTION));
        ServiceFactory serviceFactory = ServiceFactory.getInstance();
        NewsService newsService  = serviceFactory.getNewsService();

        try {
            newsService.addNews(news);
            response.sendRedirect(SHOW_PAGE);
        } catch (ServiceException e) {
            logger.error(e);
            request.setAttribute(ParametersName.ATTR_ERROR, e);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(ParametersName.ERROR_PAGE);
            requestDispatcher.forward(request, response);
        }
    }
}
