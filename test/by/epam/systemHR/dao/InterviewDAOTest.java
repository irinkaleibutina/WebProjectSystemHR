package by.epam.systemHR.dao;

import by.epam.systemHR.bean.Interview;
import by.epam.systemHR.bean.InterviewResult;
import by.epam.systemHR.dao.factory.DAOFactory;
import by.epam.systemHR.dao.pool.ConnectionPool;
import by.epam.systemHR.dao.pool.impl.ConnectionPoolImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class InterviewDAOTest {

    private ConnectionPool pool;
    private InterviewDAO interviewDAO;
    private Interview preliminaryInterview;
    private Interview technicalInterview;

    private static final int APPLICANT_ID = 4;
    private static final InterviewResult RESULT = InterviewResult.S;
    private static final String DATE = "18.04.2017";
    private static final String TIME = "12.30";

    @Before
    public void setUp() throws Exception {
        interviewDAO = DAOFactory.getInstance().getInterviewDAO();
        pool = ConnectionPoolImpl.getInstance();
        pool.init();

        preliminaryInterview = buildPreliminaryInterview();
        technicalInterview = buildTechnicalInterview();
    }

    @After
    public void tearDown() throws Exception {
        interviewDAO = null;

        pool.dispose();
        pool = null;

        preliminaryInterview = null;
        technicalInterview = null;

    }

    @Test
    public void updatePreliminaryInterview() throws Exception {
        interviewDAO.updatePreliminaryInterview(APPLICANT_ID, preliminaryInterview);
    }

    @Test
    public void updateTechnicalInterview() throws Exception {
        interviewDAO.updateTechnicalInterview(APPLICANT_ID, technicalInterview);
    }

    private Interview buildPreliminaryInterview() {

        Interview interview = new Interview();
        interview.setPreliminaryInterview(RESULT);
        interview.setDatePreInt(DATE);
        interview.setTimePreInt(TIME);

        return interview;
    }

    private Interview buildTechnicalInterview() {

        Interview interview = new Interview();
        interview.setTechnicalInterview(RESULT);
        interview.setDateTecInt(DATE);
        interview.setTimeTecInt(TIME);

        return interview;
    }
}