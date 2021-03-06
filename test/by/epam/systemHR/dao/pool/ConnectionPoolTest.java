package by.epam.systemHR.dao.pool;

import by.epam.systemHR.dao.pool.exception.ConnectionPoolException;
import by.epam.systemHR.dao.pool.impl.ConnectionPoolImpl;
import by.epam.systemHR.dao.util.sql.NewsSQL;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConnectionPoolTest {

    private ConnectionPool pool;
    private static final int SIZE = 10000;

    @Before
    public void setUp() throws Exception {

        pool = ConnectionPoolImpl.getInstance();
        try {
            pool.init();
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
        pool.dispose();
        pool = null;
    }

    @Test
    public void testConnectionPool() throws InterruptedException {

        Thread[] arr = new Thread[SIZE];

        for (int i = 0; i < SIZE; i++) {
            arr[i] = new Thread() {
                @Override
                public void run() {
                    try (Connection connection = pool.getConnection();
                         PreparedStatement statement = connection
                                 .prepareStatement(NewsSQL.NEWS);

                         ResultSet result = statement.executeQuery()) {

                    } catch (SQLException e) {
                        e.printStackTrace();
                    } catch (ConnectionPoolException e) {
                        e.printStackTrace();
                    }
                }
            };
            arr[i].start();
        }

        for (int i = 0; i < SIZE; i++) {
            arr[i].join();
        }
    }
}