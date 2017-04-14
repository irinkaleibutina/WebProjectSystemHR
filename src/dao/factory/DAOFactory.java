package dao.factory;

import dao.UserDAO;
import dao.impl.SQLUserDAO;

/**
 * Created by irinaleibutina on 20.02.17.
 */
public final class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private DAOFactory(){}

    private UserDAO userDAO = new SQLUserDAO();

    public UserDAO getUserDAO(){
        return userDAO;
    }

    public static DAOFactory getInstance(){
        return instance;
    }
}
