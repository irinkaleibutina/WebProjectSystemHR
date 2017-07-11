package by.epam.systemHR.service.factory;

import by.epam.systemHR.service.*;
import by.epam.systemHR.service.impl.*;

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

    private ApplicantSkillsService applicantSkillsService = new ApplicantSkillsServiceImpl();


    private NewsService newsService = new NewsServiceImpl();

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

    public ApplicantSkillsService getApplicantSkillsService() {
        return applicantSkillsService;
    }

    public NewsService getNewsService(){
        return newsService;
    }

    public static ServiceFactory getInstance() {
        return instance;
    }
}
