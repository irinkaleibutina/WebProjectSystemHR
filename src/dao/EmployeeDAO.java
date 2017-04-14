package dao;

import bean.EmployeeHR;
import dao.exception.DAOException;

import java.util.List;

/**
 * Created by irinaleibutina on 02.04.17.
 */
public interface EmployeeDAO {
    void addEmployee(EmployeeHR employeeHR) throws DAOException;
    void deleteEmployee(String login) throws DAOException;
    EmployeeHR signIn(String login, String password) throws DAOException;
    List<EmployeeHR> getEmployees() throws DAOException;

    // void updateInformation(EmployeeHR employeeHR) throws DAOException;
}
