package service;

import bean.Interview;
import service.exception.ServiceException;

/**
 * Interface {@code InterviewService} is the class, that contains methods
 * to work with interview info
 * @author irinaleibutina
 */
public interface InterviewService {

    void updatePreliminaryInterview(String applicantId, Interview interview) throws ServiceException;
    void updateTechnicalInterview(String applicantId, Interview interview) throws ServiceException;
}
