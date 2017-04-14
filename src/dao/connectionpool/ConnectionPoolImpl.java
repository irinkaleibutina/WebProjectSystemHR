package dao.connectionpool;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by irinaleibutina on 22.02.17.
 */
public class ConnectionPoolImpl implements ConnectionPool{


    private BlockingQueue<Connection> connectionsQueue;
    private BlockingQueue<Connection> workingConnectionsQueue;
    private String url;
    private String user;
    private String password;
    private String driverLocation;
    private int connection_amount;

    private static final String KEY_URL = "db.url";
    private static final String KEY_USER = "db.user";
    private static final String KEY_PASSWORD = "db.password";
    private static final String KEY_LOCATION_OF_DRIVER = "db.driver";
    private static final String KEY_CONNECTION_AMOUNT="db.amount";
    private static final int DEFAULT_AMOUNT = 15;

    public ConnectionPoolImpl() {
        System.out.println("Con pool Impl");
        DBResourceManager dbResourceManager = DBResourceManager.getInstance();

        this.url = dbResourceManager.getValue(KEY_URL);
        this.user = dbResourceManager.getValue(KEY_USER);
        this.password = dbResourceManager.getValue(KEY_PASSWORD);
        this.driverLocation = dbResourceManager.getValue(KEY_LOCATION_OF_DRIVER);
        try {
            this.connection_amount = Integer.parseInt(dbResourceManager.getValue(KEY_CONNECTION_AMOUNT));
        } catch(NumberFormatException e){
            this.connection_amount = DEFAULT_AMOUNT;
        }
        this.connectionsQueue = new ArrayBlockingQueue<Connection>(connection_amount);
        this.workingConnectionsQueue = new ArrayBlockingQueue<Connection>(connection_amount);
        System.out.println("1");
        try {
            init();
        } catch (ConnectionPoolException e) {
            System.out.println("Error");
        }
    }

    private void init() throws ConnectionPoolException {
        System.out.println("con poll exc");
        try {
            Class.forName(driverLocation);

            for(int i = 0; i < connection_amount; i++) {
                Connection connection = DriverManager.getConnection(url, user, password);
                connectionsQueue.put(connection);
            }
            System.out.println("2");
        } catch (SQLException ex) {
           // logger.error(ex);
            throw new ConnectionPoolException(ex);
        } catch (InterruptedException ex){
            //logger.error(ex);
            throw new ConnectionPoolException(ex);
        } catch (ClassNotFoundException ex){
           // logger.error(ex);
            throw new ConnectionPoolException(ex);
        }
    }

    private void destroy() throws ConnectionPoolException{
        System.out.println("destroy");
        try {
            closeConnectionQueue(connectionsQueue);
        } catch (ConnectionPoolException e) {
           // logger.error(e.getMessage());
        }
        try {
            closeConnectionQueue(workingConnectionsQueue);
        } catch (ConnectionPoolException e) {
            //logger.error(e.getMessage());
        }
    }

    private void closeConnectionQueue(BlockingQueue<Connection> queue) throws ConnectionPoolException {
        System.out.println("close con queue");
        for(Connection connection: queue){
            try {
                if (connection != null) {
                    if (!connection.getAutoCommit()) {
                        connection.commit();
                    }
                    connection.close();
                }
            } catch (SQLException e){
               throw new ConnectionPoolException("Connection wasn't close");
            }
        }
    }

    @Override
    public Connection getConnection() throws ConnectionPoolException {
        System.out.println("con get con");

        Connection connection = null;
        try {
            System.out.println("3");
            connection = connectionsQueue.remove();

            workingConnectionsQueue.put(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException(e);
        }
        return connection;
    }

    @Override
    public void returnConnection(Connection connection) throws ConnectionPoolException {
        System.out.println("return conn");
        try {
            connectionsQueue.put(connection);
            workingConnectionsQueue.remove(connection);
        } catch (InterruptedException e) {
            throw new ConnectionPoolException("Interrupted exception");
        }

    }
}
