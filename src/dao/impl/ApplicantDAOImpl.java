package dao.impl;

import bean.Applicant;
import bean.Interview;
import bean.InterviewResult;
import bean.JobVacancy;
import dao.ApplicantDAO;
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
 * Created by irinaleibutina on 29.03.17.
 */
public class ApplicantDAOImpl implements ApplicantDAO {
    private static final Logger logger = LogManager.getLogger(ApplicantDAOImpl.class.getName());

    private static final String APPLICANT_INFORMATION = "SELECT * FROM mydb.applicant";
    private static final String APPLICANT_REGISTRATION = "INSERT INTO mydb.applicant(appl_id,appl_name,appl_surname, password, login, email) " +
            "VALUES (LAST_INSERT_ID(),?,?,SHA2(?,256),?,?)";
    private final static String DELETE_APPLICANT = "update mydb.applicant set current_status = 'N' where login= ?";
    private final static String SEARCH_APPLICANT = "SELECT * FROM mydb.applicant WHERE appl_name = ? AND appl_surname = ? AND current_status = 'A'";
    private static final String APPLICANT = "SELECT * FROM mydb.applicant WHERE login=? AND password=(SHA2(?,256))";
    private static final String ADMIN = "SELECT * FROM mydb.employeeHR WHERE login=?";
    private static final String APPLICANT_JOBVACANCY = "SELECT job_title FROM mydb.job_vacancy " +
            "where job_vac_id = (select job_vacancy_job_vac_id from mydb.applicant_job_vacancy " +
            "where applicant_appl_id = ?)";
    private static final String APPLICANT_INTERVIEW = "SELECT * FROM mydb.interview WHERE interview_id = ?";
    private static final String UPDATE_APPLICANT = "UPDATE mydb.applicant SET " +
            "appl_name = ?, appl_surname = ?,country = ?, city = ?, email =?," +
            "phone_number = ?, education=?, prof_skills = ? WHERE appl_id =?";

    private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String PHONE_NUMBER = "phone_number";
    private static final String EMAIL = "email";
    private static final String APPLICANT_ID = "appl_id";
    private static final String APPLICANT_NAME = "appl_name";
    private static final String APPLICANT_SURNAME = "appl_surname";
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String EDUCATION = "education";
    private static final String PROFESSIONAL_SKILLS = "prof_skills";
    private static final String  JOB_TITLE = "job_title";
    private static final String INTERVIEW_ID = "interview_id";
    private static final String PRELIMINARY_INTERVIEW = "preliminary_interview";
    private static final String TECHNICAL_INTERVIEW = "technical_interview";
    private static final String COMMON_INTERVIEW_RESULT = "common_result";
    private static final String DATE_PRE_INT = "date_pre_int";
    private static final String TIME_PRE_INT = "time_pre_int";
    private static final String DATE_TEC_INT = "date_tec_int";
    private static final String TIME_TEC_INT = "time_tec_int";

    @Override
    public List<Applicant> getApplicants() throws DAOException {
        List<Applicant> applicants = new ArrayList<>();

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(APPLICANT_INFORMATION);
            resultSet = preparedStatement.executeQuery();
            applicants = applicantData(resultSet);
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
        return applicants;
    }

