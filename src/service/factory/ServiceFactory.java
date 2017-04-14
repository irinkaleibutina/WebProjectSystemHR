package service.factory;

import bean.Applicant;
import bean.Interview;
import dao.EmployeeDAO;
import service.*;
import service.impl.*;

/**
 * Created by irinaleibutina on 20.02.17.
 */
public class ServiceFactory {
    private static final ServiceFactory instance = new ServiceFactory();

    private ServiceFactory() {
    }

    private ApplicantService applicantService = new ApplicantServiceImpl();

    private EmployeeService employeeService = new EmployeeServiceImpl();

    private JobVacancyService jobVacancyService = new JobVacancyServiceImpl();

    private ApplicantJobVacancyService applicantJobVacancyService = new ApplicantJobVacancyServiceImpl();

    private InterviewService interviewService = new InterviewServiceImpl();

    public ApplicantService getApplicantService() {
        return applicantService;
    }

    public EmployeeService getEmployeeService() {
        return employeeService;
    }

    public JobVacancyService getJobVacancyService() {
        return jobVacancyService;
    }

    public ApplicantJobVacancyService getApplicantJobVacancyService() {
        return applicantJobVacancyService;
    }

    public InterviewService getInterviewService() {
        return interviewService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
