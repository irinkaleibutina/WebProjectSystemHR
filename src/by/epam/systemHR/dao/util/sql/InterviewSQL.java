package by.epam.systemHR.dao.util.sql;

/**
 *The class contains query strings to work with interview info
 */
public final class InterviewSQL {
    
    public static final String UPDATE_PRELIMINARY_INTERVIEW = "update mydb.interview set preliminary_interview =?," +
            "date_pre_int = ?, time_pre_int = ? WHERE interview_id = ?";

    public static final String INTERVIEW = "SELECT *FROM mydb.interview WHERE interview_id = ?";

    public static final String CREATE_PRELIMINARY_INTERVIEW = "INSERT INTO mydb.interview(interview_id, " +
            "preliminary_interview, date_pre_int, time_pre_int) VALUES (?,?,?,?)";

    public static final String UPDATE_TECHNICAL_INTERVIEW = "UPDATE mydb.interview set technical_interview=?," +
            " date_tec_int=?, time_tec_int=? WHERE interview_id = ?";

    public static final String CREATE_TECHNICAL_INTERVIEW = "INSERT INTO mydb.interview(interview_id, " +
            "technical_interview, date_tec_int, time_tec_int) VALUES (?,?,?,?)";
}
