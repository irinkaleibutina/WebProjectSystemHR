package dao.impl;

import bean.Applicant;
import bean.JobVacancy;
import dao.JobVacancyDAO;
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
import java.util.Locale;

/**
 * Created by irinaleibutina on 27.02.17.
 */
public class JobVacancyDAOImpl implements JobVacancyDAO {

    private static final Logger logger = LogManager.getLogger(JobVacancyDAOImpl.class.getName());

    private static final String VACANCY_INFORMATION = "SELECT * FROM job_vacancy WHERE current_status = 'A'";
    private static final String DELETE_VACANCY = "UPDATE mydb.job_vacancy SET current_status ='N' WHERE job_vac_id = ?";
    private static final String RESTORE_VACANCY = "UPDATE mydb.job_vacancy SET current_status ='A' WHERE job_title = ?";
    private static final String SEARCH_VACANCY = "SELECT * FROM mydb.job_vacancy WHERE job_vac_id = ?";
    private static final String ADD_VACANCY = "INSERT INTO mydb.job_vacancy(job_vac_id, job_title,description)" +
            " VALUES (LAST_INSERT_ID(),?,?)";

    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String CURRENT_STATUS = "current_status";
    private static final String DESCRIPTION = "description";
    private static final String JOB_TITLE= "job_title";
    private static final String JOB_VACANCY_ID = "job_vac_id";

    @Override
    public List<JobVacancy> getVacancies() throws DAOException {
        List<JobVacancy> items = new ArrayList<>();
        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(VACANCY_INFORMATION);
            resultSet = preparedStatement.executeQuery();
            items = buildVacancy(resultSet);
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

    @Override
    public void addVacancy(JobVacancy jobVacancy) throws DAOException {
        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(ADD_VACANCY);
            preparedStatement.setString(1, jobVacancy.getJobTitle());
            preparedStatement.setString(2, jobVacancy.getDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            e.printStackTrace();
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
    public void deleteVacancy(int jobVacancyId) throws DAOException {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_VACANCY);
            preparedStatement.setInt(1, jobVacancyId);
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
    public JobVacancy searchVacancy(int jobVacancyId) throws DAOException {
        JobVacancy jobVacancy;
        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            jobVacancy = new JobVacancy();
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SEARCH_VACANCY);
            preparedStatement.setInt(1, jobVacancyId);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                return getJobVacancy(resultSet);
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
        return jobVacancy;
    }

    @Override
    public void restoreVacancy(String title) throws DAOException {
        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(RESTORE_VACANCY);
            preparedStatement.setString(1, title);
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

    private List<JobVacancy> buildVacancy(ResultSet resultSet) throws SQLException {

        List<JobVacancy> jobVacancies = new ArrayList<>();
        JobVacancy jobVacancy = null;
        while (resultSet.next()) {
            jobVacancy = new JobVacancy();
            jobVacancy.setId(resultSet.getInt(JOB_VACANCY_ID));
            jobVacancy.setJobTitle(resultSet.getString(JOB_TITLE));
            jobVacancy.setDescription(resultSet.getString(DESCRIPTION));
            jobVacancy.setCurrentStatus(resultSet.getString(CURRENT_STATUS));
            jobVacancy.setCountry(resultSet.getString(COUNTRY));
            jobVacancy.setCity(resultSet.getString(CITY));
            jobVacancies.add(jobVacancy);
        }
        return jobVacancies;
    }

    private JobVacancy getJobVacancy(ResultSet resultSet) throws SQLException {

        JobVacancy jobVacancy = null;
        while (resultSet.next()) {
            jobVacancy = new JobVacancy();
            jobVacancy.setId(resultSet.getInt(JOB_VACANCY_ID));
            jobVacancy.setJobTitle(resultSet.getString(JOB_TITLE));
            jobVacancy.setDescription(resultSet.getString(DESCRIPTION));
            jobVacancy.setCurrentStatus(resultSet.getString(CURRENT_STATUS));
            jobVacancy.setCountry(resultSet.getString(COUNTRY));
            jobVacancy.setCity(resultSet.getString(CITY));
        }
        return jobVacancy;
    }
}



