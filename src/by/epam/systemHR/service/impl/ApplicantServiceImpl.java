package by.epam.systemHR.service.impl;

import by.epam.systemHR.bean.Applicant;
import by.epam.systemHR.dao.ApplicantDAO;
import by.epam.systemHR.dao.exception.DAOException;
import by.epam.systemHR.service.ApplicantService;
import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.service.validation.ServiceValidation;
import by.epam.systemHR.dao.factory.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;


/**
 * Class implements {@link src.dao.ApplicantService}
 */
public class ApplicantServiceImpl implements ApplicantService {
    private static final Logger logger = LogManager.getLogger(ApplicantServiceImpl.class.getName());

    /**
     * Method  performs a dao level call to get applicants
     *
     * @return list of instances of {@link Applicant}
     * @throws ServiceException
     */
    @Override
    public List<Applicant> getApplicants() throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            return applicantDAO.getApplicants();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to add new applicant
     *
     * @param applicant current applicant
     * @return true, if creation was successful, otherwise - false
     * @throws ServiceException
     */
    @Override
    public boolean applicantRegistration(Applicant applicant) throws ServiceException {

        if (!ServiceValidation.validateApplicantRegInfo(applicant)) {
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            if (applicantDAO.addApplicant(applicant)) {
                return true;
            }
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return false;
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to delete applicant
     *
     * @param login unique name of each user
     * @throws ServiceException
     */
    @Override
    public void deleteApplicant(String login) throws ServiceException {

        if (!ServiceValidation.validateParam(login)) {
            logger.error("invalid login");
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            applicantDAO.deleteApplicant(login);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to search applicant
     *
     * @param name    name of applicant
     * @param surname surname of applicant
     * @return list of instances of {@link Applicant}
     * @throws ServiceException
     */
    @Override
    public List<Applicant> searchApplicant(String name, String surname) throws ServiceException {

        if (!ServiceValidation.validateParam(name)) {
            logger.error("invalid name");
            throw new ServiceException("information is not correct");
        }

        if (!ServiceValidation.validateParam(surname)) {
            logger.error("invalid surname");
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            return applicantDAO.searchApplicant(name, surname);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to get user info by login and password
     *
     * @param login    applicant login
     * @param password applicant password
     * @return instance of {@link Applicant}
     * @throws ServiceException
     */
    @Override
    public Applicant signIn(String login, String password) throws ServiceException {

        if (!ServiceValidation.validateParam(login)) {
            logger.error("invalid login");
            throw new ServiceException("information is not correct");
        }
        if (!ServiceValidation.validateParam(password)) {
            logger.error("invalid password");
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            return applicantDAO.signIn(login, password);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to update applicant info
     *
     * @param applicant current applicant
     * @throws ServiceException
     */
    @Override
    public void updateInfo(Applicant applicant) throws ServiceException {

        if (!ServiceValidation.validateApplicantUpdateInfo(applicant)) {
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            applicantDAO.updateInfo(applicant);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to get actual applicant info
     *
     * @param login
     * @return instance of {@link Applicant}
     * @throws ServiceException
     */
    @Override
    public Applicant getActualInformation(String login) throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();

        try {
            return applicantDAO.getActualInformation(login);
        } catch (DAOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