    @Override
    public boolean addApplicant(Applicant applicant) throws DAOException {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(ADMIN);
            preparedStatement.setString(1, applicant.getLogin());
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                logger.error("Such login is already exist");
                throw new DAOException("Such login is already exist");
            } else {
                preparedStatement = connection.prepareStatement(APPLICANT_REGISTRATION);
                preparedStatement.setString(1, applicant.getName());
                preparedStatement.setString(2, applicant.getSurname());
                preparedStatement.setString(3, applicant.getPassword());
                preparedStatement.setString(4, applicant.getLogin());
                preparedStatement.setString(5, applicant.getEmail());
                preparedStatement.executeUpdate();
                return true;
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
            } catch (ConnectionPoolException ex) {
                logger.error(ex);
            } catch (SQLException ex) {
                logger.error(ex);
            }
        }
    }

    @Override
    public void deleteApplicant(String login) throws DAOException {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(DELETE_APPLICANT);
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
    public Applicant searchApplicant(String name, String surname) throws DAOException {

        Applicant currentApplicant;
        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            currentApplicant = new Applicant();
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(SEARCH_APPLICANT);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, surname);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                return buildApplicant(resultSet);
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
        return currentApplicant;
    }

    @Override
    public Applicant signIn(String login, String password) throws DAOException {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Applicant applicant = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(APPLICANT);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.isBeforeFirst()) {
                applicant = buildApplicant(resultSet);
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
            return applicant;
        }
    }

    @Override
    public void updateInfo(Applicant applicant) throws DAOException {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(UPDATE_APPLICANT);

            preparedStatement.setString(1, applicant.getName());
            preparedStatement.setString(2, applicant.getSurname());
            preparedStatement.setString(3, applicant.getCountry());
            preparedStatement.setString(4, applicant.getCity());
            preparedStatement.setString(5, applicant.getEmail());
            preparedStatement.setString(6, applicant.getPhoneNumber());
            preparedStatement.setString(7, applicant.getEducation());
            preparedStatement.setString(8, applicant.getProfessionalSkills());
            preparedStatement.setInt(9, applicant.getId());
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

    private List<Applicant> applicantData(ResultSet resultSet) throws SQLException {

        List<Applicant> applicants = new ArrayList<>();
        Applicant applicant = null;

        while (resultSet.next()) {
            applicant = new Applicant();
            applicant.setId(resultSet.getInt(APPLICANT_ID));
            applicant.setName(resultSet.getString(APPLICANT_NAME));
            applicant.setSurname(resultSet.getString(APPLICANT_SURNAME));
            applicant.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
            applicant.setCountry(resultSet.getString(COUNTRY));
            applicant.setCity(resultSet.getString(CITY));

            applicant = getApplicantsVacancies(applicant);
            applicant = getInterviewResult(applicant);
            applicants.add(applicant);
        }
        return applicants;
    }

    private Applicant buildApplicant(ResultSet resultSet) throws SQLException {
        Applicant applicant = new Applicant();

        while (resultSet.next()) {
            applicant.setId(resultSet.getInt(APPLICANT_ID));
            applicant.setLogin(resultSet.getString(LOGIN));
            applicant.setPassword(resultSet.getString(PASSWORD));
            applicant.setPhoneNumber(resultSet.getString(PHONE_NUMBER));
            applicant.setName(resultSet.getString(APPLICANT_NAME));
            applicant.setSurname(resultSet.getString(APPLICANT_SURNAME));
            applicant.setCountry(resultSet.getString(COUNTRY));
            applicant.setCity(resultSet.getString(CITY));
            applicant.setProfessionalSkills(resultSet.getString(PROFESSIONAL_SKILLS));
            applicant.setEmail(resultSet.getString(EMAIL));
            applicant.setEducation(resultSet.getString(EDUCATION));

            applicant = getApplicantsVacancies(applicant);
            applicant = getInterviewResult(applicant);
        }
        return applicant;
    }

    private Applicant getApplicantsVacancies(Applicant applicant) {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(APPLICANT_JOBVACANCY);
            preparedStatement.setInt(1, applicant.getId());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {

                JobVacancy jobVacancy = new JobVacancy();
                while (resultSet.next()) {
                    jobVacancy.setJobTitle(resultSet.getString(JOB_TITLE));
                }
                applicant.setJobVacancy(jobVacancy);
                return applicant;
            }
        } catch (SQLException e) {
            logger.error(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
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
        return applicant;
    }

    private Applicant getInterviewResult(Applicant applicant) {

        ConnectionPoolFactory factory = ConnectionPoolFactory.getInstance();
        ConnectionPool connectionPool = factory.getPool();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            connection = connectionPool.getConnection();
            preparedStatement = connection.prepareStatement(APPLICANT_INTERVIEW);
            preparedStatement.setInt(1, applicant.getId());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.isBeforeFirst()) {
                Interview interview = new Interview();
                while (resultSet.next()) {

                    interview.setId(resultSet.getInt(INTERVIEW_ID));
                    interview.setPreliminaryInterview(InterviewResult.valueOf(resultSet.getString(PRELIMINARY_INTERVIEW).toUpperCase()));
                    interview.setTechnicalInterview((InterviewResult.valueOf(resultSet.getString(TECHNICAL_INTERVIEW).toUpperCase())));
                    interview.setCommonResult(InterviewResult.valueOf(resultSet.getString(COMMON_INTERVIEW_RESULT).toUpperCase()));
                    interview.setDatePreInt(resultSet.getString(DATE_PRE_INT));
                    interview.setDateTecInt(resultSet.getString(DATE_TEC_INT));
                    interview.setTimePreInt(resultSet.getString(TIME_PRE_INT));
                    interview.setTimeTecInt(resultSet.getString(TIME_TEC_INT));
                }
                applicant.setInterview(interview);
                return applicant;
            }
        } catch (SQLException e) {
            logger.error(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
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
        return applicant;
    }
}
