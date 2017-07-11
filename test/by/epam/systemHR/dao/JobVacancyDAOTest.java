package by.epam.systemHR.dao;

import by.epam.systemHR.bean.JobVacancy;
import by.epam.systemHR.dao.factory.DAOFactory;
import by.epam.systemHR.dao.pool.ConnectionPool;
import by.epam.systemHR.dao.pool.impl.ConnectionPoolImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class JobVacancyDAOTest {

    private ConnectionPool pool;
    private JobVacancyDAO jobVacancyDAO;
    private JobVacancy jobVacancy;

    private static final String TITLE = "TestJob";
    private static final String DESCRIPTION = "Description";
    private static final String COUNTRY = "TestCountry";
    private static final String CITY = "TestCity";
    private static final int JOB_VACANCY_ID = 2;

    @Before

    public void setUp() throws Exception {
        jobVacancyDAO = DAOFactory.getInstance().getJobVacancyDAO();
        pool = ConnectionPoolImpl.getInstance();
        pool.init();

        jobVacancy = buildJobVacancy();
    }

    @After
    public void tearDown() throws Exception {
        jobVacancyDAO = null;

        pool.dispose();
        pool = null;

        jobVacancy = null;
    }

    @Test
    public void getVacancies() throws Exception {

        jobVacancyDAO.getVacancies();
    }

    @Test
    public void addVacancy() throws Exception {

        jobVacancyDAO.addVacancy(jobVacancy);
    }

    @Test
    public void deleteVacancy() throws Exception {
        jobVacancyDAO.deleteVacancy(JOB_VACANCY_ID);
    }

    @Test
    public void getVacancy() throws Exception {
        jobVacancyDAO.getVacancy(JOB_VACANCY_ID);
    }

    @Test
    public void restoreVacancy() throws Exception {
        jobVacancyDAO.restoreVacancy(TITLE);
    }

    @Test
    public void searchVacancy() throws Exception {
        jobVacancyDAO.searchVacancy(TITLE);
    }

    private JobVacancy buildJobVacancy() {
        JobVacancy jobVacancy = new JobVacancy();

        jobVacancy.setJobTitle(TITLE);
        jobVacancy.setDescription(DESCRIPTION);
        jobVacancy.setCity(CITY);
        jobVacancy.setCountry(COUNTRY);
        return jobVacancy;
    }
}