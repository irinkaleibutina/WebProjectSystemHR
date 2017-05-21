package dao.impl;

import dao.ApplicantJobVacancyDAO;
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

import static dao.util.sql.ApplicantJobVacancySQL.*;

/**
 * Class that provides methods for mysql db.
 * Implements {@link src.dao.ApplicantJobVacancyDAO}
 */
public class ApplicantJobVacancyDAOImpl implements ApplicantJobVacancyDAO {

    private static final Logger logger = LogManager.getLogger(ApplicantJobVacancyDAOImpl.class.getName());

    /**
     * Method tries to add job vacancy info to a particular user
     *
     * @param applicantId
     * @param jobVacancyId
     * @throws DAOException
     */
    @Override
    public void submitApplication(int applicantId, int jobVacancyId) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(SEARCH_APPLICATION)) {
                preparedStatement.setInt(1, applicantId);
                preparedStatement.setInt(2, jobVacancyId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        updateApplication(applicantId, jobVacancyId, connection);
                    } else {
                        if (checkApplication(applicantId, connection)) {
                            throw new DAOException("Applicant already has actual application");
                        } else {
                            submitApplication(applicantId, jobVacancyId, connection);
                        }
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
     * Method tries to delete job vacancy info to a particular user
     *
     * @param applicantId
     * @param jobVacancyId
     * @throws DAOException
     */
    @Override
    public void deleteApplication(int applicantId, int jobVacancyId) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_APPLICATION)) {
                preparedStatement.setInt(1, applicantId);
                preparedStatement.setInt(2, jobVacancyId);
                preparedStatement.executeUpdate();
            }
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(DELETE_INTERVIEW)) {
                preparedStatement.setInt(1, applicantId);
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
     * Method tries to update job vacancy info to a particular user
     *
     * @param applicantId
     * @param jobVacancyId
     * @throws SQLException
     */
    private void updateApplication(int applicantId, int jobVacancyId, Connection connection) throws SQLException {

        try (PreparedStatement prepareStatement = connection
                .prepareStatement(UPDATE_APPLICATION)) {
            prepareStatement.setInt(1, applicantId);
            prepareStatement.setInt(2, jobVacancyId);
            prepareStatement.executeUpdate();
        }
    }

    /**
     * Method that checks user job vacancies in database
     *
     * @param applicantId
     * @param connection
     * @return true, if user has application, otherwise - false;
     * @throws SQLException
     */
    private boolean checkApplication(int applicantId, Connection connection) throws SQLException {

        try (PreparedStatement prepareStatement = connection
                .prepareStatement(CHECK_APPLICATION)) {
            prepareStatement.setInt(1, applicantId);
            try (ResultSet resultSet = prepareStatement.executeQuery()) {
                if (resultSet.isBeforeFirst()) {
                    return true;
                }
            }
        }

        return false;
    }

    /**
     * Method tries to add job vacancy info to a particular user
     *
     * @param applicantId
     * @param jobVacancyId
     * @throws SQLException
     */
    private void submitApplication(int applicantId, int jobVacancyId, Connection connection) throws SQLException {

        try (PreparedStatement prepareStatement = connection
                .prepareStatement(SUBMIT_APPLICATION)) {
            prepareStatement.setInt(1, applicantId);
            prepareStatement.setInt(2, jobVacancyId);
            prepareStatement.executeUpdate();
        }
    }
}

