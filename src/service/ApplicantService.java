package service;

import bean.Applicant;
import dao.exception.DAOException;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by irinaleibutina on 05.04.17.
 */
public interface ApplicantService {
    List<Applicant> getApplicants() throws ServiceException;
    boolean applicantRegistration(Applicant applicant) throws ServiceException;
    void deleteApplicant(String login) throws ServiceException;
    Applicant searchApplicant(String name, String surname) throws ServiceException;
    Applicant signIn(String login, String password) throws ServiceException;
    void updateInfo(Applicant applicant) throws ServiceException;
}
