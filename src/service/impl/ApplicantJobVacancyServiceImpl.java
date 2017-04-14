package service.impl;

import dao.ApplicantJobVacancyDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ApplicantJobVacancyService;
import service.exception.ServiceException;

/**
 * Created by irinaleibutina on 09.04.17.
 */
public class ApplicantJobVacancyServiceImpl implements ApplicantJobVacancyService {
    private static final Logger logger = LogManager.getLogger(ApplicantJobVacancyServiceImpl.class.getName());

    @Override
    public void submitApplication(String applicantId, String jobVacancyId) throws ServiceException {

        if(!validateData(applicantId, jobVacancyId)){
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantJobVacancyDAO applicantJobVacancyDAO = daoFactory.getApplicantJobVacancyDAO();
        try {
            int applicantIntId = Integer.parseInt(applicantId);
            int jobVacancyIntId = Integer.parseInt(jobVacancyId);
            applicantJobVacancyDAO.submitApplication(applicantIntId, jobVacancyIntId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteApplication(String applicantId, String jobVacancyId) throws ServiceException {

        if(!validateData(applicantId, jobVacancyId)){
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantJobVacancyDAO applicantJobVacancyDAO = daoFactory.getApplicantJobVacancyDAO();
        try {
            int applicantIntId = Integer.parseInt(applicantId);
            int jobVacancyIntId = Integer.parseInt(jobVacancyId);
            applicantJobVacancyDAO.deleteApplication(applicantIntId, jobVacancyIntId);
        } catch (DAOException e) {
            logger.error(e);
           throw new ServiceException(e);
        }
    }

    private boolean validateData(String applicantId, String jobVacancyId){

        if ((applicantId == null) || (applicantId.isEmpty())) {
            logger.error("invalid applicantId");
            return false;
        }

        if ((jobVacancyId == null) || (jobVacancyId.isEmpty())) {
            logger.error("invalid jobVacancyId");
            return false;
        }
        return true;
    }
}
