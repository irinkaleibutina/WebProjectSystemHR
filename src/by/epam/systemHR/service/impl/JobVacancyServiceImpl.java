package by.epam.systemHR.service.impl;

import by.epam.systemHR.bean.JobVacancy;
import by.epam.systemHR.dao.JobVacancyDAO;
import by.epam.systemHR.dao.exception.DAOException;
import by.epam.systemHR.dao.factory.DAOFactory;
import by.epam.systemHR.service.JobVacancyService;
import by.epam.systemHR.service.exception.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import static by.epam.systemHR.service.validation.ServiceValidation.*;

/**
 * Class implements {@link src.dao.JobVacancyService}
 */
public class JobVacancyServiceImpl implements JobVacancyService {
    private static final Logger logger = LogManager.getLogger(JobVacancyServiceImpl.class.getName());

    /**
     * Method performs a dao level call to get all vacancies
     *
     * @return list of instances of {@link JobVacancy}
     * @throws ServiceException
     */
    @Override
    public List<JobVacancy> getVacancies() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        JobVacancyDAO jobVacancyDAO = factory.getJobVacancyDAO();
        List<JobVacancy> jobVacancies = new ArrayList<>();
        try {
            jobVacancies = jobVacancyDAO.getVacancies();
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return jobVacancies;
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to add job vacancy
     *
     * @param jobVacancy current job vacancy
     * @throws ServiceException
     */
    @Override
    public void addVacancy(JobVacancy jobVacancy) throws ServiceException {

        if (!validateJobVacancy(jobVacancy)) {
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        JobVacancyDAO jobVacancyDAO = daoFactory.getJobVacancyDAO();
        try {
            jobVacancyDAO.addVacancy(jobVacancy);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to delete job vacancy
     *
     * @param jobVacancyId
     * @throws ServiceException
     */
    @Override
    public void deleteVacancy(String jobVacancyId) throws ServiceException {
        if (!validateParam(jobVacancyId)) {
            logger.error("invalid jobVacancyId");
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        JobVacancyDAO jobVacancyDAO = daoFactory.getJobVacancyDAO();
        try {
            int jobVacancyIntId = Integer.parseInt(jobVacancyId);
            if (!validateId(jobVacancyIntId)) {
                throw new ServiceException();
            }
            jobVacancyDAO.deleteVacancy(jobVacancyIntId);
        } catch (IllegalArgumentException | NullPointerException | DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to get job vacancy info
     *
     * @param jobVacancyId
     * @return instance of {@link JobVacancy}
     * @throws ServiceException
     */
    @Override
    public JobVacancy getVacancy(String jobVacancyId) throws ServiceException {

        if (!validateParam(jobVacancyId)) {
            logger.error("invalid jobVacancyId");
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        JobVacancyDAO jobVacancyDAO = daoFactory.getJobVacancyDAO();
        try {
            int jobVacancyIntId = Integer.parseInt(jobVacancyId);

            if (!validateId(jobVacancyIntId)) {
                throw new ServiceException();
            }
            return jobVacancyDAO.getVacancy(jobVacancyIntId);
        } catch (IllegalArgumentException | NullPointerException | DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to restore vacancy
     *
     * @param title title of job vacancy
     * @throws ServiceException
     */
    @Override
    public void restoreVacancy(String title) throws ServiceException {
        if (!validateParam(title)) {
            logger.error("invalid title");
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        JobVacancyDAO jobVacancyDAO = daoFactory.getJobVacancyDAO();
        try {
            jobVacancyDAO.restoreVacancy(title);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }

    /**
     * Method checks the input parameters performs a dao level call
     * to search vacancy
     *
     * @param title title of job vacancy
     * @throws ServiceException
     */
    @Override
    public JobVacancy searchVacancy(String title) throws ServiceException {
        if (!validateParam(title)) {
            logger.error("invalid title");
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        JobVacancyDAO jobVacancyDAO = daoFactory.getJobVacancyDAO();
        try {
            return jobVacancyDAO.searchVacancy(title);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
    }
}
