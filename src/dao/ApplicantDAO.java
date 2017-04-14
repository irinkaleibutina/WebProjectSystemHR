package dao;

import bean.Applicant;
import bean.Interview;
import bean.JobVacancy;
import dao.exception.DAOException;

import java.util.List;

/**
 * Created by irinaleibutina on 29.03.17.
 */
public interface ApplicantDAO {
    List<Applicant> getApplicants() throws DAOException;
    boolean addApplicant(Applicant applicant) throws DAOException;
    void deleteApplicant(String login) throws DAOException;
    Applicant searchApplicant(String name, String surname) throws DAOException;
    Applicant signIn(String login, String password) throws DAOException;
    void updateInfo(Applicant applicant) throws DAOException;

    // void updateInformation(Applicant applicant) throws DAOException;
}
