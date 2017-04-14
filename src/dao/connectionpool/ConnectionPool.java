package dao.connectionpool;

import java.sql.Connection;

/**
 * Created by irinaleibutina on 22.02.17.
 */
public interface ConnectionPool {
    Connection getConnection() throws ConnectionPoolException;
    void returnConnection(Connection connection) throws ConnectionPoolException;
}
