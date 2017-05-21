package dao.impl;

import bean.Interview;
import dao.InterviewDAO;
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

import static dao.util.sql.InterviewSQL.*;

/**
 * Class that provides methods for mysql db.
 * Implements {@link src.dao.InterviewDAO}
 */
public class InterviewDAOImpl implements InterviewDAO {

    private static final Logger logger = LogManager.getLogger(InterviewDAOImpl.class.getName());

    /**
     * Method tries to update preliminary interview info in db
     *
     * @param id        applicant id
     * @param interview current interview
     * @throws DAOException
     */
    @Override
    public void updatePreliminaryInterview(int id, Interview interview) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement prepStatement = connection
                    .prepareStatement(INTERVIEW)) {
                prepStatement.setInt(1, id);
                try (ResultSet resultSet = prepStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        setPreliminaryInterview(id, interview, connection);
                    } else {
                        createPreliminaryInterview(id, interview, connection);
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
     * Method tries to update technical interview info in db
     *
     * @param id        applicant id
     * @param interview current interview
     * @throws DAOException
     */
    @Override
    public void updateTechnicalInterview(int id, Interview interview) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement prepStatement = connection
                    .prepareStatement(INTERVIEW)) {
                prepStatement.setInt(1, id);
                try (ResultSet resultSet = prepStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        setTechnicalInterview(id, interview, connection);
                    } else {
                        createTechnicalInterview(id, interview, connection);
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
     * Method tries to update preliminary interview info in db
     *
     * @param id        applicant id
     * @param interview current interview
     * @throws SQLException
     */
    private void setPreliminaryInterview(int id, Interview interview, Connection connection) throws SQLException {

        try (PreparedStatement prepareStatement = connection
                .prepareStatement(UPDATE_PRELIMINARY_INTERVIEW)) {
            prepareStatement.setString(1, interview.getPreliminaryInterview().toString());
            prepareStatement.setString(2, interview.getDatePreInt());
            prepareStatement.setString(3, interview.getTimePreInt());
            prepareStatement.setInt(4, id);
            prepareStatement.executeUpdate();
        }
    }

    /**
     * Method tries to create preliminary interview
     *
     * @param id        applicant id
     * @param interview current interview
     * @throws SQLException
     */
    private void createPreliminaryInterview(int id, Interview interview, Connection connection) throws SQLException {

        try (PreparedStatement prepareStatement = connection
                .prepareStatement(CREATE_PRELIMINARY_INTERVIEW)) {
            prepareStatement.setInt(1, id);
            prepareStatement.setString(2, interview.getPreliminaryInterview().toString());
            prepareStatement.setString(3, interview.getDatePreInt());
            prepareStatement.setString(4, interview.getTimePreInt());
            prepareStatement.executeUpdate();
        }
    }

    /**
     * Method tries to create technical interview
     *
     * @param id        applicant id
     * @param interview current interview
     * @throws SQLException
     */
    private void createTechnicalInterview(int id, Interview interview, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection
                .prepareStatement(CREATE_TECHNICAL_INTERVIEW)) {
            prepareStatement.setInt(1, id);
            prepareStatement.setString(2, interview.getTechnicalInterview().toString());
            prepareStatement.setString(3, interview.getDateTecInt());
            prepareStatement.setString(4, interview.getTimeTecInt());
            prepareStatement.executeUpdate();
        }
    }

    /**
     * Method tries to update technical interview info in db
     *
     * @param id        applicant id
     * @param interview current interview
     * @throws SQLException
     */
    private void setTechnicalInterview(int id, Interview interview, Connection connection) throws SQLException {

        try (PreparedStatement prepareStatement = connection
                .prepareStatement(UPDATE_TECHNICAL_INTERVIEW)) {
            prepareStatement.setInt(1, id);
            prepareStatement.setString(1, interview.getTechnicalInterview().toString());
            prepareStatement.setString(2, interview.getDateTecInt());
            prepareStatement.setString(3, interview.getTimeTecInt());
            prepareStatement.setInt(4, id);
            prepareStatement.executeUpdate();
        }
    }
}
