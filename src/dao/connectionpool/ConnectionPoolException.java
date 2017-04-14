package dao.connectionpool;

/**
 * Created by irinaleibutina on 22.02.17.
 */
public class ConnectionPoolException extends Exception{

    public ConnectionPoolException(){
        super();
    }

    public ConnectionPoolException(String message){
        super(message);
    }

    public ConnectionPoolException(Exception exception){
        super(exception);
    }

    public ConnectionPoolException(String message, Exception exception)
    {
        super(message, exception);
    }
}
