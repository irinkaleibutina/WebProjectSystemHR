package by.epam.systemHR.dao.impl;

import by.epam.systemHR.bean.Skill;
import by.epam.systemHR.dao.ApplicantSkillsDAO;
import by.epam.systemHR.dao.exception.DAOException;
import by.epam.systemHR.dao.pool.ConnectionPool;
import by.epam.systemHR.dao.pool.exception.ConnectionPoolException;
import by.epam.systemHR.dao.pool.impl.ConnectionPoolImpl;
import by.epam.systemHR.dao.util.sql.ApplicantSkillsSQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import static by.epam.systemHR.dao.util.columnName.SkillsColumnName.*;

/**
 * Class that provides methods for mysql db.
 * Implements {@link src.dao.ApplicantSkillsDAO}
 */
public class ApplicantSkillsDAOImpl implements ApplicantSkillsDAO {

    private static final Logger logger = LogManager.getLogger(ApplicantSkillsDAOImpl.class.getName());

    /**
     * Method tries to add skills info to a particular user in db
     *
     * @param applicantId
     * @param skills      all applicant's skills
     * @throws DAOException
     */
    @Override
    public void addSkills(int applicantId, Map<String, String> skills) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSkillsSQL.SEARCH_APPLICANT_SKILL)) {
                preparedStatement.setInt(1, applicantId);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        setApplicantSkills(applicantId, skills, connection, ApplicantSkillsSQL.UPDATE_APPLICANT_SKILLS);
                    } else {
                        setApplicantSkills(applicantId, skills, connection, ApplicantSkillsSQL.ADD_SKILL);
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
     * Method tries to set skills info to a particular user
     *
     * @param applicantId
     * @param skills      all applicant's skills
     * @throws DAOException
     */
    private void setApplicantSkills(int applicantId, Map<String, String> skills, Connection connection, String sqlName)
            throws SQLException {

        try (PreparedStatement prepareStatement = connection
                .prepareStatement(sqlName)) {
            for (Map.Entry entry : skills.entrySet()) {
                String key = (String) entry.getKey();
                String value = (String) entry.getValue();

                prepareStatement.setString(1, value);
                prepareStatement.setInt(2, applicantId);
                prepareStatement.setInt(3, getSkillId(key));
                prepareStatement.executeUpdate();
            }
        }
    }

    /**
     * Method tries to delete skills info to a particular user
     *
     * @param applicantId
     * @param skillId
     * @throws DAOException
     */
    @Override
    public void deleteSkills(int applicantId, int skillId) throws DAOException {

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSkillsSQL.DELETE_SKILL)) {
                preparedStatement.setInt(1, applicantId);
                preparedStatement.setInt(2, skillId);
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
     * Gets all technologies
     *
     * @return list of instances of {@link Skill}
     * @throws DAOException
     */
    @Override
    public List<Skill> getAllTechnologies() throws DAOException {

        List<Skill> skills = new ArrayList<>();
        Skill skill = null;

        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();
        try (Connection connection = connectionPool.getConnection()) {

            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSkillsSQL.SKILLS)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        skill = new Skill();
                        skill.setTitle(resultSet.getString(SKILL_TITLE));
                        skills.add(skill);
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

        return skills;
    }

    /**
     * Gets skill id from db using title
     *
     * @param title title of skill
     * @return id of skill
     * @throws DAOException
     */
    private int getSkillId(String title) {

        int id = 0;
        ConnectionPool connectionPool = ConnectionPoolImpl.getInstance();

        try (Connection connection = connectionPool.getConnection()) {
            try (PreparedStatement preparedStatement = connection
                    .prepareStatement(ApplicantSkillsSQL.GET_ID)) {
                preparedStatement.setString(1, title);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.isBeforeFirst()) {
                        while (resultSet.next()) {
                            id = resultSet.getInt(ID_PROF_SKILLS);
                        }
                    }
                }
            }
        } catch (SQLException e) {
            logger.error(e);
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }

        return id;
    }
}
