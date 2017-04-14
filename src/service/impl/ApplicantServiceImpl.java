package service.impl;

import bean.Applicant;
import dao.ApplicantDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.ApplicantService;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by irinaleibutina on 05.04.17.
 */
public class ApplicantServiceImpl implements ApplicantService {
    private static final Logger logger = LogManager.getLogger(ApplicantServiceImpl.class.getName());

    @Override
    public List<Applicant> getApplicants() throws ServiceException {

        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            return applicantDAO.getApplicants();
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean applicantRegistration(Applicant applicant) throws ServiceException {

        if (!validateApplicantRegInfo(applicant)) {
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            if (applicantDAO.addApplicant(applicant)) {
                return true;
            }
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
        return false;
    }

    @Override
    // public void deleteApplicant(Applicant applicant) throws ServiceException {
    public void deleteApplicant(String login) throws ServiceException {

        if (!validateParam(login)) {
            logger.error("invalid login");
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            applicantDAO.deleteApplicant(login);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Applicant searchApplicant(String name, String surname) throws ServiceException {

        if (!validateParam(name)) {
            logger.error("invalid name");
            throw new ServiceException("information is not correct");
        }

        if (!validateParam(surname)) {
            logger.error("invalid surname");
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            return applicantDAO.searchApplicant(name, surname);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public Applicant signIn(String login, String password) throws ServiceException {

        if (!validateParam(login)) {
            logger.error("invalid login");
            throw new ServiceException("information is not correct");
        }
        if (!validateParam(password)) {
            logger.error("invalid password");
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            //applicant = new Applicant();
            return applicantDAO.signIn(login, password);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void updateInfo(Applicant applicant) throws ServiceException {

        if (!validateApplicantUpdateInfo(applicant)) {
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        ApplicantDAO applicantDAO = daoFactory.getApplicant();
        try {
            applicantDAO.updateInfo(applicant);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    // Validation

    private boolean validateApplicantRegInfo(Applicant applicant) {
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

        // String phone = applicant.getPhoneNumber();
        // if(!validateParam(phone)){
        //     logger.error("invalid phone number");
        //     return false;
        // }

        String nameRegEx = "[a-zA-Zа-яА-Я]+([-\'][а-яА-Яa-zA-Z]+)*";
        String surnameRegex = "[а-яА-Яa-zA-Z]+([-\'\\s][а-яА-Яa-zA-Z]+)*";
        String emailRegEx = "(^[a-zA-Z0-9_.]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+$)";
        String passwordRegEx = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,25}";
        String loginRegEx = "[\\w._-]{6,}";
        String phoneRegEx = "\\+[0-9]{12}";
        String dateRegEx = "^(0[1-9]|[12][0-9]|3[01])[- \\.](0[1-9]|1[012])[- \\.](19|20)\\d\\d$";
        String timeRegEx = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";

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
        //   if (!phone.matches(phoneRegEx)) {
        //       logger.error("phone is not correct");
        //       return false;
        //   }

        return true;
    }

    private boolean validateApplicantUpdateInfo(Applicant applicant) {

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

        // String phone = applicant.getPhoneNumber();
        // if(!validateParam(phone)){
        //     logger.error("invalid phone number");
        //     return false;
        // }

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

        String education = applicant.getEducation();
        if (!validateParam(education)) {
            logger.error("invalid education");
            return false;
        }

        String skills = applicant.getProfessionalSkills();
        if (!validateParam(skills)) {
            logger.error("invalid skills");
            return false;
        }

        String nameRegEx = "[a-zA-Z]+([-\'][a-zA-Z]+)*";
        String surnameRegex = "[a-zA-Z]+([-\'\\s][a-zA-Z]+)*";
        String emailRegEx = "(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))";
        String countryRegEx = "[а-яa-zА-ЯA-Z]{2,}";
        String cityRegEx = "[а-яa-zА-ЯA-Z]{2,}";
        String phoneRegEx = "\\+[0-9]{12}";


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

        // if (!phone.matches(phoneRegEx)) {
        //     logger.error("phone is not correct");
        //     return false;
        // }

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

    private boolean validateParam(String param) {
        if ((param == null) || (param.isEmpty())) {
            return false;
        }
        return true;
    }
}
