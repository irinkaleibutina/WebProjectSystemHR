package by.epam.systemHR.dao;

import by.epam.systemHR.bean.Applicant;
import by.epam.systemHR.dao.exception.DAOException;

import java.util.List;

/**
 * Interface {@code ApplicantDAO} is the class, that contains methods
 * to work with applicant info in the data base.
 * @author irinaleibutina
 */
public interface ApplicantDAO {

    List<Applicant> getApplicants() throws DAOException;
    boolean addApplicant(Applicant applicant) throws DAOException;
    void deleteApplicant(String login) throws DAOException;
    List<Applicant> searchApplicant(String name, String surname) throws DAOException;
    Applicant signIn(String login, String password) throws DAOException;
    void updateInfo(Applicant applicant) throws DAOException;
    Applicant getActualInformation(String login) throws DAOException;
}
