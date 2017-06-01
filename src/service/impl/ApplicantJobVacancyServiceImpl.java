package service.impl;

import dao.ApplicantJobVacancyDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ApplicantJobVacancyService;
import service.exception.ServiceException;

import static service.validation.ServiceValidation.*;

/**
 * Implements {@link src.dao.ApplicantJobVacancyService}
 */
public class ApplicantJobVacancyServiceImpl implements ApplicantJobVacancyService {

    private static final Logger logger = LogManager.getLogger(ApplicantJobVacancyServiceImpl.class.getName());

    /**
     * Method checks the input parameters performs a dao level call
     * to add job vacancy info to a particular user
     *
     * @param applicantId
     * @param jobVacancyId
     * @throws ServiceException
     */
    @Override
    public void submitApplication(String applicantId, String jobVacancyId) throws ServiceException {

        if (!validateData(applicantId, jobVacancyId)) {
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantJobVacancyDAO applicantJobVacancyDAO = daoFactory.getApplicantJobVacancyDAO();
        try {
            int applicantIntId = Integer.parseInt(applicantId);
            int jobVacancyIntId = Integer.parseInt(jobVacancyId);

            if (!validateId(jobVacancyIntId) || !validateId(applicantIntId)) {
                throw new ServiceException();
            }
            applicantJobVacancyDAO.submitApplication(applicantIntId, jobVacancyIntId);
        } catch (IllegalArgumentException | NullPointerException | DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to delete job vacancy info to a particular user
     *
     * @param applicantId
     * @param jobVacancyId
     * @throws ServiceException
     */
    @Override
    public void deleteApplication(String applicantId, String jobVacancyId) throws ServiceException {

        if (!validateData(applicantId, jobVacancyId)) {
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantJobVacancyDAO applicantJobVacancyDAO = daoFactory.getApplicantJobVacancyDAO();
        try {
            int applicantIntId = Integer.parseInt(applicantId);
            int jobVacancyIntId = Integer.parseInt(jobVacancyId);
            applicantJobVacancyDAO.deleteApplication(applicantIntId, jobVacancyIntId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
