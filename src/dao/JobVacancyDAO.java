package dao;

import bean.JobVacancy;
import dao.exception.DAOException;
import java.util.List;

/**
 * Interface {@code NewsDAO} is the class, that contains methods
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
    //void choiceVacancy();
}
