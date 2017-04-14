package dao;

import dao.exception.DAOException;

/**
 * Created by irinaleibutina on 09.04.17.
 */
public interface ApplicantJobVacancyDAO {

    void submitApplication(int applicantId, int jobVacancyId) throws DAOException;
    void deleteApplication(int applicantId, int jobVacancyId) throws DAOException;
}
