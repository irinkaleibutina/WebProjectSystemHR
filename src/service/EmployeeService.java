package service;

import bean.EmployeeHR;
import service.exception.ServiceException;

import java.util.List;

/**
 * Created by irinaleibutina on 05.04.17.
 */
public interface EmployeeService {
    void employeeRegistration(EmployeeHR employeeHR) throws ServiceException;

    void deleteEmployee(String login) throws ServiceException;

    EmployeeHR signIn(String login, String password) throws ServiceException;

    List<EmployeeHR> getEmployees() throws ServiceException;
}
