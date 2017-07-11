package by.epam.systemHR.dao;

import by.epam.systemHR.bean.Applicant;
import by.epam.systemHR.dao.factory.DAOFactory;
import by.epam.systemHR.dao.pool.ConnectionPool;
import by.epam.systemHR.dao.pool.impl.ConnectionPoolImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicantDAOTest {

    private ApplicantDAO applicantDAO;
    private ConnectionPool pool;
    private Applicant applicant;

    private static final String NAME = "TestName";
    private static final String SURNAME = "TestSurname";
    private static final String EMAIL= "test_email@example.com";
    private static final String PHONE_NUMBER  = "TestPhone";
    private static final String PASSWORD = "TestPassword";
    private static final String LOGIN= "TestLogin";

    @Before
    public void setUp() throws Exception {
        applicantDAO = DAOFactory.getInstance().getApplicant();
        pool = ConnectionPoolImpl.getInstance();
        pool.init();

        applicant = buildApplicant();
    }

    @After
    public void tearDown() throws Exception {
        applicantDAO= null;

        pool.dispose();
        pool = null;

        applicant = null;
    }

    @Test
    public void addApplicant() throws Exception {

        applicantDAO.addApplicant(applicant);
    }

    @Test
    public void deleteApplicant() throws Exception {

         applicantDAO.deleteApplicant(LOGIN);
    }

    @Test
    public void searchApplicant() throws Exception {

        applicantDAO.searchApplicant(NAME, SURNAME);
    }

    @Test
    public void signIn() throws Exception {

        applicantDAO.signIn(LOGIN, PASSWORD);
    }

    @Test
    public void updateInfo() throws Exception {

        applicantDAO.updateInfo(applicant);
    }

    @Test
    public void getActualInformation() throws Exception {

        applicantDAO.getActualInformation(LOGIN);
    }

    @Test
    public void getApplicants() throws Exception {

        applicantDAO.getApplicants();
    }

    private Applicant buildApplicant(){
        Applicant applicant = new Applicant();
        applicant.setName(NAME);
        applicant.setSurname(SURNAME);
        applicant.setLogin(LOGIN);
        applicant.setPassword(PASSWORD);
        applicant.setEmail(EMAIL);
        applicant.setPhoneNumber(PHONE_NUMBER);
        return applicant;
    }
}