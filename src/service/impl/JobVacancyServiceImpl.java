package service.impl;

import bean.EmployeeHR;
import bean.JobVacancy;
import dao.JobVacancyDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.JobVacancyService;
import service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by irinaleibutina on 05.04.17.
 */
public class JobVacancyServiceImpl implements JobVacancyService {
    private static final Logger logger = LogManager.getLogger(JobVacancyServiceImpl.class.getName());

    @Override
    public List<JobVacancy> getVacancies() throws ServiceException {
        DAOFactory factory = DAOFactory.getInstance();
        JobVacancyDAO jobVacancyDAO = factory.getJobVacancyDAO();
        List<JobVacancy> jobVacancies = new ArrayList<>();
        try {
            jobVacancies = jobVacancyDAO.getVacancies();
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return jobVacancies;
    }

    @Override
    public void addVacancy(JobVacancy jobVacancy) throws ServiceException {

        if (!validateEmployeeInfo(jobVacancy)) {
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        JobVacancyDAO jobVacancyDAO = daoFactory.getJobVacancyDAO();
        try {
            jobVacancyDAO.addVacancy(jobVacancy);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

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
            jobVacancyDAO.deleteVacancy(jobVacancyIntId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public JobVacancy searchVacancy(String jobVacancyId) throws ServiceException {

        if (!validateParam(jobVacancyId)) {
            logger.error("invalid jobVacancyId");
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        JobVacancyDAO jobVacancyDAO = daoFactory.getJobVacancyDAO();
        try {
            int jobVacancyIntId = Integer.parseInt(jobVacancyId);
            return jobVacancyDAO.searchVacancy(jobVacancyIntId);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

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
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    private boolean validateEmployeeInfo(JobVacancy jobVacancy){
        String title = jobVacancy.getJobTitle();
        if (!validateParam(title)) {
            logger.error("invalid title");
           return false;
        }

        String description = jobVacancy.getDescription();
        if (!validateParam(description)) {
            logger.error("invalid description");
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
