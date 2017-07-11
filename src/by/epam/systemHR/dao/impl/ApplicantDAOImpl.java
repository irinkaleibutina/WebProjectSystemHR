package by.epam.systemHR.dao.impl;

import by.epam.systemHR.bean.*;
import by.epam.systemHR.dao.ApplicantDAO;
import by.epam.systemHR.dao.exception.DAOException;
import by.epam.systemHR.dao.util.columnName.SkillsColumnName;
import by.epam.systemHR.dao.pool.ConnectionPool;
import by.epam.systemHR.dao.pool.exception.ConnectionPoolException;
import by.epam.systemHR.dao.pool.impl.ConnectionPoolImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import by.epam.systemHR.dao.util.columnName.ApplicantColumnName;
import by.epam.systemHR.dao.util.sql.ApplicantSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that provides methods for mysql db.
 * Implements {@link src.dao.ApplicantDAO}
 */
public class ApplicantDAOImpl implements ApplicantDAO {

    private static final Logger logger = LogManager.getLogger(ApplicantDAOImpl.class.getName());

    /**
     * Gets applicants from db
     *
     * @return list of instances of {@link Applicant}
     * @throws DAOException
     */
    @Override
    public List<Applicant> getApplicants() throws DAOException {

        List<Applicant> applicants = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSQL.APPLICANT_INFORMATION)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    applicants = applicantData(resultSet);
                }
            }
        } catch (SQLException e) {
            logger.error(e);
            throw new DAOException(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
            throw new DAOException(e);
        }

        return applicants;
    }

    /**
     * Creates user in database
     *
     * @param applicant current applicant
     * @return true, if creation was successful, otherwise - false
     * @throws DAOException
     */
    @Override
    public boolean addApplicant(Applicant applicant) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSQL.ADMIN)) {
                preparedStatement.setString(1, applicant.getLogin());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        logger.error("Such login is already exist");
                        throw new DAOException("Such login is already exist");
                    } else {
                        applicantRegistration(applicant, connection);
                        return true;
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
     * Deletes user from database
     *
     * @param login unique name of each user
     * @throws DAOException
     */
    @Override
    public void deleteApplicant(String login) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSQL.DELETE_APPLICANT)) {
                preparedStatement.setString(1, login);
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
     * Search user in database
     *
     * @param name    name of applicant
     * @param surname surname of applicant
     * @return list of instances of {@link Applicant}
     * @throws DAOException
     */
    @Override
    public List<Applicant> searchApplicant(String name, String surname) throws DAOException {

        List<Applicant> applicants = null;
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSQL.SEARCH_APPLICANT)) {
                preparedStatement.setString(1, name);
                preparedStatement.setString(2, surname);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        applicants = applicantData(resultSet);
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

        return applicants;
    }

    /**
     * Method tries to get user info by login and password
     *
     * @param login    applicant login
     * @param password applicant password
     * @return instance of {@link Applicant}
     * @throws DAOException
     */
    @Override
    public Applicant signIn(String login, String password) throws DAOException {

        Applicant applicant = null;
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSQL.APPLICANT)) {
                preparedStatement.setString(1, login);
                preparedStatement.setString(2, password);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        applicant = buildApplicant(resultSet);
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

        return applicant;
    }

    /**
     * Updates user info in database
     *
     * @param applicant current applicant
     * @throws DAOException
     */
    @Override
    public void updateInfo(Applicant applicant) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSQL.UPDATE_APPLICANT_INFO)) {
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
     * Gets user info from database
     *
     * @param login
     * @return instance of {@link Applicant}
     * @throws DAOException
     */
    @Override
    public Applicant getActualInformation(String login) throws DAOException {

        Applicant applicant = null;
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSQL.ACTUAL_APPLICANT_INFO)) {
                preparedStatement.setString(1, login);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        applicant = buildApplicant(resultSet);
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

        return applicant;
    }

    /**
     * Method tries to add user info in db
     *
     * @param applicant  current applicant
     * @param connection current connection
     * @throws SQLException
     */
    private void applicantRegistration(Applicant applicant, Connection connection) throws SQLException {
        try (PreparedStatement prepareStatement = connection
                .prepareStatement(ApplicantSQL.APPLICANT_REGISTRATION)) {
            prepareStatement.setString(1, applicant.getName());
            prepareStatement.setString(2, applicant.getSurname());
            prepareStatement.setString(3, applicant.getPassword());
            prepareStatement.setString(4, applicant.getLogin());
            prepareStatement.setString(5, applicant.getEmail());
            prepareStatement.setString(6, applicant.getPhoneNumber());
            prepareStatement.executeUpdate();
        }
    }

    /**
     * Method tries to get user info from db
     *
     * @param resultSet
     * @return list of instances of {@link Applicant}
     * @throws SQLException
     */
    private List<Applicant> applicantData(ResultSet resultSet) throws SQLException {

        List<Applicant> applicants = new ArrayList<>();
        Applicant applicant = null;

        while (resultSet.next()) {
            applicant = new Applicant();
            applicant.setId(resultSet.getInt(ApplicantColumnName.APPLICANT_ID));
            applicant.setName(resultSet.getString(ApplicantColumnName.APPLICANT_NAME));
            applicant.setSurname(resultSet.getString(ApplicantColumnName.APPLICANT_SURNAME));
            applicant.setPhoneNumber(resultSet.getString(ApplicantColumnName.PHONE_NUMBER));
            applicant.setCountry(resultSet.getString(ApplicantColumnName.COUNTRY));
            applicant.setCity(resultSet.getString(ApplicantColumnName.CITY));
            applicant.setEmail(resultSet.getString(ApplicantColumnName.EMAIL));

            applicant = getApplicantsVacancies(applicant);
            applicant = getInterviewResult(applicant);
            applicant = getApplicantSkills(applicant);
            applicants.add(applicant);
        }

        return applicants;
    }

    /**
     * Method tries to get user info from db
     *
     * @param resultSet
     * @return instance of {@link Applicant}
     * @throws SQLException
     */
    private Applicant buildApplicant(ResultSet resultSet) throws SQLException {

        Applicant applicant = null;

        while (resultSet.next()) {
            applicant = new Applicant();
            applicant.setId(resultSet.getInt(ApplicantColumnName.APPLICANT_ID));
            applicant.setLogin(resultSet.getString(ApplicantColumnName.LOGIN));
            applicant.setPassword(resultSet.getString(ApplicantColumnName.PASSWORD));
            applicant.setPhoneNumber(resultSet.getString(ApplicantColumnName.PHONE_NUMBER));
            applicant.setName(resultSet.getString(ApplicantColumnName.APPLICANT_NAME));
            applicant.setSurname(resultSet.getString(ApplicantColumnName.APPLICANT_SURNAME));
            applicant.setCountry(resultSet.getString(ApplicantColumnName.COUNTRY));
            applicant.setCity(resultSet.getString(ApplicantColumnName.CITY));
            applicant.setProfessionalSkills(resultSet.getString(ApplicantColumnName.PROFESSIONAL_SKILLS));
            applicant.setEmail(resultSet.getString(ApplicantColumnName.EMAIL));
            applicant.setEducation(resultSet.getString(ApplicantColumnName.EDUCATION));

            applicant = getApplicantsVacancies(applicant);
            applicant = getInterviewResult(applicant);
            applicant = getApplicantSkills(applicant);
        }

        return applicant;
    }

    /**
     * Method tries to get user's job vacancy info from db
     *
     * @param applicant current applicant
     * @return instance of {@link Applicant}
     */
    private Applicant getApplicantsVacancies(Applicant applicant) {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSQL.APPLICANT_JOBVACANCY)) {
                preparedStatement.setInt(1, applicant.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        JobVacancy jobVacancy = new JobVacancy();
                        while (resultSet.next()) {
                            jobVacancy.setId(resultSet.getInt(ApplicantColumnName.JOB_VAC_ID));
                            jobVacancy.setJobTitle(resultSet.getString(ApplicantColumnName.JOB_TITLE));
                        }
                        applicant.setJobVacancy(jobVacancy);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }

        return applicant;
    }

    /**
     * Method tries to get user's interview info from db
     *
     * @param applicant current applicant
     * @return instance of {@link Applicant}
     */
    private Applicant getInterviewResult(Applicant applicant) {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSQL.APPLICANT_INTERVIEW)) {
                preparedStatement.setInt(1, applicant.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        Interview interview = new Interview();
                        while (resultSet.next()) {
                            interview.setId(resultSet.getInt(ApplicantColumnName.INTERVIEW_ID));
                            interview.setPreliminaryInterview(InterviewResult.valueOf(resultSet.getString(ApplicantColumnName.PRELIMINARY_INTERVIEW).toUpperCase()));
                            interview.setTechnicalInterview((InterviewResult.valueOf(resultSet.getString(ApplicantColumnName.TECHNICAL_INTERVIEW).toUpperCase())));
                            interview.setCommonResult(InterviewResult.valueOf(resultSet.getString(ApplicantColumnName.COMMON_INTERVIEW_RESULT).toUpperCase()));
                            interview.setDatePreInt(resultSet.getString(ApplicantColumnName.DATE_PRE_INT));
                            interview.setDateTecInt(resultSet.getString(ApplicantColumnName.DATE_TEC_INT));
                            interview.setTimePreInt(resultSet.getString(ApplicantColumnName.TIME_PRE_INT));
                            interview.setTimeTecInt(resultSet.getString(ApplicantColumnName.TIME_TEC_INT));
                        }
                        applicant.setInterview(interview);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }

        return applicant;
    }

    /**
     * Method tries to get user's skills info from db
     *
     * @param applicant current applicant
     * @return instance of {@link Applicant}
     */
    private Applicant getApplicantSkills(Applicant applicant) {

        List<Skill> skills = new ArrayList<>();
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSQL.APPLICANT_SKILLS)) {
                preparedStatement.setInt(1, applicant.getId());
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        Skill skill = new Skill();
                        while (resultSet.next()) {
                            skill = new Skill();
                            skill.setTitle(resultSet.getString(SkillsColumnName.SKILL_TITLE));
                            skill.setId(resultSet.getInt(SkillsColumnName.ID_PROF_SKILLS));

                            skills.add(skill);
                        }
                        applicant.setSkills(skills);
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
        return applicant;
    }

    public List<String> getOldVacancies() {
        List<String> oldVacancies = new ArrayList<>();

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSQL.OLD_VACANCIES)) {

                // Add code
//                preparedStatement.setInt(1, applicant.getId());
//                try (ResultSet resultSet = preparedStatement.executeQuery()) {
//                    if (resultSet.isBeforeFirst()) {
//                        Skill skill = new Skill();
//                        while (resultSet.next()) {
//                            skill = new Skill();
//                            skill.setTitle(resultSet.getString(SKILL_TITLE));
//                            skill.setId(resultSet.getInt(ID_PROF_SKILLS));
//
//                            skills.add(skill);
//                        }
//                        applicant.setSkills(skills);
//                    }
//                }
            }
        } catch (SQLException e) {
            logger.error(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }

        return oldVacancies;
    }
}
