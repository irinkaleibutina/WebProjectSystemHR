package dao.impl;

import bean.Applicant;
import bean.JobVacancy;
import dao.JobVacancyDAO;
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
import java.util.Locale;

import static dao.util.columnName.JobVacancyColumnName.*;
import static dao.util.sql.JobVacancySQL.*;

/**
 * Class that provides methods for mysql db.
 * Implements {@link src.dao.JobVacancyDAO}
 */
public class JobVacancyDAOImpl implements JobVacancyDAO {

    private static final Logger logger = LogManager.getLogger(JobVacancyDAOImpl.class.getName());

    /**
     * Get all vacancies
     *
     * @return list of instances of {@link JobVacancy}
     * @throws DAOException
     */
    @Override
    public List<JobVacancy> getVacancies() throws DAOException {

        List<JobVacancy> items = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(VACANCY_INFORMATION)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    items = buildVacancy(resultSet);
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
     * Method tries to add vacancy in db
     *
     * @param jobVacancy current job vacancy
     * @throws DAOException
     */
    @Override
    public void addVacancy(JobVacancy jobVacancy) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ADD_VACANCY)) {
                preparedStatement.setString(1, jobVacancy.getJobTitle());
                preparedStatement.setString(2, jobVacancy.getDescription());
                preparedStatement.setString(3, jobVacancy.getCountry());
                preparedStatement.setString(4, jobVacancy.getCity());
                preparedStatement.executeQuery();
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
     * Method tries to delete vacancy from db
     *
     * @param jobVacancyId
     * @throws DAOException
     */
    @Override
    public void deleteVacancy(int jobVacancyId) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_VACANCY)) {
                preparedStatement.setInt(1, jobVacancyId);
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
     * Method tries to get vacancy info from db
     *
     * @param jobVacancyId
     * @return instance of {@link JobVacancy}
     * @throws DAOException
     */
    @Override
    public JobVacancy getVacancy(int jobVacancyId) throws DAOException {

        JobVacancy jobVacancy = null;
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(GET_VACANCY)) {
                preparedStatement.setInt(1, jobVacancyId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        jobVacancy = getJobVacancy(resultSet);
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

        return jobVacancy;
    }

    /**
     * Method tries to restore vacancy
     *
     * @param title title of job vacancy
     * @throws DAOException
     */
    @Override
    public void restoreVacancy(String title) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(RESTORE_VACANCY)) {
                preparedStatement.setString(1, title);
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
     * Method tries to search vacancy in db
     *
     * @param title title of job vacancy
     * @throws DAOException
     */
    @Override
    public JobVacancy searchVacancy(String title) throws DAOException {

        JobVacancy jobVacancy = null;
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(SEARCH_VACANCY)) {
                preparedStatement.setString(1, title);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        jobVacancy = getJobVacancy(resultSet);
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

        return jobVacancy;
    }

    /**
     * Method tries to get vacancy info from db
     *
     * @param resultSet
     * @return list of instances of {@link JobVacancy}
     * @throws SQLException
     */
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

    /**
     * Method tries to get concrete vacancy info from
     *
     * @param resultSet
     * @throws SQLException
     */
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



