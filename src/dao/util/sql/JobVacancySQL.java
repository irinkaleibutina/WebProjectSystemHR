package dao.util.sql;

/**
 *The class contains query strings to work with job vacancy info
 */
public final class JobVacancySQL {

    public static final String VACANCY_INFORMATION = "SELECT * FROM job_vacancy WHERE current_status = 'A'";
    public static final String DELETE_VACANCY = "UPDATE mydb.job_vacancy SET current_status ='N' WHERE job_vac_id = ?";
    public static final String RESTORE_VACANCY = "UPDATE mydb.job_vacancy SET current_status ='A' WHERE job_title = ?";
    public static final String GET_VACANCY = "SELECT * FROM mydb.job_vacancy WHERE job_vac_id = ?";
    public static final String SEARCH_VACANCY = "SELECT * FROM mydb.job_vacancy WHERE job_title = ?";
    public static final String ADD_VACANCY = "INSERT INTO mydb.job_vacancy(job_vac_id, job_title,description,country, city)" +
            " VALUES (LAST_INSERT_ID(),?,?,?,?)";
}
