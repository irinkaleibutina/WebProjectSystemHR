package dao.connectionpool;

/**
 * Created by irinaleibutina on 22.02.17.
 */
public class ConnectionPoolFactory {

    private static final ConnectionPoolFactory instance = new ConnectionPoolFactory();

    private ConnectionPool pool = new ConnectionPoolImpl();

    private ConnectionPoolFactory(){}

    public static ConnectionPoolFactory getInstance(){
        return instance;
    }

    public ConnectionPool getPool(){
        return pool;
    }

}
