package dao.impl;

import bean.EmployeeHR;
import dao.EmployeeDAO;

import dao.exception.DAOException;
import dao.pool.ConnectionPool;
import dao.pool.exception.ConnectionPoolException;
import dao.pool.impl.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static dao.util.sql.EmployeeSQL.*;
import static dao.util.columnName.EmployeeColumnName.*;

/**
 * Class that provides methods for mysql db.
 * Implements {@link src.dao.EmployeeDAO}
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private static final Logger logger = LogManager.getLogger(EmployeeDAOImpl.class.getName());

    /**
     * Creates user in database
     *
     * @param employeeHR current employee
     * @throws DAOException
     */
    @Override
    public void addEmployee(EmployeeHR employeeHR) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(APPLICANT)) {
                preparedStatement.setString(1, employeeHR.getLogin());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        throw new DAOException("Such login is already exist");
                    } else {
                        adminRegistration(employeeHR, connection);
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
    }

    /**
     * Method tries to delete employee from db
     *
     * @param login unique name of each user
     * @throws DAOException
     */
    @Override
    public void deleteEmployee(String login) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_ADMIN)) {
                preparedStatement.setString(1, login);
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error("", e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        }
    }

    /**
     * Method tries to get user info by login and password
     *
     * @param login    employee login
     * @param password employee password
     * @return instance of {@link EmployeeHR}
     * @throws DAOException
     */
    @Override
    public EmployeeHR signIn(String login, String password) throws DAOException {

        EmployeeHR employeeHR = null;
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ADMIN)) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        employeeHR = buildEmployee(resultSet);
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

        return employeeHR;
    }

    /**
     * Method tries to get all employee from db
     *
     * @return list of instances of {@link EmployeeHR}
     * @throws DAOException
     */
    @Override
    public List<EmployeeHR> getEmployees() throws DAOException {

        List<EmployeeHR> items = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ADMIN_INFORMATION)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    items = employeeData(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return items;
    }

    /**
     * Method tries to update employee info in db
     *
     * @param employeeHR current employee
     * @throws DAOException
     */
    @Override
    public void updateEmployeeInfo(EmployeeHR employeeHR) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(UPDATE_ADMIN_INFO)) {
                preparedStatement.setString(1, employeeHR.getName());
                preparedStatement.setString(2, employeeHR.getSurname());
                preparedStatement.setString(3, employeeHR.getEmail());
                preparedStatement.setString(4, employeeHR.getPhoneNumber());
                preparedStatement.setString(5, employeeHR.getWorkId());
                preparedStatement.setInt(6, employeeHR.getId());
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
     * Method tries to get employee info from db
     *
     * @param resultSet
     * @return list of instances of {@link EmployeeHR}
     * @throws SQLException
     */
    private List<EmployeeHR> employeeData(ResultSet resultSet) throws SQLException {

        List<EmployeeHR> employees = new ArrayList<>();
        EmployeeHR employeeHR = null;

        while (resultSet.next()) {
            employeeHR = new EmployeeHR();
            employeeHR.setId(resultSet.getInt(ADMIN_ID));
            employeeHR.setName(resultSet.getString(ADMIN_NAME));
            employeeHR.setSurname(resultSet.getString(ADMIN_SURNAME));
            employeeHR.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
            employeeHR.setEmail(resultSet.getString(EMAIL));
            employeeHR.setWorkId(resultSet.getString(ADMIN_WORK_ID));
            employees.add(employeeHR);
        }

        return employees;
    }

    /**
     * Method tries to get employee info from db
     *
     * @param resultSet
     * @return instance of {@link EmployeeHR}
     * @throws SQLException
     */
    private EmployeeHR buildEmployee(ResultSet resultSet) throws SQLException {

        EmployeeHR employee = new EmployeeHR();

        while (resultSet.next()) {
            employee.setId(resultSet.getInt(ADMIN_ID));
            employee.setLogin(resultSet.getString(LOGIN));
            employee.setPassword(resultSet.getString(PASSWORD));
            employee.setName(resultSet.getString(ADMIN_NAME));
            employee.setSurname(resultSet.getString(ADMIN_SURNAME));
            employee.setWorkId(resultSet.getString(ADMIN_WORK_ID));
            employee.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
            employee.setEmail(resultSet.getString(EMAIL));
        }

        return employee;
    }

    /**
     * Method tries to add user info in db
     *
     * @param employeeHR current employee
     * @param connection current connection
     * @throws SQLException
     */
    private void adminRegistration(EmployeeHR employeeHR, Connection connection) throws SQLException {

        try (PreparedStatement preparedStatement = connection
                .prepareStatement(ADMIN_REGISTRATION)) {
            preparedStatement.setString(1, employeeHR.getName());
            preparedStatement.setString(2, employeeHR.getSurname());
            preparedStatement.setString(3, employeeHR.getPassword());
            preparedStatement.setString(4, employeeHR.getLogin());
            preparedStatement.setString(5, employeeHR.getPhoneNumber());
            preparedStatement.setString(6, employeeHR.getEmail());
            preparedStatement.setString(7, employeeHR.getWorkId());
            preparedStatement.executeUpdate();
        }
    }
}