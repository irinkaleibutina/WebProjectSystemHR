package dao.impl;

import bean.Interview;
import dao.InterviewDAO;
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
 * Created by irinaleibutina on 4/11/17.
 */
public class InterviewDAOImpl implements InterviewDAO {
    private static final Logger logger = LogManager.getLogger(InterviewDAOImpl.class.getName());

    private static final String UPDATE_PRELIMINARY_INTERVIEW = "update mydb.interview set preliminary_interview =?," +
            "date_pre_int = ?, time_pre_int = ? WHERE interview_id = ?";
    private static final String INTERVIEW = "SELECT *FROM mydb.interview WHERE interview_id = ?";
    private static final String CREATE_PRELIMINARY_INTERVIEW = "INSERT INTO mydb.interview(interview_id, preliminary_interview, date_pre_int, time_pre_int)" +
            " VALUES (?,?,?,?)";
    private static final String UPDATE_TECHNICAL_INTERVIEW = "UPDATE mydb.interview set technical_interview=?, date_tec_int=?, time_tec_int=?" +
            "WHERE interview_id = ?";
    private static final String CREATE_TECHNICAL_INTERVIEW = "INSERT INTO mydb.interview(interview_id, technical_interview, date_tec_int, time_tec_int)" +
            " VALUES (?,?,?,?)";

    @Override
    public void updatePreliminaryInterview(int id, Interview interview) throws DAOException {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INTERVIEW);

            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                preparedStatement = connection.prepareStatement(UPDATE_PRELIMINARY_INTERVIEW);
                preparedStatement.setString(1, interview.getPreliminaryInterview().toString());
                preparedStatement.setString(2, interview.getDatePreInt());
                preparedStatement.setString(3, interview.getTimePreInt());
                preparedStatement.setInt(4, id);
                preparedStatement.executeUpdate();
            } else {
                preparedStatement = connection.prepareStatement(CREATE_PRELIMINARY_INTERVIEW);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, interview.getPreliminaryInterview().toString());
                preparedStatement.setString(3, interview.getDatePreInt());
                preparedStatement.setString(4, interview.getTimePreInt());
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
    public void updateTechnicalInterview(int id, Interview interview) throws DAOException {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(INTERVIEW);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                preparedStatement = connection.prepareStatement(UPDATE_TECHNICAL_INTERVIEW);
                preparedStatement.setString(1, interview.getTechnicalInterview().toString());
                preparedStatement.setString(2, interview.getDateTecInt());
                preparedStatement.setString(3, interview.getTimeTecInt());
                preparedStatement.setInt(4, id);
                preparedStatement.executeUpdate();
            } else {
                preparedStatement = connection.prepareStatement(CREATE_TECHNICAL_INTERVIEW);
                preparedStatement.setInt(1, id);
                preparedStatement.setString(2, interview.getTechnicalInterview().toString());
                preparedStatement.setString(3, interview.getDateTecInt());
                preparedStatement.setString(4, interview.getTimeTecInt());
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
    public Interview getResult(int applicantId) throws DAOException {

        // Here get common result
        return null;
    }
}
