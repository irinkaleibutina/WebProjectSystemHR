package by.epam.systemHR.dao;

import by.epam.systemHR.bean.EmployeeHR;
import by.epam.systemHR.dao.exception.DAOException;

import java.util.List;

/**
 * Interface {@code EmployeeDAO} is the class, that contains methods
 * to work with employee info in the data base.
 * @author irinaleibutina
 */
public interface EmployeeDAO {
    void addEmployee(EmployeeHR employeeHR) throws DAOException;
    void deleteEmployee(String login) throws DAOException;
    EmployeeHR signIn(String login, String password) throws DAOException;
    List<EmployeeHR> getEmployees() throws DAOException;
    void updateEmployeeInfo (EmployeeHR employeeHR) throws DAOException;
}
