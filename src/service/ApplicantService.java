package service;

import bean.Applicant;
import dao.exception.DAOException;
import service.exception.ServiceException;

import java.util.List;

/**
 * Interface {@code ApplicantService} is the class, that contains methods
 * to work with applicant info
 * @author irinaleibutina
 */
public interface ApplicantService {

    List<Applicant> getApplicants() throws ServiceException;
    boolean applicantRegistration(Applicant applicant) throws ServiceException;
    void deleteApplicant(String login) throws ServiceException;
    List<Applicant> searchApplicant(String name, String surname) throws ServiceException;
    Applicant signIn(String login, String password) throws ServiceException;
    void updateInfo(Applicant applicant) throws ServiceException;
    Applicant getActualInformation(String login) throws ServiceException;
}
