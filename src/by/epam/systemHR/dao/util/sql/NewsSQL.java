package by.epam.systemHR.dao.util.sql;

/**
 *The class contains query strings to work with news info
 */
public final class NewsSQL {

    public static final String ADD_NEWS = "INSERT INTO mydb.news(id_news, title, description) VALUES (LAST_INSERT_ID(),?,?)";
    public static final String DELETE_NEWS = "UPDATE mydb.news SET current_status = 'N' WHERE id_news = ?";
    public static final String UPDATE_NEWS = "UPDATE mydb.news SET title = ?, description = ? WHERE id_news = ?";
    public static final String NEWS = "SELECT * FROM mydb.news WHERE current_status='A'";
    public static final String GET_NEWS = "SELECT * FROM mydb.news WHERE id_news = ?";
    public static final String SEARCH_NEWS = "SELECT * FROM mydb.news WHERE title = ?";
}
