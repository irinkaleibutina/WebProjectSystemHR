package by.epam.systemHR.dao;

import by.epam.systemHR.dao.exception.DAOException;

/**
 * Interface {@code ApplicantJobVacancyDAO} is the class, that contains methods
 * to work with job vacancy info in the data base.
 * @author irinaleibutina
 */
public interface ApplicantJobVacancyDAO {

    void submitApplication(int applicantId, int jobVacancyId) throws DAOException;
    void deleteApplication(int applicantId, int jobVacancyId) throws DAOException;
}
