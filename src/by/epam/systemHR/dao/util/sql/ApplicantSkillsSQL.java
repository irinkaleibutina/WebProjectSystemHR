package by.epam.systemHR.dao.util.sql;

/**
 *The class contains query strings to work with applicant's skills info
 */
public final class ApplicantSkillsSQL {

    public static final String SKILLS = "SELECT * FROM mydb.prof_skills";

    public static final String DELETE_SKILL = "UPDATE mydb.applicant_prof_skills SET current_status = 'null' " +
            "WHERE applicant_appl_id = ? AND prof_skills_id_prof_skills = ?";

    public static final String ADD_SKILL = "INSERT INTO mydb.applicant_prof_skills( current_status, " +
            "applicant_appl_id, prof_skills_id_prof_skills) VALUES (?,?,?)";

    public static final String UPDATE_APPLICANT_SKILLS = "UPDATE mydb.applicant_prof_skills SET " +
            "current_status = ? WHERE applicant_appl_id = ? AND prof_skills_id_prof_skills = ?";

    public static final String SEARCH_APPLICANT_SKILL = "SELECT * FROM mydb.applicant_prof_skills WHERE applicant_appl_id = ?";

    public static final String GET_ID = "SELECT * FROM mydb.prof_skills WHERE title = ?";
}
