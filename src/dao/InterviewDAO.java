package dao;

import bean.Interview;
import dao.exception.DAOException;

/**
 * Interface {@code InterviewDAO} is the class, that contains methods
 * to work with interview info in the data base.
 * @author irinaleibutina
 */
public interface InterviewDAO {

    void updatePreliminaryInterview(int applicantId, Interview interview) throws DAOException;
    void updateTechnicalInterview(int applicantId, Interview interview) throws DAOException;
}
