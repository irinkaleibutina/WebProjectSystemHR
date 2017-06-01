package service;

import service.exception.ServiceException;

/**
 * Interface {@code ApplicantJobVacancyService} is the class, that contains methods
 * to work with job vacancy
 * @author irinaleibutina
 */
public interface ApplicantJobVacancyService {
    void submitApplication(String applicantId, String jobVacancyId) throws ServiceException;
    void deleteApplication(String applicantId, String jobVacancyId) throws ServiceException;
}
