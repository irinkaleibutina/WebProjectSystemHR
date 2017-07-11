package by.epam.systemHR.dao.util.sql;

/**
 *The class contains query strings to work with applicant's job vacancy info
 */
public final class ApplicantJobVacancySQL {

    public static final String SUBMIT_APPLICATION = "INSERT INTO mydb.applicant_job_vacancy(applicant_appl_id, " +
            "job_vacancy_job_vac_id) VALUES (?,?)";

    public static final String DELETE_APPLICATION = "UPDATE mydb.applicant_job_vacancy SET current_status = 'N'" +
            " WHERE applicant_appl_id = ? AND job_vacancy_job_vac_id = ?";

    public final static String SEARCH_APPLICATION = "SELECT * FROM mydb.applicant_job_vacancy " +
            "WHERE applicant_appl_id = ? AND job_vacancy_job_vac_id = ? AND current_status = 'N'";

    public final static String CHECK_APPLICATION = "SELECT * FROM mydb.applicant_job_vacancy " +
            "WHERE applicant_appl_id = ?  AND current_status = 'A'";

    public static final String UPDATE_APPLICATION = "UPDATE mydb.applicant_job_vacancy SET current_status = 'A'" +
            " WHERE applicant_appl_id = ? AND job_vacancy_job_vac_id = ?";

    public static final String DELETE_INTERVIEW = "UPDATE  mydb.interview SET preliminary_interview ='E', technical_interview='E'," +
            "date_pre_int = null, date_tec_int = null, time_pre_int =null, time_tec_int = NULL WHERE interview_id = ?";
}
