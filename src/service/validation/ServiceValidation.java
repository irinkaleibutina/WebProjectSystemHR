package service.validation;

import bean.Applicant;
import bean.EmployeeHR;
import bean.Interview;
import bean.JobVacancy;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ServiceValidation {
    private static final Logger logger = LogManager.getLogger(ServiceValidation.class.getName());

    private static final String nameRegEx = "[a-zA-Zа-яА-Я]+([-\'][а-яА-Яa-zA-Z]+)*";
    private static final String surnameRegex = "[а-яА-Яa-zA-Z]+([-\'\\s][а-яА-Яa-zA-Z]+)*";
    private static final String emailRegEx = "(^[a-zA-Z0-9_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$)";
    private static final String passwordRegEx = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,25}";
    private static final String loginRegEx = "[\\w._-]{6,}";
    private static final String phoneRegEx = "\\+[0-9]{12}";
    private static final String dateRegEx = "^(0[1-9]|[12][0-9]|3[01])[- \\.](0[1-9]|1[012])[- \\.](19|20)\\d\\d$";
    private static final String timeRegEx = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
    private static final String countryRegEx = "[а-яa-zА-ЯA-Z.-]{2,}";
    private static final String cityRegEx = "[а-яa-zА-ЯA-Z.-]{2,}";
    private static final String workIdRegEx = "\\d{5,}";


    public static boolean validateData(String applicantId, String jobVacancyId) {

        if ((applicantId == null) || (applicantId.isEmpty())) {
            logger.error("invalid applicantId");
            return false;
        }

        if ((jobVacancyId == null) || (jobVacancyId.isEmpty())) {
            logger.error("invalid jobVacancyId");
            return false;
        }
        return true;
    }

    public static boolean validateId(int id) {
        if (id <= 0) {
            return false;
        }
        return true;
    }

    public static boolean validateParam(String param) {
        if ((param == null) || (param.isEmpty())) {
            return false;
        }
        return true;
    }

    public static boolean validateApplicantRegInfo(Applicant applicant) {
        String name = applicant.getName();
        if (!validateParam(name)) {
            logger.error("invalid name");
            return false;
        }

        String surname = applicant.getSurname();
        if (!validateParam(surname)) {
            logger.error("invalid surname");
            return false;
        }

        String email = applicant.getEmail();
        if (!validateParam(email)) {
            logger.error("invalid email");
            return false;
        }

        String password = applicant.getPassword();
        if (!validateParam(password)) {
            logger.error("invalid password");
            return false;
        }

        String login = applicant.getLogin();
        if (!validateParam(login)) {
            logger.error("invalid login");
            return false;
        }

        String phone = applicant.getPhoneNumber();
        if (!validateParam(phone)) {
            logger.error("invalid phone number");
            return false;
        }

        if (!name.matches(nameRegEx)) {
            logger.error("name is not correct");
            return false;
        }
        if (!surname.matches(surnameRegex)) {
            logger.error("surname is not correct");
            return false;
        }
        if (!email.matches(emailRegEx)) {
            logger.error("mail is not correct");
            return false;
        }
        if (!password.matches(passwordRegEx)) {
            logger.error("password is not correct");
            return false;
        }
        if (!login.matches(loginRegEx)) {
            logger.error("login is not correct");
            return false;
        }
        if (!phone.matches(phoneRegEx)) {
            logger.error("phone is not correct");
            return false;
        }

        return true;
    }

    public static boolean validateApplicantUpdateInfo(Applicant applicant) {

        String name = applicant.getName();
        if (!validateParam(name)) {
            logger.error("invalid name");
            return false;
        }

        String surname = applicant.getSurname();
        if (!validateParam(surname)) {
            logger.error("invalid surname");
            return false;
        }

        String email = applicant.getEmail();
        if (!validateParam(email)) {
            logger.error("invalid email");
            return false;
        }

        String phone = applicant.getPhoneNumber();
        if (!validateParam(phone)) {
            logger.error("invalid phone number");
            return false;
        }

        String country = applicant.getCountry();
        if (!validateParam(country)) {
            logger.error("invalid country");
            return false;
        }

        String city = applicant.getCity();
        if (!validateParam(city)) {
            logger.error("invalid city");
            return false;
        }

        if (!name.matches(nameRegEx)) {
            logger.error("name is not correct");
            return false;
        }
        if (!surname.matches(surnameRegex)) {
            logger.error("surname is not correct");
            return false;
        }
        if (!email.matches(emailRegEx)) {
            logger.error("mail is not correct");
            return false;
        }

        if (!phone.matches(phoneRegEx)) {
            logger.error("phone is not correct");
            return false;
        }

        if (!country.matches(countryRegEx)) {
            logger.error("country is not correct");
            return false;
        }
        if (!city.matches(cityRegEx)) {
            logger.error("name is not correct");
            return false;
        }
        return true;
    }

    public static boolean validateEmployeeInfo(EmployeeHR employeeHR) {

        String name = employeeHR.getName();
        if ((name == null) || (name.isEmpty())) {
            logger.error("invalid name");
            return false;
        }

        String surname = employeeHR.getSurname();
        if ((surname == null) || (surname.isEmpty())) {
            logger.error("invalid surname");
            return false;
        }

        String email = employeeHR.getEmail();
        if ((email == null) || (email.isEmpty())) {
            logger.error("invalid email");
            return false;
        }

        String password = employeeHR.getPassword();
        if ((password == null) || (password.isEmpty())) {
            logger.error("invalid password");
            return false;
        }

        String login = employeeHR.getLogin();
        if ((login == null) || (login.isEmpty())) {
            logger.error("invalid login");
            return false;
        }

        String phone = employeeHR.getPhoneNumber();
        if ((phone == null) || (phone.isEmpty())) {
            logger.error("invalid phone number");
            return false;
        }

        String workId = employeeHR.getWorkId();
        if ((workId == null) || (workId.isEmpty())) {
            logger.error("invalid workId number");
            return false;
        }

        if (!name.matches(nameRegEx)) {
            logger.error("name is not correct");
            return false;
        }
        if (!surname.matches(surnameRegex)) {
            logger.error("surname is not correct");
            return false;
        }
        if (!email.matches(emailRegEx)) {
            logger.error("mail is not correct");
            return false;
        }
        if (!login.matches(loginRegEx)) {
            logger.error("login is not correct");
            return false;
        }
        if (!phone.matches(phoneRegEx)) {
            logger.error("phone is not correct");
            return false;
        }
        if (!workId.matches(workIdRegEx)) {
            logger.error("workId is not correct");
            return false;
        }
        return true;
    }

    public static boolean validatePreInterview(Interview interview) {

        String date = interview.getDatePreInt();
        if (!validateParam(date)) {
            logger.error("invalid date");
            return false;
        } else {
            date = date.replace('/', '.');
            interview.setDatePreInt(date);
        }

        String time = interview.getTimePreInt();
        if (!validateParam(date)) {
            logger.error("invalid time");
            return false;
        }

        String status = interview.getPreliminaryInterview().toString();
        if (!validateParam(status)) {
            logger.error("invalid status");
            return false;
        }
        return true;
    }

    public static boolean validateTecInterview(Interview interview) {

        String date = interview.getDateTecInt();
        if (!validateParam(date)) {
            logger.error("invalid date");
            return false;
        } else {
            date = date.replace('/', '.');
            interview.setDateTecInt(date);
        }

        String time = interview.getTimeTecInt();
        if (!validateParam(date)) {
            logger.error("invalid time");
            return false;
        }

        String status = interview.getTechnicalInterview().toString();
        if (!validateParam(status)) {
            logger.error("invalid status");
            return false;
        }
        return true;
    }

    public static boolean validateJobVacancy(JobVacancy jobVacancy) {
        String title = jobVacancy.getJobTitle();
        if (!validateParam(title)) {
            logger.error("invalid title");
            return false;
        }

        String description = jobVacancy.getDescription();
        if (!validateParam(description)) {
            logger.error("invalid description");
            return false;
        }

        String country = jobVacancy.getCountry();
        if (!validateParam(country)) {
            logger.error("invalid country");
            return false;
        }

        String city = jobVacancy.getCity();
        if (!validateParam(city)) {
            logger.error("invalid city");
            return false;
        }

        if (!country.matches(countryRegEx)) {
            logger.error("country is not correct");
            return false;
        }
        if (!city.matches(cityRegEx)) {
            logger.error("name is not correct");
            return false;
        }
        return true;
    }
}

