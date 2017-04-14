import bean.*;
import dao.*;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import service.exception.ServiceException;
import service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


/**
 * Created by irinaleibutina on 20.02.17.
 */
public class main {

    public static void main(String[] args) throws DAOException {

        /* Admin Registration */

     //mployeeHR employeeHR = new EmployeeHR();
     //mployeeHR.setName("");
     //mployeeHR.setSurname("Ivano");
     //mployeeHR.setLogin("1");
     //mployeeHR.setPassword("Ivan12345");
     //mployeeHR.setEmail("Ivanmail.ru");
     //mployeeHR.setPhoneNumber("+375299695236");
     //mployeeHR.setWorkId("2738");
//
     // ServiceFactory serviceFactory = ServiceFactory.getInstance();
     // RegistrationService registrationService = serviceFactory.registrationService();
     // try {
     //     registrationService.adminRegistration(employeeHR);
     // } catch  (ServiceException e) {
     //     e.printStackTrace();
     // }
     //   System.out.println("New admin was added");



   /* Applicant Registration */

     //  Applicant applicant = new Applicant();
////
     //  applicant.setName("Ninasa");
     //  applicant.setSurname("ssas");
     //  applicant.setLogin("nop");
     //  applicant.setPassword("12asnoajs");
     //  applicant.setEmail("nasina@mail.ru");
//
//
     // ServiceFactory serviceFactory = ServiceFactory.getInstance();
     // RegistrationService registration = serviceFactory.registrationService();
     // try {
     //     registration.applicantRegistration(applicant);
     // } catch (ServiceException e) {
     //     e.printStackTrace();
     // }

        /* Delete Applicant */

        // DAOFactory daoFactory = DAOFactory.getInstance();
        // ApplicantDAO applicantDAO = daoFactory.getApplicant();
        // applicantDAO.deleteApplicant(applicant);


        /* Search applicant */

        // DAOFactory daoFactory = DAOFactory.getInstance();
        // ApplicantDAO applicantDAO = daoFactory.getApplicant();
        // applicantDAO.searchApplicant("Irina", "Ivanova");


        /*Sign In applicant*/

       // DAOFactory daoFactory = DAOFactory.getInstance();
       // ApplicantDAO applicantDAO = daoFactory.getApplicant();
       // System.out.println(applicantDAO.signIn("1", "1"));


        /* Delete Employee */
        //DAOFactory daoFactory = DAOFactory.getInstance();
        //EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        //employeeDAO.deleteEmployee(employeeHR);


        /* Sign In admin */

      //  DAOFactory daoFactory = DAOFactory.getInstance();
      //  EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
      //  employeeDAO.signIn("root", "root");

        /* Get admins */

         //DAOFactory daoFactory = DAOFactory.getInstance();
         //EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
         //employeeDAO.getEmployees();
         //System.out.println("OK");

        /* Get applicants */

        // DAOFactory daoFactory = DAOFactory.getInstance();
        // ApplicantDAO applicantDAO = daoFactory.getApplicant();
        // applicantDAO.getApplicants();
        // System.out.println("OK");


        /* Delete vacancy*/

        // JobVacancy jobVacancy = new JobVacancy();
        // jobVacancy.setJobTitle("job1");
        // JobVacancyDAO jobVacancyDAO= daoFactory.getJobVacancyDAO();
        // jobVacancyDAO.deleteVacancy(jobVacancy);


        /* Add vacancy */
       // JobVacancy jobVacancy= new JobVacancy();
//
       // jobVacancy.setJobTitle("dreamjob");
       // jobVacancy.setDescription("It very hard work");
//
       // //DAOFactory daoFactory = DAOFactory.getInstance();
//
       // JobVacancyDAO jobVacancyDAO = daoFactory.getJobVacancyDAO();
       // jobVacancyDAO.addVacancy(jobVacancy);


        /* Submit application*/

       // ApplicantJobVacancyDAO applicantJobVacancyDAO = daoFactory.getApplicantJobVacancyDAO();
       // applicantJobVacancyDAO.submitApplication(6,8);
       // System.out.println("Insert");



        /*Add  pre int*/

      // Interview interview = new Interview();
      // interview.setPreliminaryInterview(InterviewResult.S);
      // interview.setDatePreInt("10.10.2017");
      // interview.setTimePreInt("12.30");

      // DAOFactory factory = DAOFactory.getInstance();
      // InterviewDAO interviewDAO =factory.getInterviewDAO();
      // interviewDAO.updatePreliminaryInterview(3, interview);

      // System.out.println("Ok");


        // Check regExp

        String dateRegEx = "^(0[1-9]|[12][0-9]|3[01])[- \\.](0[1-9]|1[012])[- \\.](19|20)\\d\\d$"; // TODO check work
        String timeRegEx = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        String statusRegEx = "[S|F|E]"; // S, E, F

        if (!"10.10.1900".matches(dateRegEx)) {
            System.out.println("false");
        }
        else System.out.println("true");
      // if (!time.matches(timeRegEx)) {
      //     logger.error("time is not correct");
      //     return false;
      // }
      // if (!status.matches(statusRegEx)) {
      //     logger.error("status is not correct");
      //     return false;
      // }
    }
}


