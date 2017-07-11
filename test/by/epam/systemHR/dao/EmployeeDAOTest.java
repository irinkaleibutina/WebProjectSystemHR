package by.epam.systemHR.dao;

import by.epam.systemHR.bean.EmployeeHR;
import by.epam.systemHR.dao.factory.DAOFactory;
import by.epam.systemHR.dao.pool.ConnectionPool;
import by.epam.systemHR.dao.pool.impl.ConnectionPoolImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class EmployeeDAOTest {

    private EmployeeDAO employeeDAO;
    private ConnectionPool pool;
    private EmployeeHR employeeHR;

    private static final String NAME = "TestName";
    private static final String SURNAME = "TestSurname";
    private static final String EMAIL = "test_email@example.com";
    private static final String PHONE_NUMBER = "TestPhone";
    private static final String PASSWORD = "TestEmployeePassword";
    private static final String LOGIN = "TestEmployeeLogin";
    private static final String WORK_ID = "238133";

    @Before
    public void setUp() throws Exception {
        employeeDAO = DAOFactory.getInstance().getEmployeeDAO();
        pool = ConnectionPoolImpl.getInstance();
        pool.init();

        employeeHR = buildEmployee();
    }

    @After
    public void tearDown() throws Exception {
        employeeDAO = null;

        pool.dispose();
        pool = null;

        employeeHR = null;
    }

    @Test
    public void addEmployee() throws Exception {

        employeeDAO.addEmployee(employeeHR);
    }

    @Test
    public void deleteEmployee() throws Exception {

        employeeDAO.deleteEmployee(LOGIN);
    }

    @Test
    public void signIn() throws Exception {

        employeeDAO.signIn(LOGIN, PASSWORD);
    }

    @Test
    public void getEmployees() throws Exception {

        employeeDAO.getEmployees();
    }

    @Test
    public void updateEmployeeInfo() throws Exception {
        employeeDAO.updateEmployeeInfo(employeeHR);
    }

    private EmployeeHR buildEmployee() {
        EmployeeHR employeeHR = new EmployeeHR();

        employeeHR.setName(NAME);
        employeeHR.setSurname(SURNAME);
        employeeHR.setLogin(LOGIN);
        employeeHR.setPassword(PASSWORD);
        employeeHR.setEmail(EMAIL);
        employeeHR.setPhoneNumber(PHONE_NUMBER);
        employeeHR.setWorkId(WORK_ID);

        return employeeHR;
    }
}