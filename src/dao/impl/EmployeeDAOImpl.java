package dao.impl;


import bean.Applicant;
import bean.EmployeeHR;
import dao.EmployeeDAO;
import dao.connectionpool.ConnectionPool;
import dao.connectionpool.ConnectionPoolException;
import dao.connectionpool.ConnectionPoolFactory;
import dao.exception.DAOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by irinaleibutina on 02.04.17.
 */
public class EmployeeDAOImpl implements EmployeeDAO {

    private static final Logger logger = LogManager.getLogger(EmployeeDAOImpl.class.getName());

    private static final String ADMIN_REGISTRATION = "insert into mydb.employeeHR(empl_id,empl_name, empl_surname, password, login,phone_number, email, workId) " +
            "values(LAST_INSERT_ID(),?, ?,SHA2(?,256),?, ?, ?, ?)";
    private static final String DELETE_ADMIN = "UPDATE mydb.employeeHR set current_status = 'N' where login=?";
    private static final String ADMIN_INFORMATION = "SELECT * FROM mydb.employeeHR";
    private static final String APPLICANT = "SELECT * FROM mydb.applicant WHERE login=?";
    private static final String ADMIN = "SELECT * FROM mydb.employeeHR WHERE login=? AND password=SHA2(?,256)";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String EMAIL = "email";
    private static final String ADMIN_ID = "empl_id";
    private static final String ADMIN_NAME = "empl_name";
    private static final String ADMIN_SURNAME = "empl_surname";
    private static final String ADMIN_WORK_ID = "workId";

    @Override
    public void addEmployee(EmployeeHR employeeHR) throws DAOException {
        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(APPLICANT);
            preparedStatement.setString(1, employeeHR.getLogin());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                System.out.println("Such login is already exist");
                throw new DAOException("Such login is already exist");
            } else {
                preparedStatement = connection.prepareStatement(ADMIN_REGISTRATION);
                preparedStatement.setString(1, employeeHR.getName());
                preparedStatement.setString(2, employeeHR.getSurname());
                preparedStatement.setString(3, employeeHR.getPassword());
                preparedStatement.setString(4, employeeHR.getLogin());
                preparedStatement.setString(5, employeeHR.getPhoneNumber());
                preparedStatement.setString(6, employeeHR.getEmail());
                preparedStatement.setString(7, employeeHR.getWorkId());
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
                connectionPool.returnConnection(connection);
            } catch (ConnectionPoolException e) {
                logger.error(e);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public void deleteEmployee(String login) throws DAOException {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_ADMIN);
            preparedStatement.setString(1, login);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            try {
                preparedStatement.close();
                connectionPool.returnConnection(connection);
            } catch (ConnectionPoolException e) {
                logger.error(e);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
    }

    @Override
    public EmployeeHR signIn(String login, String password) throws DAOException {
        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        EmployeeHR employeeHR = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(ADMIN);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                employeeHR = buildEmployee(resultSet);
            }

        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException("Error during authorization");
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
                connectionPool.returnConnection(connection);
            } catch (ConnectionPoolException e) {
                logger.error(e);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return employeeHR;
    }

    @Override
    public List<EmployeeHR> getEmployees() throws DAOException {
        List<EmployeeHR> items = new ArrayList<>();

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(ADMIN_INFORMATION);
            resultSet = preparedStatement.executeQuery();
            items = employeeData(resultSet);
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        } finally {
            try {
                preparedStatement.close();
                resultSet.close();
                connectionPool.returnConnection(connection);
            } catch (ConnectionPoolException e) {
                logger.error(e);
            } catch (SQLException e) {
                logger.error(e);
            }
        }
        return items;
    }

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
}