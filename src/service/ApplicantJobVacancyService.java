package service;

import dao.exception.DAOException;
import service.exception.ServiceException;

/**
 * Created by irinaleibutina on 09.04.17.
 */
public interface ApplicantJobVacancyService {
    void submitApplication(String applicantId, String jobVacancyId) throws ServiceException;
    void deleteApplication(String applicantId, String jobVacancyId) throws ServiceException;
}
