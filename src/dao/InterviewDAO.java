package dao;

import bean.Interview;
import dao.exception.DAOException;

/**
 * Created by irinaleibutina on 4/11/17.
 */
public interface InterviewDAO {
    void updatePreliminaryInterview(int applicantId, Interview interview) throws DAOException;
    void updateTechnicalInterview(int applicantId, Interview interview) throws DAOException;
    Interview getResult(int applicantId) throws DAOException;

    // TODO maybe add func for common result
}
