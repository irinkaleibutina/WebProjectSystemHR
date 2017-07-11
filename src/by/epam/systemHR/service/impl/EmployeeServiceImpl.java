package by.epam.systemHR.service.impl;

import by.epam.systemHR.bean.EmployeeHR;
import by.epam.systemHR.dao.EmployeeDAO;
import by.epam.systemHR.dao.exception.DAOException;
import by.epam.systemHR.dao.factory.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.systemHR.service.EmployeeService;
import by.epam.systemHR.service.exception.ServiceException;

import java.util.List;

import static by.epam.systemHR.service.validation.ServiceValidation.*;

/**
 * Class implements {@link src.dao.EmployeeService}
 */
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class.getName());

    /**
     * Method checks the input parameters performs a dao level call
     * to add employee
     *
     * @param employeeHR current employee
     * @throws ServiceException
     */
    @Override
    public void employeeRegistration(EmployeeHR employeeHR) throws ServiceException {

        if (!validateEmployeeInfo(employeeHR)) {
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        try {
            employeeDAO.addEmployee(employeeHR);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to delete employee
     *
     * @param login unique name of each user
     * @throws ServiceException
     */
    @Override
    public void deleteEmployee(String login) throws ServiceException {

        if (!validateParam(login)) {
            logger.error("invalid login");
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        try {
            employeeDAO.deleteEmployee(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to get user info by login and password
     *
     * @param login    employee login
     * @param password employee password
     * @return instance of {@link EmployeeHR}
     * @throws ServiceException
     */
    @Override
    public EmployeeHR signIn(String login, String password) throws ServiceException {

        if (!validateParam(login)) {
            logger.error("invalid login");
            throw new ServiceException("information is not correct");
        }
        if (!validateParam(password)) {
            logger.error("invalid password");
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        try {
            return employeeDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method performs a dao level call
     * to get all employee from db
     *
     * @return list of instances of {@link EmployeeHR}
     * @throws ServiceException
     */
    @Override
    public List<EmployeeHR> getEmployees() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        try {
            return employeeDAO.getEmployees();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to update employee info
     *
     * @param employeeHR current employee
     * @throws ServiceException
     */
    @Override
    public void updateEmployeeInfo(EmployeeHR employeeHR) throws ServiceException {

        if (!validateEmployeeInfo(employeeHR)) {
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        try {
            employeeDAO.updateEmployeeInfo(employeeHR);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
