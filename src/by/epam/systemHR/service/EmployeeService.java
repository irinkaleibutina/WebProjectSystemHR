package by.epam.systemHR.service;

import by.epam.systemHR.service.exception.ServiceException;
import by.epam.systemHR.bean.EmployeeHR;

import java.util.List;

/**
 * Interface {@code EmployeeService} is the class, that contains methods
 * to work with employee info
 * @author irinaleibutina
 */
public interface EmployeeService {

    void employeeRegistration(EmployeeHR employeeHR) throws ServiceException;
    void deleteEmployee(String login) throws ServiceException;
    EmployeeHR signIn(String login, String password) throws ServiceException;
    List<EmployeeHR> getEmployees() throws ServiceException;
    void updateEmployeeInfo (EmployeeHR employeeHR) throws ServiceException;
}
