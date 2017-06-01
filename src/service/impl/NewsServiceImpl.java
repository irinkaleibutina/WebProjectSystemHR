package service.impl;

import bean.Content;
import dao.NewsDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import dao.impl.JobVacancyDAOImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.NewsService;
import service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

import static service.validation.ServiceValidation.validateId;

/**
 * Class implements {@link src.dao.NewsService}
 */
public class NewsServiceImpl implements NewsService {
    private static final Logger logger = LogManager.getLogger(NewsServiceImpl.class.getName());

    /**
     * Method checks the input parameters performs a dao level call
     * to add news
     *
     * @param content contain current news
     * @throws ServiceException
     */
    @Override
    public void addNews(Content content) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getNewsDAO();
        try {
            newsDAO.addNews(content);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to delete news
     *
     * @param newsId if of current news
     * @throws ServiceException
     */
    @Override
    public void deleteNews(String newsId) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getNewsDAO();

        try {
            int newsIntId = Integer.parseInt(newsId);
            if (!validateId(newsIntId)) {
                throw new ServiceException();
            }
            newsDAO.deleteNews(newsIntId);
        } catch (IllegalArgumentException | NullPointerException | DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }

    }

    /**
     * Method checks the input parameters performs a dao level call
     * to update news
     *
     * @param content contain current news
     * @throws ServiceException
     */
    @Override
    public void updateNews(Content content) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getNewsDAO();

        try {
            newsDAO.updateNews(content);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method performs a dao level call
     * to get all news
     *
     * @return list of instances of {@link Content}
     * @throws ServiceException
     */
    @Override
    public List<Content> getNews() throws ServiceException {
        List<Content> news = new ArrayList<>();
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getNewsDAO();

        try {
            news = newsDAO.getNews();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return news;
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to get concrete news
     *
     * @param newsId * @return instance of {@link Content}
     * @throws ServiceException
     */
    @Override
    public Content getNews(int newsId) throws ServiceException {
        Content news = null;
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getNewsDAO();
        if (!validateId(newsId)) {
            throw new ServiceException();
        }
        try {
            news = newsDAO.getNews(newsId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return news;
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to search concrete news
     *
     * @param title news title
     * @return instance of {@link Content}
     * @throws ServiceException
     */
    @Override
    public Content searchNews(String title) throws ServiceException {
        Content news = null;
        DAOFactory daoFactory = DAOFactory.getInstance();
        NewsDAO newsDAO = daoFactory.getNewsDAO();
        try {
            news = newsDAO.searchNews(title);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return news;
    }
}
