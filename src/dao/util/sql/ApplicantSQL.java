package dao.util.sql;

/**
 *The class contains query strings to work with applicant info
 */
public final class ApplicantSQL {

    public static final String APPLICANT_INFORMATION = "SELECT * FROM mydb.applicant";

    public static final String APPLICANT_REGISTRATION = "INSERT INTO mydb.applicant(appl_id,appl_name,appl_surname," +
            " password, login, email, phone_number) VALUES (LAST_INSERT_ID(),?,?,SHA2(?,256),?,?,?)";

    public final static String DELETE_APPLICANT = "update mydb.applicant set current_status = 'N' where login= ?";

    public final static String SEARCH_APPLICANT = "SELECT * FROM mydb.applicant WHERE appl_name = ? AND appl_surname " +
            "= ? AND current_status = 'A'";

    public static final String APPLICANT = "SELECT * FROM mydb.applicant WHERE login=? AND password=(SHA2(?,256))";

    public static final String ADMIN = "SELECT * FROM mydb.employeeHR WHERE login=?";

    public static final String APPLICANT_JOBVACANCY = "SELECT job_vac_id, job_title FROM mydb.job_vacancy " +
            "where job_vac_id = (select job_vacancy_job_vac_id from mydb.applicant_job_vacancy " +
            "where applicant_appl_id = ? AND current_status = 'A')";

    public static final String APPLICANT_INTERVIEW = "SELECT * FROM mydb.interview WHERE interview_id = ?";

    public static final String UPDATE_APPLICANT_INFO = "UPDATE mydb.applicant SET " +
            "appl_name = ?, appl_surname = ?,country = ?, city = ?, email =?," +
            "phone_number = ?, education=?, prof_skills = ? WHERE appl_id =?";

    public static final String ACTUAL_APPLICANT_INFO = "SELECT * FROM mydb.applicant WHERE login = ?";

    public static final String APPLICANT_SKILLS = "SELECT * FROM mydb.prof_skills " +
            "WHERE id_prof_skills IN (SELECT prof_skills_id_prof_skills FROM mydb.applicant_prof_skills " +
            "WHERE applicant_appl_id = ? AND current_status ='on')";
}
