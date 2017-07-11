package by.epam.systemHR.dao;

import by.epam.systemHR.bean.Content;
import by.epam.systemHR.dao.factory.DAOFactory;
import by.epam.systemHR.dao.pool.ConnectionPool;
import by.epam.systemHR.dao.pool.impl.ConnectionPoolImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class NewsDAOTest {

    private ConnectionPool pool;
    private NewsDAO newsDAO;
    private Content content;

    private static final String TITLE = "TestNews";
    private static final String DESCRIPTION = "NewsDescription";
    private static final int NEWS_ID = 2;

    @Before

    public void setUp() throws Exception {
        newsDAO = DAOFactory.getInstance().getNewsDAO();
        pool = ConnectionPoolImpl.getInstance();
        pool.init();

        content = buildContent();
    }

    @After
    public void tearDown() throws Exception {
        newsDAO = null;

        pool.dispose();
        pool = null;

        content = null;
    }

    @Test
    public void addNews() throws Exception {

        newsDAO.addNews(content);
    }

    @Test
    public void deleteNews() throws Exception {

        newsDAO.deleteNews(NEWS_ID);
    }

    @Test
    public void updateNews() throws Exception {

        newsDAO.updateNews(content);
    }

    @Test
    public void getNews() throws Exception {

        newsDAO.getNews();
    }

    @Test
    public void getConcreteNews() throws Exception {

        newsDAO.getNews(NEWS_ID);
    }

    @Test
    public void searchNews() throws Exception {

        newsDAO.searchNews(TITLE);
    }

    private Content buildContent() {
        Content content = new Content();

        content.setNewsTitle(TITLE);
        content.setNewsDescription(DESCRIPTION);
        return content;
    }
}