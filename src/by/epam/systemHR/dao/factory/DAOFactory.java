package by.epam.systemHR.dao.factory;

import by.epam.systemHR.dao.*;
import by.epam.systemHR.dao.impl.*;
import by.epam.systemHR.dao.*;

/**
 * Factory pattern implementation for dao layer
 */
public final class DAOFactory {

    private static final DAOFactory instance = new DAOFactory();

    private DAOFactory() {
    }

    private JobVacancyDAO jobVacancyDAO = new JobVacancyDAOImpl();

    private ApplicantDAO applicant = new ApplicantDAOImpl();

    private EmployeeDAO employeeDAO = new EmployeeDAOImpl();

    private ApplicantJobVacancyDAO applicantJobVacancyDAO = new ApplicantJobVacancyDAOImpl();

    private InterviewDAO interviewDAO = new InterviewDAOImpl();

    private NewsDAO newsDAO = new NewsDAOImpl();

    private ApplicantSkillsDAO applicantSkillsDAO = new ApplicantSkillsDAOImpl();

    public JobVacancyDAO getJobVacancyDAO() {
        return jobVacancyDAO;
    }

    public ApplicantDAO getApplicant() {
        return applicant;
    }

    public EmployeeDAO getEmployeeDAO() {
        return employeeDAO;
    }

    public ApplicantJobVacancyDAO getApplicantJobVacancyDAO() {
        return applicantJobVacancyDAO;
    }

    public InterviewDAO getInterviewDAO() {
        return interviewDAO;
    }

    public NewsDAO getNewsDAO() {
        return newsDAO;
    }

    public ApplicantSkillsDAO getApplicantSkillsDAO() {
        return applicantSkillsDAO;
    }

    public static DAOFactory getInstance() {
        return instance;
    }
}
