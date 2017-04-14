package service;


import bean.JobVacancy;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by irinaleibutina on 05.04.17.
 */
public interface JobVacancyService {

    List<JobVacancy> getVacancies() throws ServiceException;

    void addVacancy(JobVacancy jobVacancy) throws ServiceException;

    void deleteVacancy(String jobVacancyId) throws ServiceException;

    JobVacancy searchVacancy(String jobVacancyId) throws ServiceException;

    void restoreVacancy(String title) throws ServiceException;

}
