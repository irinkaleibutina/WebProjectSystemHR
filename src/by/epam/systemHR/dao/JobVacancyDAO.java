package by.epam.systemHR.dao;

import by.epam.systemHR.dao.exception.DAOException;
import by.epam.systemHR.bean.JobVacancy;

import java.util.List;

/**
 * Interface {@code JobVacancyDAO} is the class, that contains methods
 * to work with job vacancy info in the data base.
 * @author irinaleibutina
 */
public interface JobVacancyDAO {

    List<JobVacancy> getVacancies() throws DAOException;
    void addVacancy(JobVacancy jobVacancy) throws DAOException;
    void deleteVacancy(int jobVacancyId) throws DAOException;
    JobVacancy getVacancy(int jobVacancyId) throws DAOException;
    void restoreVacancy(String title) throws DAOException;
    JobVacancy searchVacancy(String title) throws DAOException;
}
