package service.impl;

import bean.Interview;
import dao.InterviewDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.InterviewService;
import service.exception.ServiceException;

/**
 * Created by irinaleibutina on 4/12/17.
 */
public class InterviewServiceImpl implements InterviewService {
    private static final Logger logger = LogManager.getLogger(InterviewServiceImpl.class.getName());

    @Override
    public void updatePreliminaryInterview(int applicantId, Interview interview) throws ServiceException {

        if (!validatePreInterview(interview)) {
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory= DAOFactory.getInstance();
        InterviewDAO interviewDAO = daoFactory.getInterviewDAO();
        try {
            interviewDAO.updatePreliminaryInterview(applicantId, interview);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateTechnicalInterview(int applicantId, Interview interview) throws ServiceException {

        if (!validateTecInterview(interview)) {
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory= DAOFactory.getInstance();
        InterviewDAO interviewDAO = daoFactory.getInterviewDAO();
        try {
            interviewDAO.updateTechnicalInterview(applicantId, interview);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    // Validation
    private boolean validatePreInterview(Interview interview) {

        String date = interview.getDatePreInt();
        if (!validateParam(date)) {
            logger.error("invalid date");
            return false;
        }

        String time = interview.getTimePreInt();
        if (!validateParam(date)) {
            logger.error("invalid time");
            return false;
        }

        String status = interview.getPreliminaryInterview().toString();
        if (!validateParam(status)) {
            logger.error("invalid status");
            return false;
        }

        String dateRegEx = "^(0[1-9]|[12][0-9]|3[01])[- \\.](0[1-9]|1[012])[- \\.](19|20)\\d\\d$"; // TODO check work
        String timeRegEx = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        String statusRegEx = "[S|F|E]"; // S, E, F

        if (!date.matches(dateRegEx)) {
            logger.error("date is not correct");
            return false;
        }
        if (!time.matches(timeRegEx)) {
            logger.error("time is not correct");
            return false;
        }
        if (!status.matches(statusRegEx)) {
            logger.error("status is not correct");
            return false;
        }
        return true;
    }

    private boolean validateTecInterview(Interview interview) {

        String date = interview.getDateTecInt();
        if (!validateParam(date)) {
            logger.error("invalid date");
            return false;
        }

        String time = interview.getTimeTecInt();
        if (!validateParam(date)) {
            logger.error("invalid time");
            return false;
        }

        String status = interview.getTechnicalInterview().toString();
        if (!validateParam(status)) {
            logger.error("invalid status");
            return false;
        }

        String dateRegEx = "^(0[1-9]|[12][0-9]|3[01])[- \\.](0[1-9]|1[012])[- \\.](19|20)\\d\\d$";
        String timeRegEx = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        String statusRegEx = "[S|F|E]"; // S, E, F

        if (!date.matches(dateRegEx)) {
            logger.error("date is not correct");
            return false;
        }
        if (!time.matches(timeRegEx)) {
            logger.error("time is not correct");
            return false;
        }

        if (!status.matches(statusRegEx)) {
            logger.error("status is not correct");
            return false;
        }
        return true;
    }

    private boolean validateParam(String param) {
        if ((param == null) || (param.isEmpty())) {
            return false;
        }
        return true;
    }
}
