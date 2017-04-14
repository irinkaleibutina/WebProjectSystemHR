package dao;

import bean.JobVacancy;
import dao.exception.DAOException;

import java.util.List;

/**
 * Created by irinaleibutina on 27.02.17.
 */
public interface JobVacancyDAO {

    List<JobVacancy> getVacancies() throws DAOException;
    void addVacancy(JobVacancy jobVacancy) throws DAOException;
    void deleteVacancy(int jobVacancyId) throws DAOException;
    JobVacancy searchVacancy(int jobVacancyId) throws DAOException;
    void restoreVacancy(String title) throws DAOException;

    //void choiceVacancy();
}
