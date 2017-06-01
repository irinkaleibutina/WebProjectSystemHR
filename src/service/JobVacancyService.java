package service;

import bean.JobVacancy;
import service.exception.ServiceException;

import java.util.List;

/**
 * Interface {@code JobVacancyService} is the class, that contains methods
 * to work with job vacancy info
 * @author irinaleibutina
 */
public interface JobVacancyService {

    List<JobVacancy> getVacancies() throws ServiceException;
    void addVacancy(JobVacancy jobVacancy) throws ServiceException;
    void deleteVacancy(String jobVacancyId) throws ServiceException;
    JobVacancy getVacancy(String jobVacancyId) throws ServiceException;
    void restoreVacancy(String title) throws ServiceException;
    JobVacancy searchVacancy(String title) throws ServiceException;
}
