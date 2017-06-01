package service.impl;

import bean.Interview;
import dao.InterviewDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.InterviewService;
import service.exception.ServiceException;

import static service.validation.ServiceValidation.*;

/**
 * Class implements {@link src.dao.InterviewService}
 */
public class InterviewServiceImpl implements InterviewService {
    private static final Logger logger = LogManager.getLogger(InterviewServiceImpl.class.getName());

    /**
     * Method checks the input parameters performs a dao level call
     * to update preliminary interview info
     *
     * @param applicantId applicant id
     * @param interview   current interview
     * @throws ServiceException
     */
    @Override
    public void updatePreliminaryInterview(String applicantId, Interview interview) throws ServiceException {

        if (!validatePreInterview(interview)) {
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        InterviewDAO interviewDAO = daoFactory.getInterviewDAO();
        try {
            int applicantIntId = Integer.parseInt(applicantId);

            if (!validateId(applicantIntId)) {
                throw new ServiceException();
            }

            interviewDAO.updatePreliminaryInterview(applicantIntId, interview);
        } catch (IllegalArgumentException | NullPointerException | DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to update technical interview info
     *
     * @param applicantId applicant id
     * @param interview   current interview
     * @throws ServiceException
     */
    @Override
    public void updateTechnicalInterview(String applicantId, Interview interview) throws ServiceException {

        if (!validateTecInterview(interview)) {
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        InterviewDAO interviewDAO = daoFactory.getInterviewDAO();
        try {
            int applicantIntId = Integer.parseInt(applicantId);

            if (!validateId(applicantIntId)) {
                throw new ServiceException();
            }

            interviewDAO.updateTechnicalInterview(applicantIntId, interview);
        } catch (IllegalArgumentException | NullPointerException | DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }
}
