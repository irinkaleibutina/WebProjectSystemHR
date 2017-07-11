package by.epam.systemHR.dao;

import by.epam.systemHR.dao.factory.DAOFactory;
import by.epam.systemHR.dao.pool.ConnectionPool;
import by.epam.systemHR.dao.pool.impl.ConnectionPoolImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ApplicantJobVacancyDAOTest {

    private ApplicantJobVacancyDAO applicantJobVacancyDAO;
    private ConnectionPool pool;

    private static final int JOB_VACANCY_ID = 5;
    private static final int APPLICANT_ID = 5;

    @Before
    public void setUp() throws Exception {
        applicantJobVacancyDAO = DAOFactory.getInstance().getApplicantJobVacancyDAO();
        pool = ConnectionPoolImpl.getInstance();
        pool.init();

    }

    @After
    public void tearDown() throws Exception {
        applicantJobVacancyDAO= null;

        pool.dispose();
        pool = null;

    }

    @Test
    public void submitApplication() throws Exception {

        applicantJobVacancyDAO.submitApplication(APPLICANT_ID, JOB_VACANCY_ID);
    }

    @Test
    public void deleteApplication() throws Exception {

        applicantJobVacancyDAO.deleteApplication(APPLICANT_ID, JOB_VACANCY_ID);
    }

}