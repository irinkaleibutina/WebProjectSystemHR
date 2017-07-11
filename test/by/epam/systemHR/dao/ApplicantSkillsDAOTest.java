package by.epam.systemHR.dao;

import by.epam.systemHR.dao.factory.DAOFactory;
import by.epam.systemHR.dao.pool.ConnectionPool;
import by.epam.systemHR.dao.pool.impl.ConnectionPoolImpl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

public class ApplicantSkillsDAOTest {

    private ApplicantSkillsDAO applicantSkillsDAO;
    private ConnectionPool pool;
    private Map<String, String> skills;

    private static final int APPLICANT_ID = 8;
    private static final int SKILL_ID = 3;



    @Before
    public void setUp() throws Exception {
        applicantSkillsDAO = DAOFactory.getInstance().getApplicantSkillsDAO();
        pool = ConnectionPoolImpl.getInstance();
        pool.init();

        skills = buildListOfSkills();

    }

    @After
    public void tearDown() throws Exception {
        applicantSkillsDAO = null;

        pool.dispose();
        pool = null;

    }

    @Test
    public void addSkills() throws Exception {

        applicantSkillsDAO.addSkills(APPLICANT_ID, skills);
    }

    @Test
    public void deleteSkills() throws Exception {

        applicantSkillsDAO.deleteSkills(APPLICANT_ID, SKILL_ID);
    }

    @Test
    public void getAllTechnologies() throws Exception {

        applicantSkillsDAO.getAllTechnologies();
    }

    public Map<String, String> buildListOfSkills() {
        Map<String, String> skills = new HashMap<>();
        skills.put("Java", "on");
        skills.put("C/C++", "null");
        skills.put(".Net", "null");
        skills.put("C#",  "null");
        skills.put("Delphi",  "null");
        skills.put("PHP", "on");
        skills.put("Ruby", "null");
        skills.put("Python", "on");
        skills.put("HTML", "null");
        skills.put("PostgreSQL", "null");
        skills.put("MySQL", "null");
        skills.put("Microsoft SQL Server", "null");
        skills.put("Windows", "null");
        skills.put("Unix / Linux / Solaris", "on");
        skills.put("iOS", "on");
        skills.put("BlackBerry", "on");
        skills.put("Android", "on");

        return skills;

    }
}