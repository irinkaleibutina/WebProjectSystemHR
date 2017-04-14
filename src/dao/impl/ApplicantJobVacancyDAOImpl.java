package dao.impl;

import dao.ApplicantJobVacancyDAO;
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

/**
 * Created by irinaleibutina on 09.04.17.
 */
public class ApplicantJobVacancyDAOImpl implements ApplicantJobVacancyDAO {

    private static final Logger logger = LogManager.getLogger(ApplicantJobVacancyDAOImpl.class.getName());

    private static final String SUBMIT_APPLICATION = "INSERT INTO mydb.applicant_job_vacancy(applicant_appl_id, job_vacancy_job_vac_id) " +
            "VALUES (?,?)";
    private static final String DELETE_APPLICATION = "UPDATE mydb.applicant_job_vacancy SET current_status = 'N'" +
            " WHERE applicant_appl_id = ? AND job_vacancy_job_vac_id = ?";
    private final static String SEARCH_APPLICATION = "SELECT * FROM mydb.applicant_job_vacancy " +
            "WHERE applicant_appl_id = ? AND job_vacancy_job_vac_id = ? AND current_status = 'N'";
    private static final String UPDATE_APPLICATION= "UPDATE mydb.applicant_job_vacancy SET current_status = 'A'" +
            " WHERE applicant_appl_id = ? AND job_vacancy_job_vac_id = ?";

    @Override
    public void submitApplication(int applicantId, int jobVacancyId) throws DAOException {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SEARCH_APPLICATION);
            preparedStatement.setInt(1, applicantId);
            preparedStatement.setInt(2, jobVacancyId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                preparedStatement = connection.prepareStatement(UPDATE_APPLICATION);
            } else {
                preparedStatement = connection.prepareStatement(SUBMIT_APPLICATION);
            }
            preparedStatement.setInt(1, applicantId);
            preparedStatement.setInt(2, jobVacancyId);
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
    public void deleteApplication(int applicantId, int jobVacancyId) throws DAOException {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_APPLICATION);
            preparedStatement.setInt(1, applicantId);
            preparedStatement.setInt(2, jobVacancyId);
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

    // TODO function for get all user for one vacancy
}
