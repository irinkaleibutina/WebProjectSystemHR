package by.epam.systemHR.dao.impl;

import by.epam.systemHR.bean.Content;
import by.epam.systemHR.dao.exception.DAOException;
import by.epam.systemHR.dao.util.sql.NewsSQL;
import by.epam.systemHR.dao.NewsDAO;
import by.epam.systemHR.dao.pool.ConnectionPool;
import by.epam.systemHR.dao.pool.exception.ConnectionPoolException;
import by.epam.systemHR.dao.pool.impl.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.systemHR.dao.util.columnName.NewsColumnName;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that provides methods for mysql db.
 * Implements {@link src.dao.NewsDAO}
 */
public class NewsDAOImpl implements NewsDAO {

    private static final Logger logger = LogManager.getLogger(NewsDAO.class.getName());

    /**
     * Method tries to add news in db
     *
     * @param content contain current news
     * @throws DAOException
     */
    @Override
    public void addNews(Content content) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(NewsSQL.ADD_NEWS)) {
                preparedStatement.setString(1, content.getNewsTitle());
                preparedStatement.setString(2, content.getNewsDescription());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        }
    }

    /**
     * Method tries to delete news from db
     *
     * @param newsId
     * @throws DAOException
     */
    @Override
    public void deleteNews(int newsId) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(NewsSQL.DELETE_NEWS)) {
                preparedStatement.setInt(1, newsId);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        }
    }

    /**
     * Method tries to update news in db
     *
     * @param content contain current news
     * @throws DAOException
     */
    @Override
    public void updateNews(Content content) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(NewsSQL.UPDATE_NEWS)) {
                preparedStatement.setString(1, content.getNewsTitle());
                preparedStatement.setString(2, content.getNewsDescription());
                preparedStatement.setInt(3, content.getNewsId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        }
    }

    /**
     * Method tries to get all news
     *
     * @return list of instances of {@link Content}
     * @throws DAOException
     */
    @Override
    public List<Content> getNews() throws DAOException {

        List<Content> news = new ArrayList<>();
        Content content = null;
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(NewsSQL.NEWS)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        content = new Content();
                        content.setNewsId(resultSet.getInt(NewsColumnName.ID_NEWS));
                        content.setNewsTitle(resultSet.getString(NewsColumnName.TITLE));
                        content.setNewsDescription(resultSet.getString(NewsColumnName.DESCRIPTION));
                        news.add(content);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return news;
    }

    /**
     * Method tries to get concrete news from db
     *
     * @param newsId * @return instance of {@link Content}
     * @throws DAOException
     */
    @Override
    public Content getNews(int newsId) throws DAOException {

        Content content = null;
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(NewsSQL.GET_NEWS)) {
                preparedStatement.setInt(1, newsId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        content = new Content();
                        content.setNewsTitle(resultSet.getString(NewsColumnName.TITLE));
                        content.setNewsDescription(resultSet.getString(NewsColumnName.DESCRIPTION));
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return content;
    }

    /**
     * Method tries to search concrete news in db
     *
     * @param title news title
     * @return instance of {@link Content}
     * @throws DAOException
     */
    @Override
    public Content searchNews(String title) throws DAOException {

        Content content = null;
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(NewsSQL.SEARCH_NEWS)) {
                preparedStatement.setString(1, title);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        while (resultSet.next()) {
                            content = new Content();
                            content.setNewsTitle(resultSet.getString(NewsColumnName.TITLE));
                            content.setNewsDescription(resultSet.getString(NewsColumnName.DESCRIPTION));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return content;
    }
}
