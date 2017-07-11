package by.epam.systemHR.service;

import by.epam.systemHR.bean.Content;
import by.epam.systemHR.service.exception.ServiceException;

import java.util.List;

/**
 * Interface {@code NewsService} is the class, that contains methods
 * to work with applicant info in the data base.
 * @author irinaleibutina
 */
public interface NewsService {

    void addNews(Content content) throws ServiceException;
    void deleteNews(String newsId) throws ServiceException;
    void updateNews(Content content) throws ServiceException;
    List<Content> getNews() throws ServiceException;
    Content getNews(int newsId) throws ServiceException;
    Content searchNews(String title) throws ServiceException;
}
