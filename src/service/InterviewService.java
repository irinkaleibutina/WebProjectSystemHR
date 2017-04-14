package service;

import bean.Interview;
import dao.exception.DAOException;
import service.exception.ServiceException;

/**
 * Created by irinaleibutina on 4/11/17.
 */
public interface InterviewService {

    void updatePreliminaryInterview(int applicantId, Interview interview) throws ServiceException;
    void updateTechnicalInterview(int applicantId, Interview interview) throws ServiceException;
    //Interview getResult(int applicantId) throws DAOException;
}
