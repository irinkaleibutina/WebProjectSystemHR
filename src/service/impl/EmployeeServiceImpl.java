package service.impl;

import bean.EmployeeHR;
import dao.EmployeeDAO;
import dao.exception.DAOException;
import dao.factory.DAOFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import service.EmployeeService;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by irinaleibutina on 05.04.17.
 */
public class EmployeeServiceImpl implements EmployeeService {
    private static final Logger logger = LogManager.getLogger(EmployeeServiceImpl.class.getName());
    @Override
    public void employeeRegistration(EmployeeHR employeeHR) throws ServiceException {

        if(!validateEmployeeInfo(employeeHR)){
            throw new ServiceException("information is not correct");
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        try {
            employeeDAO.addEmployee(employeeHR);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public void deleteEmployee(String login) throws ServiceException {

        if (!validateParam(login)) {
            logger.error("invalid login");
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        try {
            employeeDAO.deleteEmployee(login);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public EmployeeHR signIn(String login, String password) throws ServiceException {

        if (!validateParam(login)) {
            logger.error("invalid login");
            throw new ServiceException("information is not correct");
        }
        if (!validateParam(password)) {
            logger.error("invalid password");
            throw new ServiceException("information is not correct");
        }

        DAOFactory daoFactory = DAOFactory.getInstance();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        try {
            return employeeDAO.signIn(login, password);
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    @Override
    public List<EmployeeHR> getEmployees() throws ServiceException {
        DAOFactory daoFactory = DAOFactory.getInstance();
        EmployeeDAO employeeDAO = daoFactory.getEmployeeDAO();
        try {
            return employeeDAO.getEmployees();
        } catch (DAOException e) {
            logger.error(e);
            throw new ServiceException(e);
        }
    }

    // Validation
    private boolean validateEmployeeInfo(EmployeeHR employeeHR) {

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

        String nameRegEx = "[a-zA-Z]+([-\'][a-zA-Z]+)*";
        String surnameRegex = "[a-zA-Z]+([-\'\\s][a-zA-Z]+)*";
        String emailRegEx = "(([^<>()\\[\\]\\\\.,;:\\s@\"]+(\\.[^<>()\\[\\]\\\\.,;:\\s@\"]+)*)|(\".+\"))@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))";
        String passwordRegEx = "(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])[0-9a-zA-Z]{6,25}";
        String loginRegEx = "[a-zA-Z]+([-\'][a-zA-Z]+)*"; ///// TODO check it
        String phoneRegEx = "[0-9]";
        String workIdRegEx = "\\d{5,}";

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
        if (!workId.matches(workIdRegEx)) {
            logger.error("workId is not correct");
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
