package dao;

import bean.Content;
import dao.exception.DAOException;
import java.util.List;

/**
 * Interface {@code NewsDAO} is the class, that contains methods
 * to work with news info in the data base.
 * @author irinaleibutina
 */
public interface NewsDAO {

    void addNews(Content content) throws DAOException;
    void deleteNews(int newsId) throws DAOException;
    void updateNews(Content content) throws DAOException;
    List<Content> getNews() throws DAOException;
    Content getNews(int newsId) throws DAOException;
    Content searchNews(String title) throws DAOException;
}
